package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.IGameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;

public class GameManager {
    private GameContext fsm;
    PropertyChangeSupport pcs;
    public GameManager(){
        this.fsm = null;
        pcs = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void changeState(IGameState newState){fsm.changeState(newState);}
    public void Up(){fsm.Up();}
    public void Down(){fsm.Down();}
    public void Left(){fsm.Left();}
    public void Right(){fsm.Right();}
    public void pause(){fsm.pause();}
    public void start(){fsm = new GameContext(); pcs.firePropertyChange(null,null,null);}
    public boolean elementVulnerable(char c){return fsm.elementVulnerable(c);}
    public EGameState getState(){return fsm.getState();}
    public EGameState getLastState() {
        return fsm.getLastState();
    }
    public  int getPoints(){return fsm.getPoints();}
    public int getLives(){return fsm.getLives();}
    public int getLevel(){return fsm.getLevel();}
    public char [][]getBoard(){return fsm.getBoard();}
    public int getDirection(){return fsm.getDirection();}
    public boolean FSM_Is_Created(){return fsm!=null;}

    public boolean evolve(double time){
        if(fsm==null)return false;
        pcs.firePropertyChange(null,null,null);
        return fsm.evolve(time);
    }
    public boolean save(File file){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(fsm);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean load(File file){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            fsm = (GameContext) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }
}
