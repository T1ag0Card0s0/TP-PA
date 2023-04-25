package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GameStarted extends GameStateAdapter {
    public GameStarted(GameContext context, GameManager gameManager) {
        super(context, gameManager);
        context.startGameEngine(100);
    }
    @Override
    public boolean KeyIsPressed(String keyPressed){
        if(keyPressed.equals(" "))changeState(new GamePaused(context,gameManager));

        if(keyPressed.equals("Escape"))changeState(new GameOver(context,gameManager));

        gameManager.setPacmanNextDirection(keyPressed);
        return true;
    }
    @Override
    public boolean LostCurrentLevel() {
        changeState(new InitialState(context,gameManager));
        return false;
    }
    @Override
    public boolean WinLevel() {
        if(gameManager.WinLevel()&&!gameManager.LastLevel()){
            changeState(new InitialState(context,gameManager));
        }else if(gameManager.WinLevel()&&gameManager.LastLevel()){
            changeState(new GameWin(context,gameManager));
        }
        return true;
    }

    @Override
    public boolean beVulnerable(long interval) {
        if(gameManager.pacManHasPower()){
            changeState(EGameState.VULNERABLE.createState(context,gameManager));
            return true;
        }
        return false;
    }

    @Override
    public boolean WinGame() {
        changeState(new GameWin(context,gameManager));
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.GAME_STARTED;
    }

}
