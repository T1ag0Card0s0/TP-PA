package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GameStarted extends GameStateAdapter {
    public GameStarted(GameContext context, Game game) {
        super(context, game);
        context.startGameEngine(100);
    }
    @Override
    public boolean KeyIsPressed(String keyPressed){
        if(keyPressed.equals(" "))changeState(new GamePaused(context,game));
        game.setPacmanNextDirection(keyPressed);
        return true;
    }
    @Override
    public boolean WinLevel() {
        if(game.thereIsNoFood()){
            if(!game.LastLevel()) {
                game.changelevel();
                changeState(new InitialState(context, game));
            }else{
                changeState(new GameWin(context,game));
            }
            return true;
        }
        if(game.pacManWasEaten()){
            if(game.getPacManLives()>0) {
                game.initElementsPosition();
                changeState(new InitialState(context, game));
            }else{
                changeState(new GameOver(context,game));
            }
            return false;
        }
        return true;
    }
    @Override
    public boolean beVulnerable(long interval) {
        if(game.pacManHasPower()){
            game.setVulnerable(true);
            changeState(new Vulnerable(context,game));
            return true;
        }
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.GAME_STARTED;
    }

}
