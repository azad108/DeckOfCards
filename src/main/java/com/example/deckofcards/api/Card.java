package com.example.deckofcards.api;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getter methods for suit and rank
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // Override toString method to represent card as a string
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
