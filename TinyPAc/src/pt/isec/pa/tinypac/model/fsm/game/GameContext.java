package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.game.states.InitialState;

import java.util.ArrayList;

public class GameContext implements IGameEngineEvolve{
    IGameState state;
    Game game;
    public GameContext(){
        game=new Game();
        state=new InitialState(this,game);
    }
    void changeState(IGameState newState){this.state=newState;}
    public EGameState getState(){
        if(state==null)return null;
        return state.getGameState();
    }
    public IMazeElement getMazeElement(int x, int y){return game.getMazeElement(x,y);}
    public ArrayList<MoveableElement> getMoveableElements(){return game.getMoveableElements();}
    public MoveableElement getPacMan(){return game.getPacMan();}
    public int getPacManPoints(){return game.getPoints();}
    public int getLevel(){return game.getCurrentLevel();}
    public int getBoardHeight(){return game.getBoardHeight();}
    public int getBoardWidth(){return game.getBoardWidth();}
    public int getPacManLives(){return game.getPacManLives();}
    public boolean pacManHasPower(){return game.pacManHasPower();}
    public boolean allGhostsReachedCave(){return game.allGhostsInCave();}

    public boolean DirectionKeyIsPressed(String s){//StartPlaying||Playing
        return state.DirectionKeyIsPressed(s);
    }
    public boolean LostCurrentLevel(){//RestartLevel
        return state.LostCurrentLevel();
    }
    public boolean PauseGame(){//PauseGame
        return state.PauseGame();
    }
    public boolean UnPauseGame(){//NextLevel
        return state.UnPauseGame();
    }
    public boolean WinLevel(){//ReturnToLevel1
        return state.WinLevel();
    }
    public boolean WinGame(){
        return state.WinGame();
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        switch (state.getGameState()){
            case GAME_STARTED -> {
                for(MoveableElement element: getMoveableElements()){
                    element.move();
                }
            }
        }
    }
}
