package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends MoveableElement{
    private final Random rnd;
    private List<int[]> positions;
    private final int[] caveDoorCoords;
    private boolean inCave;
    private final int[] initCoords;
    private int xPCoord,yPCoord;
    private boolean vulnerable;
    private int index;
    public Ghost(char symbol, MazeInfo maze) {
        super(maze.getInitGhostsPosition()[0],maze.getInitGhostsPosition()[1],symbol, maze);
        this.initCoords=maze.getInitGhostsPosition();
        this.caveDoorCoords= maze.getCaveDoorCoords();
        this.inCave=true;
        this.rnd=new Random();
        this.positions =new ArrayList<>();
        this.vulnerable=false;
        this.index=0;
    }
    public void travelTo(int x,int y){
        if(getX()==x&&getY()==y)return;
        if(x==getX()) {
            if (y < getY()) setNextDirection(0);
            else setNextDirection(2);
        }else if(y==getY()){
            if (x < getX()) setNextDirection(1);
            else setNextDirection(3);
        }
    }
    public void choseRandDirection(){
        int nextDirection;
        do{
            nextDirection=rnd.nextInt(4);
        }while (getNeighboorValue(nextDirection));
        setNextDirection(nextDirection);
    }
    public void lockedMovement(){
        if(!move()){
            choseRandDirection();
        }
        if(getTicks()*getInterval()>5000){
            if(getUnderElement()==null)return;
            if(getUnderElement().getSymbol()=='Y')return;
            travelTo(caveDoorCoords[0],caveDoorCoords[1]);
        }
    }
    public boolean getInCave(){
        if(getUnderElement()==null)inCave = false;
        else inCave= (getUnderElement().getSymbol() == 'y'||getUnderElement().getSymbol() == 'Y');
        return inCave;
    }
    public int[] getInitCoords() {return initCoords;}
    public int getxPCoord() {return xPCoord;}
    public int getyPCoord() {return yPCoord;}
    public boolean getVulnerable(){return vulnerable;}
    public void setPCoords(int x, int y){
        xPCoord=x;
        yPCoord=y;
    }
    public void setVulnerable(boolean value){vulnerable=value;}
    @Override
    public void checkNeighboors() {
        if(getVulnerable()){
            super.checkNeighboors();
            return;
        }
        if(getInCave()){
            super.checkNeighboors();
        }else{
            super.checkNeighboorsWithExtraConstraint('Y');
        }
    }
    public void vulnerableMove(){
        if(index>0){
            if(getX()==getxPCoord()&&getY()==getyPCoord()) {
                setXY(getInitCoords());
                setVulnerable(false);
                positions=new ArrayList<>();
            }else{
                int nextX=positions.get(index)[0],nextY=positions.get(index)[1];
                setMazeElement(getX(),getY(),getUnderElement());
                if(getMazeElement(nextX,nextY) instanceof MoveableElement m)setUnderElement(m.getUnderElement());
                else setUnderElement(new Element(getSymbol(nextX, nextY), nextX, nextY));
                setX(nextX);setY(nextY);
                setMazeElement(getX(),getY(),this);
                positions.remove(index);
                index--;
            }
        }else{
           setVulnerable(false);
        }
    }
    @Override
    public boolean move() {
        if(getVulnerable()){
           vulnerableMove();
           positions.add(new int[]{getX(),getY()});
        }else{
            if(!inCave) {
                index = positions.size();
                positions.add(new int[]{getX(), getY()});
            }
            return super.move();
        }
        return false;
    }
}
