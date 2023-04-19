package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;

public class Blinky extends MoveableElement {
    private boolean inCave;
    public Blinky(int x, int y, Maze maze) {
        super(x,y, maze,TextColor.ANSI.RED);
        this.inCave=true;
    }

    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }
    @Override
    public boolean getInCave(){
        return inCave;
    }

    @Override
    public boolean move(){
        if(!super.move()){
            Random rnd=new Random();
            int newDirection;
            do{
                newDirection=rnd.nextInt(4);
            }while (getNeighboorValue(newDirection));
            if(getMazeElement(getX(),getY()).getSymbol()=='y')inCave=true;
            return false;
        }
        return true;
    }
}
