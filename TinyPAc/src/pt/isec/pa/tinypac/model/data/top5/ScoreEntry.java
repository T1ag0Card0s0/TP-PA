package pt.isec.pa.tinypac.model.data.top5;

import java.io.Serializable;

public class ScoreEntry implements Serializable {
    private String name;
    private int score;

    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " : " + score;
    }
}