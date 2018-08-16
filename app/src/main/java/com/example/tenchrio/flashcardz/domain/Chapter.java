package com.example.tenchrio.flashcardz.domain;

import java.util.ArrayList;

public class Chapter extends abstractModel {
    private String naam;
    private ArrayList<FlashCard> myFlashCards;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public ArrayList<FlashCard> getMyFlashCards() {
        return myFlashCards;
    }

    public void setMyFlashCards(ArrayList<FlashCard> myFlashCards) {
        this.myFlashCards = myFlashCards;
    }

    public boolean addFlashCard(FlashCard newFlash){
        if(newFlash instanceof FlashCard){
            if(this.myFlashCards.add(newFlash)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean removeFlashCard(FlashCard remFlash){
        if(remFlash instanceof FlashCard){
            if(this.myFlashCards.remove(remFlash)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Chapter(String naam) {
        this.naam = naam;
        this.myFlashCards = new ArrayList<FlashCard>();
    }
}
