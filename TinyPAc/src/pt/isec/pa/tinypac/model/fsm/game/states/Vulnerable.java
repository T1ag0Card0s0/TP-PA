package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class Vulnerable extends GameStateAdapter {
    public Vulnerable(GameContext context, Game game) {
        super(context, game);
    }
    @Override
    public boolean WinLevel() {
        if(game.thereIsFood()){
            if(game.LastLevel()){
                game.changelevel();
                changeState(new InitialState(context,game));
            }else{
                changeState(new GameWin(context,game));
            }
        }
        return true;
    }
    @Override
    public boolean beVulnerable(long interval) {
        if(game.endOfVulnerability(interval)){
            changeState(new GameStarted(context,game));
            return false;
        }
        return true;
    }

    @Override
    public boolean KeyIsPressed(String keyPressed){
        if(keyPressed.equals(" "))changeState(new GamePaused(context,game));

        if(keyPressed.equals("Escape"))changeState(new GameOver(context,game));

        game.setPacmanNextDirection(keyPressed);
        return true;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.VULNERABLE;
    }
}
