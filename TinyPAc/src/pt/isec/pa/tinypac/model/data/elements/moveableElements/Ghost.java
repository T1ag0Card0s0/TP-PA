package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends MoveableElement{
    private final Random rnd;
    private ArrayList<Integer> directions;
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
        this.directions=new ArrayList<>();
        this.vulnerable=false;
        this.index=0;
    }
    public void travelTo(int x,int y){
        if(getUnderElement()==null)return;
        if(getUnderElement().getSymbol()=='Y')return;
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
        if(!super.move()){
            choseRandDirection();
        }
        if(getTicks()*getInterval()>5000){
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
    @Override
    public boolean move() {
        super.move();
        if(getVulnerable()){
            if(index<directions.size()) {
                setNextDirection(directions.get(index++));
            }
            if(getX()==getxPCoord()&&getY()==getyPCoord()) {
                setXY(getInitCoords());
                setVulnerable(false);
                index=0;
                directions=new ArrayList<>();
            }
        }else {
            switch (getCurrentDirection()) {
                case 0 -> directions.add(0, 2);
                case 1 -> directions.add(0, 3);
                case 2 -> directions.add(0, 0);
                case 3 -> directions.add(0, 1);
                case -1 -> directions.add(0, -1);
            }
        }
        return getCurrentDirection()!=-1 ;
    }
}
