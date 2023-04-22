package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;


public class Ghost extends MoveableElement{
    private int[] caveDoorCoords;
    private boolean inCave;
    public Ghost(int x, int y, Maze maze, TextColor color) {
        super(x, y, maze, color);
        this.caveDoorCoords=new int[2];
        this.inCave=false;
    }
    public void travelTo(int x,int y){
        if(getX()==x&&getY()==y)return;
        if(getX()<x){
            x++;
        }else if(getY()<y){
            y++;
        }else if(getX()>x){
            x--;
        }else{
            y--;
        }
        setX(x);
        setY(y);
    }
    public void checkIfInCave(){
        IMazeElement element=getMazeElement(getX(),getY());
        if(element==null){
            inCave=false;
            return;
        }
        inCave=true;
    }

    public boolean getInCave(){return inCave;}
    public int getCaveDoorCoords(int index){
        return caveDoorCoords[index];
    }
    public void setCaveDoorCoords(int []doorCoords){
        this.caveDoorCoords=doorCoords;
    }
    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }
}
