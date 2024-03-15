package com.example.deckofcards.api;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Game {
    private List<Deck> gameDeck;
    private List<Player> players;
    
    // Costructor to initialize the Game
    public Game() {
        this.gameDeck = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addDeck(Deck deck) {
        gameDeck.add(deck);
    }
}
