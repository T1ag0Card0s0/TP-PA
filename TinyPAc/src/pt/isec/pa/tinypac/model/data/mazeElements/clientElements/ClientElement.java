package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;


public class ClientElement implements IMazeElement {
    private Maze maze;
    private int xCoord;
    private int yCoord;
    private final char symbol;
    private final TextColor color;
    private EDirections currentDirection;
    public ClientElement(int xCoord,int yCoord,char symbol,TextColor color){
        this.xCoord=xCoord;
        this.yCoord=yCoord;
        this.symbol=symbol;
        this.currentDirection=EDirections.STOPED;
        this.color=color;
    }

    public Maze getMaze() {
        return maze;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    public EDirections getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(EDirections newDirection){
        currentDirection=newDirection;
    }

    @Override
    public TextColor getColor() {
        return null;
    }

    @Override
    public char getSymbol() {
        return 0;
    }

}
