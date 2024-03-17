package com.example.deckofcards.api;

import java.time.LocalDateTime;

public class Event {
    private String action;
    private String details;
    private LocalDateTime timestamp;

    public Event(String action, String details, LocalDateTime timestamp) {
        this.action = action;
        this.details = details;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }
}
