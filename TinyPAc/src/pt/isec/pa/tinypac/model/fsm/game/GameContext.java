package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.GameEngineState;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.Ghost;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.game.states.InitialState;

import java.util.ArrayList;

public class GameContext{
    IGameEngine gameEngine;
    IGameState state;
    Game game;
    public GameContext(){
        game=new Game();
        state=new InitialState(this,game);
        gameEngine=new GameEngine();
    }
    void changeState(IGameState newState){this.state=newState;}
    public EGameState getState(){
        if(state==null)return null;
        return state.getGameState();
    }
    public IMazeElement getMazeElement(int x, int y){return game.getMazeElement(x,y);}
    public ArrayList<MoveableElement> getMoveableElements(){
        ArrayList<MoveableElement>tmp=new ArrayList<>(game.getGhosts());
        tmp.add(game.getPacMan());
        return tmp;
    }
    public MoveableElement getPacMan(){return game.getPacMan();}
    public int getPacManPoints(){return game.getPoints();}
    public int getLevel(){return game.getCurrentLevel();}
    public int getBoardHeight(){return game.getBoardHeight();}
    public int getBoardWidth(){return game.getBoardWidth();}
    public int getPacManLives(){return game.getPacManLives();}
    public boolean pacManHasPower(){return game.pacManHasPower();}
    public boolean allGhostsReachedCave(){return game.allGhostsInCave();}

    public void registEngineClients(){
        for(Ghost newClient:game.getGhosts()) {
            newClient.setStartTime();
            gameEngine.registerClient((IGameEngineEvolve) newClient);
        }
        gameEngine.registerClient((IGameEngineEvolve) game.getPacMan());
    }
    public void unregistEngineClient(IGameEngineEvolve client){gameEngine.unregisterClient(client);}
    public void startGameEngine(long interval){gameEngine.start(interval);}
    public GameEngineState getGameEngineState(){return gameEngine.getCurrentState();}
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
}
