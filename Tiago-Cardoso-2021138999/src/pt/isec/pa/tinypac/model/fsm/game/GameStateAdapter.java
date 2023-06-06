package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.model.data.Game;

public class GameStateAdapter implements IGameState{
    protected Game game;
     protected GameContext context;
    public GameStateAdapter(GameContext context, Game game){
        this.context=context;
        this.game=game;
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
    public boolean WinLevel() {
        return false;
    }
    @Override
    public boolean beVulnerable(long currentTime) {return false;}

}
