package com.company.model;

import java.io.Serializable;
import java.util.HashMap;

public class Question implements Serializable {
    private String title;
    private HashMap<String, Boolean> answerList;

    public Question(String title, HashMap<String, Boolean> answerList) {
        this.title = title;
        this.answerList = answerList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Boolean> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(HashMap<String, Boolean> answerList) {
        this.answerList = answerList;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCorrect(String answer) {
        return answerList.get(answer);
    }
}
