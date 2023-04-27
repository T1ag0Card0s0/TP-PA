package pt.isec.pa.tinypac.model.fsm.game;

public interface IGameState {
    EGameState getGameState();
     boolean KeyIsPressed(String keyPressed);
     boolean WinLevel();
     boolean beVulnerable(long currentTime);
}
