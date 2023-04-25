package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends MoveableElement{
    private ArrayList<Integer> directions;
    private final Random rnd;
    private int[] caveDoorCoords;
    private boolean inCave;
    public Ghost(int x, int y,char symbol, Maze maze) {
        super(x, y,symbol, maze);
        this.caveDoorCoords=new int[2];
        this.inCave=true;
        this.rnd=new Random();
    }
    public boolean travelTo(int x,int y){
        if(getMazeElementSymbol(getX(),getY())=='Y')return true;
        if(x==getX()) {
            if (y < getY()) setNextDirection(0);
            else setNextDirection(2);
        }else if(y==getY()){
            if (x < getX()) setNextDirection(1);
            else setNextDirection(3);
        }
        /*if(getMazeElementSymbol(getX(),getY())=='Y')return true;
        if(getX()<=x){
            setNextDirection(1);
        }else if(getY()>=y){
            setNextDirection(2);
        }else if(getX()>=x){
            setNextDirection(3);
        }else{
            setNextDirection(0);
        }*/
        return false;
    }
    public boolean getInCave(){
        inCave= getMazeElementSymbol(getX(), getY()) == 'y';
        return inCave;
    }
    public int getCaveDoorCoords(int index){
        return caveDoorCoords[index];
    }
    public void setCaveDoorCoords(int []doorCoords){
        this.caveDoorCoords=doorCoords;
    }
    public void setInCave(boolean inCave) {this.inCave = inCave;}
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
        }else{
            if(getTicks()*getInterval()>5000){
               travelTo(caveDoorCoords[0],caveDoorCoords[1]);
            }
        }
    }

}
