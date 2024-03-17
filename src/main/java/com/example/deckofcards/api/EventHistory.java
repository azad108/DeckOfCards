package com.example.deckofcards.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
public class EventHistory {
    private List<Event> history = new ArrayList<>();

    public void addEvent(Event event) {
        history.add(event);
    }

    public List<Event> getHistory() {
        // Sort events by timestamp
        history.sort(Comparator.comparing(Event::getTimestamp));
        return history;
    }
}