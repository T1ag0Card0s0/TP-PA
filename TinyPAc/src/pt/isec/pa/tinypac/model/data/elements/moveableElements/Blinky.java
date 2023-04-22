package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.ansi.TelnetTerminalServer;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;

public class Blinky extends Ghost implements IGameEngineEvolve{

    private final Random rnd;
    public Blinky(int x, int y, Maze maze) {
        super(x,y, maze,TextColor.ANSI.RED);

        this.rnd=new Random();
    }

    @Override
    public boolean move(){
        if(!super.move()){
            int newDirection;
            do{
                newDirection=rnd.nextInt(4);
            }while (getNeighboorValue(newDirection));
            setNextDirection(newDirection);
            checkIfInCave();
            return false;
        }
        return true;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        if(getTicks()*gameEngine.getInterval()<5000){
            move();
        }else{
            if(getMazeElement(getX(),getY())==null){
                move();
                return;
            }
            if(getMazeElement(getX(),getY()).getSymbol()=='y')
                travelTo(getCaveDoorCoords(0),getCaveDoorCoords(1));
            move();
        }

    }
}
