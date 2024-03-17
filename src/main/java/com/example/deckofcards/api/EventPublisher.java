package com.example.deckofcards.api;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private List<EventListener> listeners = new ArrayList<>();

    public void addEventListener(EventListener listener) {
        listeners.add(listener);
    }

    public void publishEvent(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
