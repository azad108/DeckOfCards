package com.example.deckofcards.api;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Iterator;

public class Game {
    private List<Deck> gameDeck = new ArrayList<>();
    private final Object gameDeckLock = new Object();
    private final  Map<Player, UUID> players;
    private final List<Object> playerLocks;
    
    // Costructor to initialize the Game
    public Game() {
        this.players = new HashMap<>();
        this.playerLocks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            playerLocks.add(new Object());
        }
    }

    // Method to add a new player to the game
    public void addPlayer(Player player) {
        int lockIndex = Math.abs(player.hashCode() % playerLocks.size());
        synchronized (playerLocks.get(lockIndex)) {
            players.put(player, UUID.randomUUID());
        }
    }

    // Method to remove a player from a game
    public void removePlayer(Player player) {
        int lockIndex = Math.abs(player.hashCode() % playerLocks.size());
        synchronized (playerLocks.get(lockIndex)) {
            players.remove(player);
        }
    }

    // Method to get the list of cards a player holds
    public List<Card> getCardsForPlayer(Player player) {
        int lockIndex = Math.abs(player.hashCode() % playerLocks.size());
        List<Card> playerCards = null;
        synchronized (playerLocks.get(lockIndex)) {
            Iterator<Map.Entry<Player, UUID>> iterator = players.entrySet().iterator();
            
            while (iterator.hasNext()) {
                Map.Entry<Player, UUID> entry = iterator.next();
                Player key = entry.getKey();
                if (key.equals(player)) {
                    playerCards = key.getHand();
                    break;
                }
            }
        }
        return playerCards;
    }

    // Method to add a new deck to the gameDeck
    public void addDeck(Deck deck) {
        synchronized (gameDeckLock) {
            gameDeck.add(deck);
        }
    }

    // Method to get the remaining cards in the gameDeck per suit
    public SuitValues getRemainder() {
        Integer hearts = 0, diamonds = 0, spades = 0, clubs = 0;
        synchronized (gameDeckLock) {
            for (Deck deck : gameDeck) {
                hearts += deck.getHeartsCount();
                diamonds += deck.getDiamondsCount();
                spades += deck.getSpadesCount();
                clubs += deck.getClubsCount();
            }
        }
        return new SuitValues(hearts, spades, diamonds, clubs);
    }

    // Method to deal a number of cards from the gameDeck to a player
    public Player dealCardsToPlayer(Player player, Integer number) {
        List<Card> dealtCards = new ArrayList<>();
        synchronized (gameDeckLock) {
            for (Deck deck : gameDeck) {
                if (number == 0) break;
                List<Card> curCards = deck.dealCards(number);
                dealtCards.addAll(curCards);
                if (curCards.size() <= number) number -= curCards.size();
            }
        }
        int lockIndex = Math.abs(player.hashCode() % playerLocks.size());
        Player thisPlayer = null;
        synchronized (playerLocks.get(lockIndex)) {
            Iterator<Map.Entry<Player, UUID>> iterator = players.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Player, UUID> entry = iterator.next();
                Player key = entry.getKey();
                if (key.equals(player)) {
                    for (Card card : dealtCards) {
                        key.addCard(card);
                    }
                    thisPlayer = key;
                }
            }
        }
        return thisPlayer;
    }

    // Method to get list of current Player in the game including their cards and their total hand value
    public List<Player> getPlayers() {
        Player player = players.entrySet().iterator().next().getKey();
        int lockIndex = Math.abs(player.hashCode() % playerLocks.size());
        synchronized (playerLocks.get(lockIndex)) {
            List<Player> playerList = new ArrayList<>();
            players.forEach((key, value) -> {
                playerList.add(key);
            });
            return playerList.stream()
                .sorted((p1,p2) -> p2.getHandValue() - p1.getHandValue())
                .collect(Collectors.toList());
        }
    }
}
