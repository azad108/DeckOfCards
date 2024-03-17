package com.example.deckofcards.api;

public enum Suit {
    CLUBS("CLUBS"), 
    DIAMONDS("DIAMONDS"), 
    HEARTS("HEARTS"), 
    SPADES("SPADES");

    public final String suit;

    Suit(String suit) {
        this.suit = suit;
    }
}