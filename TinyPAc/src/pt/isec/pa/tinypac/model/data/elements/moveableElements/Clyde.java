package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;


public class Clyde extends Ghost {
    int xPCoord,yPCoord;
    public Clyde( MazeInfo maze) {
        super('c',maze);

    }
    public boolean pacManInFieldOfVision(){
        int i = 0;
        for(boolean b: getNeighboors()){
            if(!b){
                int x=getX(),y=getY();
                while (getMazeElementSymbol(x,y)!='x'){
                    switch (i){
                        case 0-> y--;
                        case 1-> x++;
                        case 2-> y++;
                        case 3-> x--;
                    }
                    if(x==xPCoord&&yPCoord==y){
                        setNextDirection(i);
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }
    public void setPCoords(int x,int y){
        xPCoord=x;
        yPCoord=y;
    }
    @Override
    public void evolve() {
        if (getInCave()) {
            lockedMovement();
        } else{
            if (pacManInFieldOfVision()) {
                super.move();
            } else if (!super.move()) {
                if(!getVulnerable()) {
                    choseRandDirection();
            }
        }
    }
}
