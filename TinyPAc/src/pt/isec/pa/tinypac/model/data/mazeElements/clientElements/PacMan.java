package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.NormalFood;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.PowerFood;

public class PacMan extends ClientElement {
    private int pontos;
    public PacMan(int xCoord, int yCoord){
        super(xCoord,yCoord,'p',TextColor.ANSI.GREEN);
        this.pontos=0;
    }
    public boolean checkNextPosition(IMazeElement nextElement){
        return nextElement == null || nextElement instanceof NormalFood || nextElement instanceof PowerFood;
    }
    public void move(){
        int x=getxCoord(),y=getyCoord();
        switch (getCurrentDirection()) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        IMazeElement element = getMaze().get(y,x);
        if(checkNextPosition(element)){
            setxCoord(x);
            setyCoord(y);
        }else {
            setCurrentDirection(EDirections.STOPED);
        }

    }
    public int getPontos(){return pontos;}
    public void somaPontos(int valor){this.pontos+=valor;}

}
