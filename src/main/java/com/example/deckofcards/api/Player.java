package com.example.deckofcards.api;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private List<Card> hand;
    private Integer handValue;

    public Player(String id) {
        this.id = id;
        this.hand = new ArrayList<>();
        this.handValue = 0;
    }

    // Getter method for id
    public String getId() {
        return id;
    }

    // Getter method for player hand
    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }

    // Getter method for player hand value
    public Integer getHandValue() {
        return handValue;
    }

    // Method for adding card to player hand
    public void addCard(Card card) {
        hand.add(card);
        handValue += card.getRank().ordinal() + 1;
    }
    @Override
    public int hashCode() 
    {
       return (int)id.hashCode();
    }
  
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        return id.equals(other.id);
    }
}