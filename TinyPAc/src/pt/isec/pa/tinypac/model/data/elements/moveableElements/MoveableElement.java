package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;

public class MoveableElement implements IMazeElement {
    private final Maze maze;
    private long ticks;
    private int x,y;
    private int lastX,lastY;
    private final TextColor color;
    private final char symbol;
    private int currentDirection,nextDirection;//0=TOP 1=RIGHT 2=BOTTOM 3=LEFT -1=STOP
    private final boolean []neighboors;//TOP,RIGHT,BOTTOM,LEFT Check if there are walls around
    public MoveableElement(int x,int y,Maze maze,TextColor color){
        this.symbol='█';
        this.color=color;
        this.x=x;
        this.y=y;
        this.lastX=x;
        this.lastY=y;
        this.neighboors=new boolean[]{true,true,true,true};
        this.currentDirection=-1;
        this.maze=maze;
        this.ticks=0;
    }
    public void checkNeighboors() {
        neighboors[0] = maze.get(x, y - 1) != null && maze.get(x, y - 1).getSymbol() == 'x';
        neighboors[1] = maze.get(x + 1,y ) != null && maze.get(x + 1,y ).getSymbol() == 'x';
        neighboors[2] = maze.get(x, y+1) != null && maze.get(x, y+1).getSymbol() == 'x';
        neighboors[3] = maze.get(x-1, y) != null && maze.get(x-1, y).getSymbol() == 'x';
    }
    public void setNextDirection(String directionKey) {
        int newDirection=-1;
        switch (directionKey){
            case "ArrowUp"->newDirection=0;
            case "ArrowRight"->newDirection=1;
            case "ArrowDown"->newDirection=2;
            case "ArrowLeft"->newDirection=3;
        }
        this.nextDirection = newDirection;
    }
    public void changeDirection(){
        if(nextDirection==-1)return;
        if(!neighboors[nextDirection]){
            currentDirection=nextDirection;
        }
    }
    public boolean move(){
        ticks++;
        checkNeighboors();
        changeDirection();
        if(currentDirection==-1) { return false;}

        if (!neighboors[currentDirection]) {
            lastX=x;
            lastY=y;
            switch (currentDirection) {
                case 0 -> y--;//UP
                case 1 -> x++;//RIGHT
                case 2 -> y++;//BOTTOM
                case 3 -> x--;//LEFT
            }
            return true;
        }
        currentDirection=-1;
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLastX(){return lastX;}
    public int getLastY(){return lastY;}
    public boolean getNeighboorValue(int index){
        return neighboors[index];
    }
    public TextColor getColor() {return color;}
    public IMazeElement getMazeElement(int x,int y){
        return maze.get(x,y);
    }
    public long getTicks(){return ticks;}
    public void setMazeElement(int x, int y, IMazeElement mazeElement){
        maze.set(y, x, mazeElement);
    }
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

    public void setNextDirection(int newDirection){
        this.nextDirection=newDirection;
    }
    @Override
    public char getSymbol() {
        return symbol;
    }
}
