package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;

public class Pinky extends ClientElement implements IGameEngineEvolve {

    public Pinky(int xCoord, int yCoord){
        super(xCoord,yCoord,'█',new TextColor.RGB(255,184,255));
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
    }
