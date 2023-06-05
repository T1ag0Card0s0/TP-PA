package pt.isec.pa.tinypac.model.data.moveableElements;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;
import pt.isec.pa.tinypac.model.data.maze.MazeInfo;

public class MoveableElement extends Element{
    private MazeInfo mazeInfo;
    private int currentDirection, nextDirection;
    private int lastX,lastY,initX,initY;
    private final boolean []neighboors;//TOP,RIGHT,BOTTOM,LEFT Check if there are walls around
    private Element underElement;
    public MoveableElement(char symbol, int y, int x, MazeInfo mazeInfo) {
        super(symbol,y,x);
        this.neighboors = new boolean[4];
        this.currentDirection=-1;
        this.nextDirection=currentDirection;
        if(mazeInfo.get(y,x) instanceof MoveableElement moveableElement){
            underElement = moveableElement.getUnderElement();
        }else{
            underElement =  (Element) mazeInfo.get(y,x);
        }
        mazeInfo.set(y,x,this);
        this.lastX=x;
        this.lastY=y;
        this.initX = x;
        this.initY = y;
        this.mazeInfo=mazeInfo;
        checkNeighboors();

    }

    public void checkNeighboors(){
        neighboors[0] = getMazeElementSymbol(getY()-1,getX()) == 'x';
        neighboors[1] = getMazeElementSymbol(getY(),getX() + 1) == 'x';
        neighboors[2] = getMazeElementSymbol(getY()+1,getX()) == 'x';
        neighboors[3] = getMazeElementSymbol(getY(),getX()-1) == 'x';
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
        checkNeighboors();
        changeDirection();
        if(currentDirection==-1) return false;
        if(neighboors[currentDirection]){ currentDirection=-1;return false;}

        int x = getX(),y = getY();
        this.lastX=getX();this.lastY=getY();
        switch (currentDirection) {
            case 0 -> y--;//UP
            case 1 -> x++;//RIGHT
            case 2 -> y++;//BOTTOM
            case 3 -> x--;//LEFT
        }
        if(mazeInfo.get(y,x) instanceof MoveableElement moveableElement){
            underElement = moveableElement.getUnderElement();
        }else{
            underElement = (Element) mazeInfo.get(y,x);
        }
        setX(x);setY(y);
        return true;
    }
    public int getCurrentDirection() {return currentDirection;}
    public boolean[] getNeighboors() {return neighboors;}
    public Element getUnderElement(){return underElement;}
    public int getLastX(){return lastX;}
    public int getLastY(){return lastY;}
    public int getXPacMan(){return mazeInfo.getMoveableElement('P').getX();}
    public int getYPacMan(){return mazeInfo.getMoveableElement('P').getY();}
    public int getInitX(){return initX;}
    public int getInitY(){return initY;}
    protected char getMazeElementSymbol(int y,int x){
        if(mazeInfo.get(y,x)==null) return ' ';
        return  mazeInfo.get(y,x).getSymbol();
    }
    public void setCurrentDirection(int currentDirection) {this.currentDirection = currentDirection;}
    public void setNextDirection(int nextDirection) {this.nextDirection = nextDirection;}

    public void setUnderElement(Element underElement) {this.underElement = underElement;}

    public void setMazeInfo(MazeInfo mazeInfo){this.mazeInfo=mazeInfo;}

    @Override
    public String toString() {
        return "("+getX()+","+getY()+")";
    }
}
