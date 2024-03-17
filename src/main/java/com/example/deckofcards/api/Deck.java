package com.example.deckofcards.api;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
    private final List<Card> cards;
    
    // Constructor to initialize the deck
    public Deck() {
        cards = new ArrayList<>();
        createDeck();
    }

    // Method to create a standard 52-card deck
    private void createDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Method to deal a specified number of cards from the deck
    public List<Card> dealCards(int numCards) {
        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            if (!cards.isEmpty()) {
                dealtCards.add(cards.remove(0)); // Remove the top card from the deck
            } else {
                System.out.println("No more cards in the deck!");
                break;
            }
        }
        return dealtCards;
    }

    public Integer getHeartsCount() {
        Integer total = 0;
        for (Card card : cards) {
            if (card.getSuit() == Suit.HEARTS) {
                total += 1;
            }
        }
        return total;
    }

    public Integer getDiamondsCount() {
        Integer total = 0;
        for (Card card : cards) {
            if (card.getSuit() == Suit.DIAMONDS) {
                total += 1;
            }
        }
        return total;
    }

    public Integer getSpadesCount() {
        Integer total = 0;
        for (Card card : cards) {
            if (card.getSuit() == Suit.SPADES) {
                total += 1;
            }
        }
        return total;
    }

    public Integer getClubsCount() {
        Integer total = 0;
        for (Card card : cards) {
            if (card.getSuit() == Suit.CLUBS) {
                total += 1;
            }
        }
        return total;
    }
}
