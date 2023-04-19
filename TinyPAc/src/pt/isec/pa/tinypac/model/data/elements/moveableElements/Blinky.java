package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;

public class Blinky extends MoveableElement implements IGameEngineEvolve{
    private final Random rnd;
    private boolean inCave;
    public Blinky(int x, int y, Maze maze) {
        super(x,y, maze,TextColor.ANSI.RED);
        this.inCave=true;
        this.rnd=new Random();
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
        System.out.println("Passei aqui 1");
        if(!super.move()){
            int newDirection;
            do{
                newDirection=rnd.nextInt(4);
            }while (getNeighboorValue(newDirection));
            if(getMazeElement(getX(),getY()).getSymbol()=='y')inCave=true;
            System.out.println("Passei aqui 2");
            return false;
        }
        System.out.println("Passei aqui 3");
        return true;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        System.out.println("Passei no evolve Blinky");
        move();
    }
}
