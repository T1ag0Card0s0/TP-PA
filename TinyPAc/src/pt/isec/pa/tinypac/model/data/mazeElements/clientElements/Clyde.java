package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

public class Clyde extends ClientElement implements IGameEngineEvolve {

    public Clyde(int xCoord, int yCoord){
        super(xCoord,yCoord,'█',new TextColor.RGB(255,184,82));
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}
