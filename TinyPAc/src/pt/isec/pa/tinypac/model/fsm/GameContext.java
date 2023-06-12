package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.states.InitialState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GameContext implements Serializable {
    public static final long serialVersionUID = 1L;
    private IGameState state;
    private IGameState lastState;
    private final Game game;
    public GameContext(){
        game=new Game();
        state=new InitialState(this,game);
        lastState=state;
    }
    public void changeState(IGameState newState){
        this.lastState=state;
        this.state=newState;
    }
    public void Up(){state.Up();}
    public void Down(){state.Down();}
    public void Left(){state.Left();}
    public void Right(){state.Right();}
    public boolean pause(){return state.pause();}
    public void resume(){state.resume();}
    public boolean elementVulnerable(char c){return game.elementVulnerable(c);}
    public boolean lostGame(){return game.lostGame();}
    public EGameState getState(){
        if(state==null)return null;
        return state.getGameState();
    }
    public EGameState getLastState() {
        if (lastState == null) return null;
        return lastState.getGameState();
    }
    public  int getPoints(){return game.getPoints();}
    public int getLives(){return game.getLives();}
    public int getLevel(){return game.getLevel();}
    public char [][]getBoard(){return game.getBoard();}
    public int getDirection(){return game.getDirection();}
    public boolean evolve(double time){
        if(state.evolve(time)){
            game.moveElements(time);
        }
        return game.getLives()>0;
    }
}
