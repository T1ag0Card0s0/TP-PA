package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;

import java.util.Random;


public class Ghost extends MoveableElement{
    private final Random rnd;
    private int[] caveDoorCoords;
    private boolean inCave;
    public Ghost(int x, int y,char symbol, Maze maze) {
        super(x, y,symbol, maze);
        this.caveDoorCoords=new int[2];
        this.inCave=true;
        this.rnd=new Random();
    }
    public void travelTo(int x,int y){
        if(getX()<x){
            setNextDirection(1);
        }else if(getY()<y){
            setNextDirection(2);
        }else if(getX()>x){
            setNextDirection(3);
        }else{
            setNextDirection(0);
        }
        move();
    }
    public void lockedMovement(){
        if(getTicks()<50&&!super.move()) {
            int newDirection;
            do {
                newDirection = rnd.nextInt(4);
            } while (getNeighboorValue(newDirection));
            setNextDirection(newDirection);
            return;
        }
    }
    public boolean getInCave(){return inCave;}
    public int getCaveDoorCoords(int index){
        return caveDoorCoords[index];
    }
    public void setCaveDoorCoords(int []doorCoords){
        this.caveDoorCoords=doorCoords;
    }
    public void setInCave(boolean inCave) {this.inCave = inCave;}


}
