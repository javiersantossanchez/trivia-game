package jdss.trivia.microservice.core.services;

import jdss.trivia.microservice.core.dto.QuestionIn;
import jdss.trivia.microservice.core.dto.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    public Mono<List<QuestionIn>>    getQuestions(String level) {

        final int easy ;
        final int medium ;
        final int hard ;
        final int inferno ;
        switch (level){
            case "Easy":
                easy =30;
                medium = 0;
                hard = 0;
                inferno =0;
                break;
            case "Medium":
                easy =15;
                medium = 25;
                hard = 0;
                inferno =0;
                break;
            case "Hard":
                easy =0;
                medium = 30;
                hard = 30;
                inferno =0;
                break;
            case "Inferno":
                easy =0;
                medium = 0;
                hard = 0;
                inferno =50;
                break;
            default:
                easy =0;
                medium = 0;
                hard = 0;
                inferno =0;
                break;
        }

        List<Flux<QuestionIn>> questions = new ArrayList<>();
        Mono<List<QuestionIn>> easyQuestions = this.execute(easy,"easy");
        if(easyQuestions != null){
            questions.add(easyQuestions.flatMapMany(Flux::fromIterable));
        }


        Mono<List<QuestionIn>> mediumQuestions = this.execute(medium,"medium");
        if(mediumQuestions != null){
            questions.add(mediumQuestions.flatMapMany(Flux::fromIterable));
        }

        Mono<List<QuestionIn>> hardQuestions = this.execute(hard,"hard");
        if(hardQuestions != null){
            questions.add(hardQuestions.flatMapMany(Flux::fromIterable));
        }

        Mono<List<QuestionIn>> infernoQuestions = this.execute(inferno,"inferno");
        if(infernoQuestions != null){
            questions.add(infernoQuestions.flatMapMany(Flux::fromIterable));
        }

        Mono<List<QuestionIn>> v =  Flux.merge(questions).collectList();
        return v;
    }

    private Mono<List<QuestionIn>> execute(final int numberOfQuestions, final String level){
        Mono<List<QuestionIn>> questions = null;
        if(numberOfQuestions > 0) {
            WebClient client3 = WebClient
                    .builder()
                    .baseUrl("https://opentdb.com")
                    .build();

            questions = client3.get()
                    .uri(uriBuilder -> uriBuilder.path("api.php")
                            .queryParam("amount", numberOfQuestions)
                            .queryParam("difficulty", level)
                            .build())
                    .retrieve().bodyToMono(Response.class)
                    .map(Response::getResults);
        }
        return questions;
    }
}
