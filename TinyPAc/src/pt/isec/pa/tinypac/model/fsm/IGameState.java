package pt.isec.pa.tinypac.model.fsm;

public interface IGameState {
    EGameState getGameState();
    void Up();
    void Down();
    void Left();
    void Right();
    boolean pause();
    boolean resume();
    boolean evolve(double time);
}
