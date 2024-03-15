package com.example.deckofcards.api;
import org.springframework.stereotype.Component;

@Component
public class Player {
    private String id;

    public Player(String id) {
        this.id = id;
    }

    // Getter method for id
    public String getId() {
        return id;
    }
}
