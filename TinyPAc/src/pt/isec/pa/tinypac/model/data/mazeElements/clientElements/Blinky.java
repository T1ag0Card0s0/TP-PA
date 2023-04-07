package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class Blinky  extends ClientElement implements IGameEngineEvolve{

    public Blinky(int xCoord,int yCoord){
        super(xCoord,yCoord,'█',TextColor.ANSI.RED);
    }


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}
