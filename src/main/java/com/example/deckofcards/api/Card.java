package com.example.deckofcards.api;

public class Card {
    private Suit suit;
    private Rank rank;

    // Constructor for Card class
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getter method for suit
    public Suit getSuit() {
        return suit;
    }

    // Getter method for rank
    public Rank getRank() {
        return rank;
    }

    // Override toString method to represent card as a string
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
