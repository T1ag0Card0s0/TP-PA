package pt.isec.pa.tinypac.model.fsm;

import java.io.Serializable;

public interface IGameState extends Serializable {
    EGameState getGameState();
    void Up();
    void Down();
    void Left();
    void Right();
    boolean pause();
    boolean resume();
    boolean evolve(double time);
}
