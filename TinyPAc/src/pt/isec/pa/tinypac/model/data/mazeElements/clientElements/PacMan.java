package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.NormalFood;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.PowerFood;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.Wall;
import pt.isec.pa.tinypac.ui.text.TextInterface;

public class PacMan extends ClientElement implements IGameEngineEvolve {
    private final Maze maze;
    private int pontos;
    private final TextInterface textInterface;
    public PacMan(Maze maze,TextInterface textInterface){
        super(0,0,'█',TextColor.ANSI.GREEN);
        this.textInterface=textInterface;
        this.maze=maze;
    }
    /*public PacMan(int xCoord, int yCoord,TextInterface textInterface){
        super(xCoord,yCoord,'█',TextColor.ANSI.GREEN);
        this.pontos=0;
        this.textInterface=textInterface;
    }*/
    @Override
    public void move(){
        int x=getxCoord(),y=getyCoord();
        switch (getCurrentDirection()) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        IMazeElement nextElement = maze.get(x,y);
        if(!(nextElement instanceof Wall)){
            setxCoord(x);
            setyCoord(y);
        }else{
            setCurrentDirection(EDirections.STOPED);
        }
    }
    public int getPontos(){return pontos;}
    public void somaPontos(int valor){this.pontos+=valor;}


    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        switch (textInterface.ReadArrow()){
            case "ArrowUp"->setCurrentDirection(EDirections.UP);
            case "ArrowDown"->setCurrentDirection(EDirections.DOWN);
            case "ArrowLeft"->setCurrentDirection(EDirections.LEFT);
            case "ArrowRight"-> setCurrentDirection(EDirections.RIGHT);
            case "Escape"-> gameEngine.stop();
        }
    }
}

