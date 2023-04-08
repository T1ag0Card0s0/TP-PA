package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;


public class ClientElement implements IMazeElement {
    private final Maze maze;
    private int xCoord;
    private int yCoord;
    private final char symbol;
    private final TextColor color;
    private EDirections currentDirection;
    private EDirections nexDirection;
    public ClientElement(int xCoord,int yCoord,char symbol,TextColor color,Maze maze){
        this.xCoord=xCoord;
        this.yCoord=yCoord;
        this.symbol=symbol;
        this.currentDirection=EDirections.STOPED;
        this.nexDirection=this.currentDirection;
        this.color=color;
        this.maze=maze;
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
        currentDirection= newDirection;
    }
    public EDirections getNexDirection() {
        return nexDirection;
    }
    public void setNexDirection(EDirections nexDirection) {
        this.nexDirection = nexDirection;
    }
    public IMazeElement getMazeElement(int x,int y){
        return maze.get(x,y);
    }
    public void setMazeElement(int x,int y,IMazeElement element){
        maze.set(x,y,element);
    }
    public void setStartPosition(){
        maze.setPosition(this);
    }
    public void move(){}

    @Override
    public TextColor getColor() {
        return color;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

}
