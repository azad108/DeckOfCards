package com.example.deckofcards.api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private Game game;
    EventPublisher publisher = new EventPublisher();
    EventHistory history = new EventHistory();

    public GameController() {
        // Register event listener
        publisher.addEventListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                history.addEvent(event);
            }
        });
    }
    @PostMapping(value="/create", produces="application/json")
    public String createGame() {
        this.game = new Game();
        Event e = new Event("Create", "Game", LocalDateTime.now());
        publisher.publishEvent(e);
        return "Game Created";
    }

    @DeleteMapping(value="/delete", produces="application/json")
    public String deleteGame() {
        game = null;
        Event e = new Event("Delete", "Game", LocalDateTime.now());
        publisher.publishEvent(e);
        return "Game Deleted";
    }

    @PostMapping(value="/deck", produces="application/json")
    public String addDeck() {
        Deck deck = new Deck();
        game.addDeck(deck);
        Event e = new Event("Add", "Deck", LocalDateTime.now());
        publisher.publishEvent(e);
        return "New Deck Added";
    }

    @PostMapping(value="/player/{id}", produces="application/json")
    public Player addPlayer(@PathVariable String id) {
        Player player = new Player(id);
        game.addPlayer(player);
        Event e = new Event("Add", String.format("Player with name %s", id), LocalDateTime.now());
        publisher.publishEvent(e);
        return player;
    }

    @DeleteMapping(value="/player/{id}", produces="application/json")
    public String removePlayer(@PathVariable String id) {
        Player player = new Player(id);
        game.removePlayer(player);
        Event e = new Event("Remove", String.format("Player with name %s", id), LocalDateTime.now());
        publisher.publishEvent(e);
        return String.format("Player %s removed from game", id);
    }

    @GetMapping(value="/cards", produces="application/json")
    public SuitValues getRemainder() {
        return game.getRemainder();
    }

    @GetMapping(value="/player/list", produces="application/json")
    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    @GetMapping(value="/player/{id}/cards", produces="application/json")
    public List<Card> getCardsForPlayer(@PathVariable String id) {
        Player player = new Player(id);
        return game.getCardsForPlayer(player);
    }

    @PostMapping(value="/player/{id}/deal/{number}", produces="application/json")
    public Player dealCards(@PathVariable String id, @PathVariable Integer number) {
        Player player = new Player(id);
        Event e = new Event("Deal", String.format("%d cards to %s", number, id), LocalDateTime.now());
        publisher.publishEvent(e);
        return game.dealCardsToPlayer(player, number);
    }

    @GetMapping(value="/events", produces="application/json")
    public List<Event> getEventHistory() {
        // Return event history
        return history.getHistory();
    }
}