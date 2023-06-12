package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.data.top5.Top5Players;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;

public class GameManager {
    public static final String FILE_GAME = "files/game.dat";
    private static final String FILE_TOP5 = "files/top5.dat";
    private GameContext fsm;
    private Top5Players top5Players;
    PropertyChangeSupport pcs;
    public GameManager(){
        this.fsm = null;
        this.top5Players=null;
        pcs = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void Up(){fsm.Up();}
    public void Down(){fsm.Down();}
    public void Left(){fsm.Left();}
    public void Right(){fsm.Right();}
    public boolean pause(){
        if(fsm.pause()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }
    public void resume(){fsm.resume();pcs.firePropertyChange(null,null,null);}
    public void exit(){fsm=null;pcs.firePropertyChange(null,null,null);}
    public void start(){fsm = new GameContext(); pcs.firePropertyChange(null,null,null);}
    public boolean lostGame(){
        if(fsm == null)return false;
        return fsm.lostGame();
    }
    public boolean elementVulnerable(char c){return fsm.elementVulnerable(c);}
    public EGameState getState(){if(fsm==null)return null; return fsm.getState();}
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
    public boolean save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_GAME))){
            oos.writeObject(fsm);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean load(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_GAME))){
            fsm = (GameContext) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }
    public void addTop5(String name){
        if(top5Players==null)loadTop5();
        top5Players.addPlayer(name,getPoints());
    }
    public List<String> getTop5(){return top5Players.getTop5();}
    public boolean top5IsLoaded(){return top5Players!=null;}
    public boolean loadTop5(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_TOP5))){
            top5Players = (Top5Players) ois.readObject();
        }catch (Exception e){
            top5Players=new Top5Players();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }
    public boolean saveTop5(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_TOP5))){
            oos.writeObject(top5Players);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }

    public void closeTop5(){top5Players=null;        pcs.firePropertyChange(null,null,null);}
    public boolean existSavedGame() {
        File arquivo = new File(FILE_GAME);
        return arquivo.exists();
    }
}
