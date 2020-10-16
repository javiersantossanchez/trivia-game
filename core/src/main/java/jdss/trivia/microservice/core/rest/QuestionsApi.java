package jdss.trivia.microservice.core.rest;

import jdss.trivia.microservice.core.dto.QuestionIn;
import jdss.trivia.microservice.core.dto.QuestionOut;
import jdss.trivia.microservice.core.dto.Setting;
import jdss.trivia.microservice.core.entities.Score;
import jdss.trivia.microservice.core.repository.ScoreRepository;
import jdss.trivia.microservice.core.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@RestController
@RequestMapping("/api/question")
public class QuestionsApi {


    private ScoreRepository authorRepository;

    private QuestionService questionService;

    @Autowired
    public QuestionsApi(ScoreRepository authorRepository, QuestionService questionService) {
        this.authorRepository = authorRepository;
        this.questionService =  questionService;
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> initGame(HttpSession session,@RequestBody Setting setting){
        Mono<List<QuestionIn>> v = this.questionService.getQuestions(setting.getLevel());

        //TODO:  I think I need find other way to fill the question Queue. I am breaking the pure function concept.
        Boolean re = v.map(item -> {
             session.setAttribute("user-name",setting.getUserName());
             session.setAttribute("score",0);
             session.setAttribute("current-question",null);
            session.setAttribute("questions",new LinkedList<QuestionIn>(item));
            return true;
        }).block();

        return new ResponseEntity(re,HttpStatus.OK);
    }

    @PostMapping("/finish")
    public ResponseEntity<Void> finish(HttpSession session) {
        if(!session.isNew()){
            int currentScore = (int) session.getAttribute("score");
            String userName = (String) session.getAttribute("user-name");
            Score score = new Score();
            score.setName(userName);
            score.setScore(currentScore);
            score.setTime(currentScore);
            authorRepository.save(score);
        }else{
            System.out.println("There is a problem with game status");
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{question}/answer/{answer}")
    public ResponseEntity<Integer> getNextQuestion(HttpSession session, @PathVariable("answer") String answer,@PathVariable("question") String question) {
        int currentScore = 0;
        if(!session.isNew()){
            currentScore = (int) session.getAttribute("score");
            QuestionIn currentQuestion = (QuestionIn) session.getAttribute("current-question");
            if(currentQuestion.getCorrect_answer().equals(answer)){
                currentScore += 2;
            }else{
                currentScore--;
            }
            session.setAttribute("score",currentScore);
        }else{
            System.out.println("There is a problem with game status");
        }

        return new ResponseEntity(currentScore, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Mono<QuestionIn>> getNextQuestion(HttpSession session) {
        Mono<QuestionOut> nextQuestion = null;
        if(!session.isNew()){
            Queue<QuestionIn> questionQueue = (Queue<QuestionIn>) session.getAttribute("questions");
            if(questionQueue!= null && !questionQueue.isEmpty() ) {
                QuestionIn aa = questionQueue.poll();
                session.setAttribute("current-question",aa);
                nextQuestion = Mono.just(new QuestionOut(aa));
            }
        }else{
            System.out.println("There is a problem");
        }
        return new ResponseEntity(nextQuestion,HttpStatus.OK);
    }


}