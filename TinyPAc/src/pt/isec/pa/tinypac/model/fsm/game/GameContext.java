package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.game.states.InitialState;

public class GameContext implements IGameEngineEvolve{
    private final IGameEngine gameEngine;
    private IGameState state;
    private final Game game;

    public GameContext(){
        game=new Game();
        state=new InitialState(this,game);
        this.gameEngine=new GameEngine();
    }
    void changeState(IGameState newState){this.state=newState;}
    public EGameState getState(){
        if(state==null)return null;
        return state.getGameState();
    }
    public int getPacManPoints(){return game.getPoints();}
    public int getLevel(){return game.getCurrentLevel();}
    public int getBoardHeight(){return game.getBoardHeight();}
    public int getBoardWidth(){return game.getBoardWidth();}
   public int getPacManLives(){return game.getPacManLives();}
    public IMazeElement getMazeElement(int x,int y){return game.getMazeElement(x,y);}
    public MoveableElement[] getMoveableElements(){return game.getMoveableElements();}
    public void registEngineClient(IGameEngineEvolve newClient){gameEngine.registerClient(newClient);}
    public void startGameEngine(long interval){
        game.setGameEngineInterval(interval);
        gameEngine.registerClient(this);
        gameEngine.start(interval);}
    public void stopGame(){gameEngine.stop();gameEngine.waitForTheEnd();}
    public void KeyIsPressed(String s){//StartPlaying||Playing
        state.KeyIsPressed(s);
    }
    public void WinLevel(){state.WinLevel();}

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        switch (state.getGameState()){
            case GAME_STARTED -> {
                game.evolve();
                if(state.beVulnerable(gameEngine.getInterval())){
                    game.setVulnerable(true);
                }
                WinLevel();
            }
            case VULNERABLE -> {
                game.evolve();
                if(!state.beVulnerable(gameEngine.getInterval())){
                    game.setVulnerable(false);
                }
                WinLevel();
            }
        }
    }
    @Override
    public String toString() {
        return game+"\nEstado do jogo: "+ state;
    }
}
