package jdss.trivia.microservice.core.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/configuration")
public class ConfigurationApi {


    @GetMapping("/levels")
    public ResponseEntity<List<String>> getAll() {
        List<String> level = new ArrayList<>();
        level.add("Easy");
        level.add("Medium");
        level.add("Hard");
        level.add("Inferno");

        return new ResponseEntity(level,HttpStatus.OK);
    }


}