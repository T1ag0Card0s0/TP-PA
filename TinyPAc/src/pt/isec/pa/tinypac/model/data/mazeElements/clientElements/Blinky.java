package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class Blinky  extends ClientElement implements IGameEngineEvolve{

    public Blinky(int xCoord, int yCoord, Maze maze){
        super(xCoord,yCoord,'█',TextColor.ANSI.RED,maze);
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}
