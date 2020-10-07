package com.company.gamelogic;

public class RoundResult {

    private Person player;

    private int handScore;

    private boolean playerWon;

    public RoundResult(Person player, boolean playerWin, int handScore){
        this.player = player;
        this.playerWon = playerWin;
        this.handScore = handScore;
    }

    public Person getPlayer() {
        return player;
    }

    public void setPlayer(Person player) {
        this.player = player;
    }

    public boolean playerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    public int getHandScore() {
        return handScore;
    }

    public void setHandScore(int handScore) {
        this.handScore = handScore;
    }
}
