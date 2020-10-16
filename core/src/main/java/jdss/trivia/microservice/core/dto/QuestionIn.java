package jdss.trivia.microservice.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class QuestionIn {
    String question;

    String correct_answer;

    String []incorrect_answers;

    String category;

    String type;

    String []Options;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correctAnsware) {
        this.correct_answer = correctAnsware;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(String []incorrectAnswers) {
        this.incorrect_answers = incorrectAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
