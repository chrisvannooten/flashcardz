package com.example.tenchrio.flashcardz.domain;

public class FlashCard extends abstractModel {
    private String question;
    private String answer;
    private String photoUrl;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
