package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;

public class Clyde extends MoveableElement {
    private boolean inCave;
    public Clyde(int x, int y, Maze maze) {
        super(x, y,maze,new TextColor.RGB(255,184,82));
    }
    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }
    @Override
    public boolean getInCave(){
        return inCave;
    }
  /*  @Override
    public boolean move(){
        if(!super.move()){
            Random rnd=new Random();
            int newDirection;
            do{
                newDirection=rnd.nextInt(4);
            }while (getNeighboorValue(newDirection));
            return false;
        }
        return true;
    }*/

}
