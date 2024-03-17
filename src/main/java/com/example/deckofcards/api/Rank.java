package com.example.deckofcards.api;

public enum Rank {
    ACE("ACE"), 
    TWO("TWO"), 
    THREE("THREE"), 
    FOUR("FOUR"), 
    FIVE("FIVE"), 
    SIX("SIX"), 
    SEVEN("SEVEN"), 
    EIGHT("EIGHT"), 
    NINE("NINE"), 
    TEN("TEN"), 
    JACK("JACK"), 
    QUEEN("QUEEN"), 
    KING("KING");
    
    public final String rank;

    Rank(String rank) {
        this.rank = rank;
    }
}