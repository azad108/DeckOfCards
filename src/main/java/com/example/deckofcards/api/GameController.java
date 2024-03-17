package com.example.deckofcards.api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private Game game;

    @PostMapping(value="/create", produces="application/json")
    public String createGame() {
        this.game = new Game();
        return "Game Created";
    }

    @DeleteMapping(value="/delete", produces="application/json")
    public String deleteGame() {
        game = null;
        return "Game Deleted";
    }

    @PostMapping(value="/deck", produces="application/json")
    public String addDeck() {
        Deck deck = new Deck();
        game.addDeck(deck);
        return "New Deck Added";
    }

    @PostMapping(value="/player/{id}", produces="application/json")
    public Player addPlayer(@PathVariable String id) {
        Player player = new Player(id);
        game.addPlayer(player);
        return player;
    }

    @DeleteMapping(value="/player/{id}", produces="application/json")
    public String deleteUser(@PathVariable String id) {
        Player player = new Player(id);
        game.removePlayer(player);
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
        return game.dealCardsToPlayer(player, number);
    }
}