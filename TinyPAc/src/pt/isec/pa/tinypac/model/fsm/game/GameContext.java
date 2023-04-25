package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.game.states.InitialState;

public class GameContext implements IGameEngineEvolve{
    private final IGameEngine gameEngine;
    private IGameState state;
    private final GameManager gameManager;

    public GameContext(){
        gameManager=new GameManager();
        state=new InitialState(this,gameManager);
        this.gameEngine=new GameEngine();
    }
    void changeState(IGameState newState){this.state=newState;}
    public EGameState getState(){
        if(state==null)return null;
        return state.getGameState();
    }
    public int getPacManPoints(){return gameManager.getPoints();}
    public int getLevel(){return gameManager.getCurrentLevel();}
    public int getBoardHeight(){return gameManager.getBoardHeight();}
    public int getBoardWidth(){return gameManager.getBoardWidth();}
   public int getPacManLives(){return gameManager.getPacManLives();}
    public char [][]getMazeSymbols(){return  gameManager.getMazeSymbols();}
    public MoveableElement[] getMoveableElements(){return gameManager.getMoveableElements();}
    public void registEngineClients(){
        gameEngine.registerClient(gameManager);
    }
    public void startGameEngine(long interval){
        gameManager.setGameEngineInterval(interval);
        gameEngine.registerClient(this);
        gameEngine.registerClient(gameManager);
        gameEngine.start(interval);}
    public void waitForTheEnd(){gameEngine.waitForTheEnd();}
    public void pauseGameEngine(){gameEngine.pause();}
    public void resumeGameEngine(){gameEngine.resume();}

    public void KeyIsPressed(String s){//StartPlaying||Playing
        state.KeyIsPressed(s);
    }
    public boolean LostCurrentLevel(){//RestartLevel
        return state.LostCurrentLevel();
    }
    public boolean WinLevel(){
        return state.WinLevel();
    }
    public boolean WinGame(){
        return state.WinGame();
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        switch (state.getGameState()){
            case GAME_STARTED -> {
                if(state.beVulnerable(gameEngine.getInterval())){
                    gameManager.setVulnerable(true);
                }
            }
            case VULNERABLE -> {
                if(!state.beVulnerable(gameEngine.getInterval())){
                    gameManager.setVulnerable(false);
                }
            }

        }
    }
    @Override
    public String toString() {
        return gameManager+"\nEstado do jogo: "+ state;
    }
}
