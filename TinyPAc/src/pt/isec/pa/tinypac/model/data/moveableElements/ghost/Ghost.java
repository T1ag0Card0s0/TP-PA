package pt.isec.pa.tinypac.model.data.moveableElements.ghost;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends MoveableElement {
    private boolean locked;
    private boolean vulnerable;
    private final int []caveDoor;
    private List<int []> positions;
    private int index;
    public Ghost(char symbol, int y, int x,MazeInfo mazeInfo) {
        super(symbol,y,x,mazeInfo);
        this.locked=true;
        this.vulnerable=false;
        this.caveDoor=mazeInfo.getCaveDoors();
        this.index=0;
        this.positions = new ArrayList<>();
    }
    public void TravelTo(int x,int y){
        boolean XAxis=false,YAxis=false;
        if(x==getX())YAxis = true;
        else if(y==getY())XAxis = true;
        else {
            randomMove();
        }
        if(XAxis)
            if(getX()>x) setNextDirection(3);
            else if(getX()<x)setNextDirection(1);
        if(YAxis)
            if (getY() > y) setNextDirection(0);
            else if (getY() < y)setNextDirection(2);
    }
    public void randomMove(){
        if(getCurrentDirection()>=0) return;
        Random rnd = new Random();
        int rndDirecion;
        do {
            rndDirecion = rnd.nextInt(4);
        } while (getNeighboors()[rndDirecion]);
        setNextDirection(rndDirecion);
    }
    public void vulnerableMove(){
        if(getUnderElement()!=null)
            if(getUnderElement().getSymbol()=='y'){
                vulnerable=false;
                return;
            }
        TravelTo(positions.get(index)[0],positions.get(index)[1]);
        if(index>0)
            index--;
    }

    public void myMove() {
        if(getUnderElement()!=null)
            if(getUnderElement().getSymbol()=='y')
                TravelTo(caveDoor[1],caveDoor[0]);
    }
    public boolean getVulnerable(){return vulnerable;}
    public boolean getLocked(){return locked;}
    public void setVulnerable(boolean value){this.vulnerable=value;}
    public void setLocked(boolean value){this.locked=value;}
    @Override
    public boolean move() {
        positions.add(new int[]{getX(), getY()});
        if (vulnerable) vulnerableMove();
        else if (locked){
            positions=new ArrayList<>();
            randomMove();
        }
        else{index=positions.size(); myMove();}
        return super.move();
    }
}
