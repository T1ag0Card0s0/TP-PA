package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.MazeInfo;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.Element;

import java.util.ArrayList;

public class MoveableElement extends Element {
    private final MazeInfo maze;
    private long ticks;
    private int currentDirection,nextDirection;//0=TOP 1=RIGHT 2=BOTTOM 3=LEFT -1=STOP
    private final boolean []neighboors;//TOP,RIGHT,BOTTOM,LEFT Check if there are walls around
    private long interval;
    private Element underElement;
    private int lastX,lastY;
    public MoveableElement(int x, int y, char symbol, MazeInfo maze){
        super(symbol,x,y);
        this.neighboors=new boolean[]{true,true,true,true};
        this.currentDirection=-1;
        this.maze=maze;
        this.ticks=0;
        this.interval=0;
        this.underElement=null;
        this.lastX=0;
        this.lastY=0;
        checkNeighboors();
    }
    public void checkNeighboors() {
        neighboors[0] = getMazeElementSymbol(getX(),getY()-1) == 'x';
        neighboors[1] = getMazeElementSymbol(getX() + 1,getY()) == 'x';
        neighboors[2] = getMazeElementSymbol(getX(),getY()+1) == 'x';
        neighboors[3] = getMazeElementSymbol(getX()-1,getY()) == 'x';
    }
    public void checkNeighboorsWithExtraConstraint(char constraint) {
        char top=getMazeElementSymbol(getX(),getY()-1),right=getMazeElementSymbol(getX() + 1,getY())
                ,bottom=getMazeElementSymbol(getX(),getY()+1),left=getMazeElementSymbol(getX()-1,getY());
            neighboors[0] = top=='x'||top==constraint;
            neighboors[1] = right=='x'||right==constraint;
            neighboors[2] = bottom=='x'||bottom==constraint;
            neighboors[3] = left=='x'||left==constraint;

    }
    public void changeDirection(){
        if(nextDirection==-1)return;
        if(!neighboors[nextDirection]){
            currentDirection=nextDirection;
        }
    }
    public Element getUnderElement(){return underElement;}
    public boolean move(){
        ticks++;
        checkNeighboors();
        changeDirection();
        if(currentDirection==-1) { return false;}
        if (!neighboors[currentDirection]) {
            int x=getX(),y=getY();
            lastX=x;lastY=y;
            setMazeElement(x,y,underElement);
            switch (currentDirection) {
                case 0 -> y--;//UP
                case 1 -> x++;//RIGHT
                case 2 -> y++;//BOTTOM
                case 3 -> x--;//LEFT
            }

            if(maze.getMazeElement(x,y) instanceof MoveableElement m){
                underElement=m.getUnderElement();
            }
            else underElement = new Element(maze.getSymbol(x, y), x, y);
            setX(x);setY(y);
            setMazeElement(getX(),getY(),this);
            return true;
        }
        currentDirection=-1;
        return false;
    }
    public void evolve(){}
    public boolean inPacManPosition(int x,int y){return maze.inPacManPosition(x,y);}
    public void pacManAteAGhost(){maze.pacManAteAGhost();}
    public boolean getNeighboorValue(int index){return neighboors[index];}
    public char getMazeElementSymbol(int x,int y){return maze.getMazeSymbols()[x][y];}
    public boolean[] getNeighboors(){checkNeighboors();return neighboors;}
    public long getTicks(){return ticks;}
    public long getInterval(){return interval;}
    public int getCurrentDirection(){return currentDirection;}
    public IMazeElement getMazeElement(int x,int y){return maze.getMazeElement(x,y);}
    public int getCurrentLevel(){return maze.getCurrentLevel();}
    public char getSymbol(int x,int y){return maze.getSymbol(x,y);}
    public int getLastX(){return lastX;}
    public int getLastY(){return lastY;}
    public void setNextDirection(int newDirection){this.nextDirection=newDirection;}
    public void setGameEngineInterval(long interval){this.interval=interval;}
    public void setUnderElement(Element underElement){this.underElement=underElement;}
    public void setMazeElement(int x,int y, Element newElement){maze.setMazeElement(x,y,newElement);}
    public void setVulnerable(boolean value){maze.setVulnerable(value);}
}
