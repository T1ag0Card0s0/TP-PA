package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;


 public class ShowBoard  implements IGameEngineEvolve {
    private final PacMan pacMan;
    private final Maze maze;
    private final TextInterface textInterface;
    public ShowBoard(Maze maze,PacMan pacMan,TextInterface textInterface){
        this.maze=maze;
        this.textInterface =textInterface;
        this.pacMan=pacMan;
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        textInterface.DrawMazeElement(pacMan.getMazeElement(pacMan.getxCoord(),pacMan.getyCoord()),pacMan.getxCoord(),pacMan.getyCoord());
        pacMan.move();
        textInterface.DrawClientMazeElement(pacMan);
    }
}