package pt.isec.pa.tinypac.model.fsm.game;

public interface IGameState {
    EGameState getGameState();
     boolean KeyIsPressed(String keyPressed);
     boolean LostCurrentLevel();
     boolean WinLevel();
     boolean beVulnerable(long currentTime);
     boolean WinGame();
     boolean waitForTheEnd();

}
