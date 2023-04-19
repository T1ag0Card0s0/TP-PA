package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;

public class Inky extends Ghost implements IGameEngineEvolve {

    public Inky(int x, int y, Maze maze) {
        super(x, y,maze,TextColor.ANSI.CYAN);
    }
    @Override
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
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        move();
    }
}
