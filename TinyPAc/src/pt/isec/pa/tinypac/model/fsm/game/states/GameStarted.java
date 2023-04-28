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
    public boolean WinLevel() {
        if(gameManager.thereIsFood()){
            if(!gameManager.LastLevel()) {
                gameManager.changelevel();
                changeState(new InitialState(context, gameManager));
            }else{
                changeState(new GameWin(context,gameManager));
            }
            return true;
        }
        if(gameManager.pacManWasEaten()){
            if(gameManager.getPacManLives()>0) {
                gameManager.initGame();
                changeState(new InitialState(context, gameManager));
            }else{
                changeState(new GameOver(context,gameManager));
            }
            return false;
        }
        return true;
    }
    @Override
    public boolean beVulnerable(long interval) {
        if(gameManager.pacManHasPower()){
            changeState(new Vulnerable(context,gameManager));
            return true;
        }
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.GAME_STARTED;
    }

}
