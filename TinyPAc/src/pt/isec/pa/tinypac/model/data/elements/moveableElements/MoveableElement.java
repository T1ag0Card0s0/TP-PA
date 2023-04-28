package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.MazeInfo;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.Element;

public class MoveableElement extends Element {
    private MazeInfo maze;
    private long ticks;
    private int lastX,lastY;
    private int currentDirection,nextDirection;//0=TOP 1=RIGHT 2=BOTTOM 3=LEFT -1=STOP
    private final boolean []neighboors;//TOP,RIGHT,BOTTOM,LEFT Check if there are walls around
    private boolean vulnerable;
    private long interval;
    private Element underElement;
    public MoveableElement(int x, int y, char symbol, MazeInfo maze){
        super(symbol,x,y);
        this.lastX=x;
        this.lastY=y;
        this.neighboors=new boolean[]{true,true,true,true};
        this.currentDirection=-1;
        this.maze=maze;
        this.ticks=0;
        this.interval=0;
        this.vulnerable=false;
        this.underElement=null;
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
    public boolean move(){
        ticks++;
        checkNeighboors();
        changeDirection();
        if(currentDirection==-1) { return false;}
        if (!neighboors[currentDirection]) {
            lastX=getX();
            lastY=getY();
            int x=getX(),y=getY();
            maze.setMazeElement(getX(),getY(),underElement);
            switch (currentDirection) {
                case 0 -> y--;//UP
                case 1 -> x++;//RIGHT
                case 2 -> y++;//BOTTOM
                case 3 -> x--;//LEFT
            }
            setX(x);
            setY(y);
            underElement=new Element(maze.getSymbol(x,y),x,y);
            return true;
        }
        currentDirection=-1;
        return false;
    }
    public void evolve(){}
    public int getLastX(){return lastX;}
    public int getLastY(){return lastY;}
    public boolean getNeighboorValue(int index){return neighboors[index];}
    public char getMazeElementSymbol(int x,int y){return maze.getMazeSymbols()[x][y];}
    public boolean[] getNeighboors(){checkNeighboors();return neighboors;}
    public long getTicks(){return ticks;}
    public boolean getVulnerable(){return vulnerable;}
    public long getInterval(){return interval;}
    public void setNextDirection(int newDirection){this.nextDirection=newDirection;}
    public void setVulnerable(boolean value){vulnerable=value;}
    public void setGameEngineInterval(long interval){this.interval=interval;}
    public void setMaze(MazeInfo newMaze){maze=newMaze;}
    public void setUnderElement(Element underElement){this.underElement=underElement;}
}
