package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;

public class Clyde extends ClientElement implements IGameEngineEvolve {

    public Clyde(int xCoord, int yCoord, char symbol, TextColor color){
        super(xCoord,yCoord,symbol,color);
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}
