package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;

public class Clyde extends Ghost {

    public Clyde(int x, int y, Maze maze) {
        super(x, y,'c',maze);
    }

}
