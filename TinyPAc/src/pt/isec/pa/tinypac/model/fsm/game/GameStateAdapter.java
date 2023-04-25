package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.model.data.GameManager;

public class GameStateAdapter implements IGameState{
    protected GameManager gameManager;
     protected GameContext context;
    public GameStateAdapter(GameContext context, GameManager game){
        this.context=context;
        this.gameManager=game;
    }
     protected void changeState(IGameState newState){
        context.changeState(newState);
    }
    @Override
    public EGameState getGameState() {
        return null;
    }

    @Override
    public boolean KeyIsPressed(String keyPressed) {
        return false;
    }

    @Override
    public boolean LostCurrentLevel() {
        return false;
    }
    @Override
    public boolean WinLevel() {
        return false;
    }
    @Override
    public boolean beVulnerable(long currentTime) {return false;}
    @Override
    public boolean WinGame() {
        return false;
    }
}
