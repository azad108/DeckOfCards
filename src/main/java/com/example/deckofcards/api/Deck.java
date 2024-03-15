package com.example.deckofcards.api;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Deck {
    private List<Card> cards;

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


}
