package jdss.trivia.microservice.core.rest;

import jdss.trivia.microservice.core.entities.Score;
import jdss.trivia.microservice.core.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api/score")
public class ScoreApi {

    private ScoreRepository authorRepository;

    @Autowired
    public ScoreApi(ScoreRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("")
    public ResponseEntity<Iterable<Score>> getAll() {
        return new ResponseEntity(this.authorRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Score> create(@RequestBody Score score){
        Score result = this.authorRepository.save(score);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<Iterable<Score>> findByName(@PathVariable("name") String name){
        Iterable<Score> result = this.authorRepository.findByName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
           this.authorRepository.deleteById(id);
           return new ResponseEntity(HttpStatus.OK);
    }

}