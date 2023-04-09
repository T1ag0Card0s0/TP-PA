package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.*;

public class PacMan extends ClientElement {
    private int pontos,numOfFoodEaten;
    private int index;
    private final int [][]wraperCoordinates;
    public PacMan(Maze maze){
        super(0,0,'█',TextColor.ANSI.GREEN,maze);
        this.wraperCoordinates=new int[2][2];
        this.index=0;
        this.pontos=0;
        this.numOfFoodEaten=0;
    }
    public void storeWraperCoordinates( int x, int y) {
        wraperCoordinates[index][0] = x;
        wraperCoordinates[index][1] = y;
        index++;
    }
    private int wichWraperIsPacMan( ){
        for(int i = 0; i<wraperCoordinates.length;i++){
            if(getxCoord()==wraperCoordinates[i][0]&&getyCoord()==wraperCoordinates[i][1]){
                return i;
            }
        }
        return -1;
    }
    private void teleTransport(){
        switch (wichWraperIsPacMan()){
            case 0->{
                setxCoord(wraperCoordinates[1][0]);
                setyCoord(wraperCoordinates[1][1]);
            }
            case 1->{
                setxCoord(wraperCoordinates[0][0]);
                setyCoord(wraperCoordinates[0][1]);
            }
        }
    }
    private boolean checkNextElement(IMazeElement nextElement){
        return !(nextElement instanceof Wall||nextElement instanceof GhostCaveDoor);
    }
    private boolean canMoveNextPosition(EDirections direction){
        int x=getxCoord();
        int y=getyCoord();
        switch (direction){
            case UP-> y--;
            case DOWN->y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        return checkNextElement(getMazeElement(x,y));
    }
    private void changeDirection(){
        if(canMoveNextPosition(getNexDirection())){
            setCurrentDirection(getNexDirection());
        }
    }
    private void identifyCurrentAction(int x,int y){
        IMazeElement element = getMazeElement(x,y);
        if(element instanceof NormalFood) {
            pontos++;
            numOfFoodEaten++;
            setMazeElement(x, y, null);
        }else if(element instanceof PowerFood){
            pontos+=10;
            numOfFoodEaten++;
            setMazeElement(x, y, null);
        }else if(element instanceof Wraper){
            teleTransport();
        }
    }
    public int getNumOfFoodEaten() {
        return numOfFoodEaten;
    }
    @Override
    public boolean move(){
        changeDirection();
        if(canMoveNextPosition(getCurrentDirection())){
            int x=getxCoord();
            int y=getyCoord();
            switch (getCurrentDirection()){
                case UP-> y--;
                case DOWN->y++;
                case LEFT -> x--;
                case RIGHT -> x++;
            }
            setxCoord(x);
            setyCoord(y);
            identifyCurrentAction(x,y);
            return true;
        }
        return false;
    }
}

