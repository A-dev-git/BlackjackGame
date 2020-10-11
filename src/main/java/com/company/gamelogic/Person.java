package com.company.gamelogic;

public class Person {

    private String name;

    private Hand hand;

    public Person(String name,Hand hand){
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }
}
