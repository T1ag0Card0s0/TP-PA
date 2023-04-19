package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.Maze;
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
        if(keyPressed.equals(" ")){
            context.pauseGameEngine();
            changeState(new GamePaused(context,game));
        }
        if(keyPressed.equals("Escape"))changeState(new GameOver(context,game));
        game.setPacmanNextDirection(keyPressed);
        return true;
    }
    @Override
    public boolean LostCurrentLevel() {
        changeState(new InitialState(context,game));
        return false;
    }
    @Override
    public boolean WinLevel() {
        if(game.thereIsFood()) return false;
        if(game.getCurrentLevel()<MAX_LEVEL){
            game.NextLevel();
            changeState(new InitialState(context,game));
        }else{
            changeState(new GameWin(context,game));
        }

        return false;
    }
    @Override
    public boolean WinGame() {
        changeState(new GameWin(context,game));
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.GAME_STARTED;
    }

}
