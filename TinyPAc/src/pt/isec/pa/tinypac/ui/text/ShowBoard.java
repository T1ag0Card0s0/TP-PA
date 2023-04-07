package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.ClientElement;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ShowBoard implements IGameEngineEvolve {
    private PacMan pacMan;
    private final Maze maze;
    private final TextInterface textInterface;
    public ShowBoard(Maze maze,PacMan pacMan,TextInterface textInterface){
        this.maze=maze;
        this.textInterface =textInterface;
        this.pacMan=pacMan;
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        textInterface.DrawMaze(maze);
        textInterface.DrawClientMazeElement(pacMan);
    }
}