package jdss.trivia.microservice.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class QuestionOut {
    String question;

    String category;

    String type;

    String []options;

    public QuestionOut(QuestionIn question) {
        this.question = question.question;
        this.category = question.category;
        this.type = question.type;
        this.options = Arrays.copyOf(question.incorrect_answers,question.incorrect_answers.length+1);
        options[options.length-1] = question.correct_answer;
    }

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


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
