package com.example.tenchrio.flashcardz.domain;

import java.util.ArrayList;

public class Course extends abstractModel {
    private String naam;
    private String locatie;
    private String creator;
    private ArrayList<Chapter> myChapter;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public ArrayList<Chapter> getMyChapter() {
        return myChapter;
    }

    public void setMyChapter(ArrayList<Chapter> myChapter) {
        this.myChapter = myChapter;
    }

    public boolean addChapter(Chapter newChapter){
        if(newChapter instanceof Chapter){
            if(this.myChapter.add(newChapter)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean removeChapter(Chapter remChapter){
        if(remChapter instanceof Chapter){
            if(this.myChapter.remove(remChapter)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Course(String naam, String locatie) {
        this.naam = naam;
        this.locatie = locatie;
        this.myChapter = new ArrayList<Chapter>();
    }

    @Override
    public String toString() {
        return this.naam + " by " + this.creator;
    }
}
