package jdss.trivia.microservice.core.dto;

import java.util.List;

public class Response {
    List<QuestionIn> results;

    public List<QuestionIn> getResults() {
        return results;
    }

    public void setResults(List<QuestionIn> results) {
        this.results = results;
    }
}
