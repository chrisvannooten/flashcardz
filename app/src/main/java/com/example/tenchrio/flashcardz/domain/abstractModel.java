package com.example.tenchrio.flashcardz.domain;

public abstract class abstractModel {

    private long id;

    abstractModel(){ this.id = -1; }

    public long getId() { return id;}

    public void setId(long id) { this.id = id; }
}
