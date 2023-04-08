package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.GhostCave;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.NormalFood;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.Wall;
import pt.isec.pa.tinypac.ui.text.TextInterface;


public class PacMan extends ClientElement implements IGameEngineEvolve {
    private final TextInterface textInterface;
    public PacMan(Maze maze,TextInterface textInterface){
        super(0,0,'█',TextColor.ANSI.GREEN,maze);
        this.textInterface=textInterface;
        setStartPosition();
    }
    public boolean checkNextElement(IMazeElement nextElement){
         return !(nextElement instanceof Wall||nextElement instanceof GhostCave);
    }
    public IMazeElement getNextElement(EDirections direction){
        int x=getxCoord();
        int y=getyCoord();
        switch (direction){
            case UP-> y--;
            case DOWN->y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        return getMazeElement(x,y);
    }
    public void changeDirection(){
        if(checkNextElement(getNextElement(getNexDirection()))){
            setCurrentDirection(getNexDirection());
        }
    }

    @Override
    public void move(){
        changeDirection();
        if(checkNextElement(getNextElement(getCurrentDirection()))){
            int x=getxCoord();
            int y=getyCoord();
            switch (getCurrentDirection()){
                case UP-> y--;
                case DOWN->y++;
                case LEFT -> x--;
                case RIGHT -> x++;
            }
            setxCoord(x);
            setyCoord(y);
            if(getMazeElement(x,y) instanceof NormalFood)setMazeElement(x,y,null);
        }
    }
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        switch (textInterface.ReadArrow()){
            case "ArrowUp"->setNexDirection(EDirections.UP);
            case "ArrowDown"->setNexDirection(EDirections.DOWN);
            case "ArrowLeft"->setNexDirection(EDirections.LEFT);
            case "ArrowRight"-> setNexDirection(EDirections.RIGHT);
            case "Escape"-> gameEngine.stop();
        }
    }
}

