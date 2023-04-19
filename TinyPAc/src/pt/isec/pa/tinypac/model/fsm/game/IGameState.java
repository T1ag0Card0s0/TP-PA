package pt.isec.pa.tinypac.model.fsm.game;

public interface IGameState {
    EGameState getGameState();
     boolean DirectionKeyIsPressed(String keyPressed);
     boolean LostCurrentLevel();
     boolean PauseGame();
     boolean UnPauseGame();
     boolean WinLevel();
     boolean WinGame();
}
