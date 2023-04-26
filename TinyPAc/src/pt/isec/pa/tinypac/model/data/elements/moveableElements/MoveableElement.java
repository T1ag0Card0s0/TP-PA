package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;

public class MoveableElement implements IMazeElement {
    private final Maze maze;
    private long ticks;
    private int x,y;
    private int lastX,lastY;
    private final char symbol;
    private int currentDirection,nextDirection;//0=TOP 1=RIGHT 2=BOTTOM 3=LEFT -1=STOP
    private final boolean []neighboors;//TOP,RIGHT,BOTTOM,LEFT Check if there are walls around
    private boolean vulnerable;
    private long interval;
    public MoveableElement(int x,int y,char symbol,Maze maze){
        this.symbol=symbol;
        this.x=x;
        this.y=y;
        this.lastX=x;
        this.lastY=y;
        this.neighboors=new boolean[]{true,true,true,true};
        this.currentDirection=-1;
        this.maze=maze;
        this.ticks=0;
        this.interval=0;
        this.vulnerable=false;
        checkNeighboors();
    }
    public void checkNeighboors() {
        neighboors[0] = getMazeElementSymbol(x,y-1) == 'x';
        neighboors[1] = getMazeElementSymbol(x + 1,y) == 'x';
        neighboors[2] = getMazeElementSymbol(x,y+1) == 'x';
        neighboors[3] = getMazeElementSymbol(x-1,y) == 'x';
    }
    public void checkNeighboorsWithExtraConstraint(char c) {
        neighboors[0] = neighboors[0]||getMazeElementSymbol(x,y-1) == c;
        neighboors[1] = neighboors[1]||getMazeElementSymbol(x + 1,y) == c;
        neighboors[2] = neighboors[2]||getMazeElementSymbol(x,y+1) == x;
        neighboors[3] = neighboors[3]||getMazeElementSymbol(x-1,y) == c;
    }
    public boolean[] getNeighboors(){return neighboors;}
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
    public int getCurrentDirection(){return currentDirection;}
    public boolean getNeighboorValue(int index){
        return neighboors[index];
    }
    public char getMazeElementSymbol(int x,int y){
        return maze.getMaze()[x][y];
    }
    public char  [][]getMaze(){return maze.getMaze();}
    public long getTicks(){return ticks;}
    public void setMazeElement(int x, int y, IMazeElement mazeElement){
        maze.set(y, x, mazeElement);
    }
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setNextDirection(int newDirection){
        this.nextDirection=newDirection;
    }
    public void setVulnerable(boolean value){vulnerable=value;}
    public boolean getVulnerable(){return vulnerable;}
    public void setGameEngineInterval(long interval){this.interval=interval;}
    public long getInterval(){return interval;}
    @Override
    public char getSymbol() {
        return symbol;
    }
}
