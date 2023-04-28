package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends MoveableElement{
    private final Random rnd;
    private ArrayList<Integer> directions;
    private final int[] caveDoorCoords;
    private boolean inCave;
    public Ghost(char symbol, MazeInfo maze) {
        super(maze.getInitGhostsPosition()[0],maze.getInitGhostsPosition()[1],symbol, maze);
        this.caveDoorCoords= maze.getCaveDoorCoords();
        this.inCave=true;
        this.rnd=new Random();
        this.directions=new ArrayList<>();
    }
    public void travelTo(int x, int y){
        if(getMazeElementSymbol(getX(),getY())=='Y')return;
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
        }else{
            if(getTicks()*getInterval()>5000){
                travelTo(caveDoorCoords[0],caveDoorCoords[1]);
            }
        }
    }

    public boolean getInCave(){
        inCave= getMazeElementSymbol(getX(), getY()) == 'y';
        return inCave;
    }

}