package Game_logic;

import java.awt.*;

public class Player implements java.io.Serializable {
    private String name;
    private int currentScore;
    private Color color;
    private boolean turn;//دور اللاعب

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        currentScore =0;
        turn=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
