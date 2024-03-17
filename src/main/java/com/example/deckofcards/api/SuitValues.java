package com.example.deckofcards.api;

public class SuitValues {
    private Integer hearts;
    private Integer spades;
    private Integer diamonds;
    private Integer clubs;

    public SuitValues(Integer hearts, Integer spades, Integer diamonds, Integer clubs) {
        this.hearts = hearts;
        this.spades = spades;
        this.diamonds = diamonds;
        this.clubs = clubs;
    }

    public Integer getHears() {
        return hearts;
    }

    public Integer getSpades () {
        return spades;
    }

    public Integer getDiamonds () {
        return diamonds;
    }

    public Integer getClubs () {
        return clubs;
    }
}
