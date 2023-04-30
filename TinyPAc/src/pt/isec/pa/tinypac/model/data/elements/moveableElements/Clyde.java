package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;


public class Clyde extends Ghost {
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
                    if(x==getxPCoord()&&getyPCoord()==y){
                        setNextDirection(i);
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }

    @Override
    public void evolve() {
        if (getInCave()) {
            lockedMovement();
            return;
        }
        if (!getVulnerable()) {
            pacManInFieldOfVision();
             if (getCurrentDirection()==-1) {
                if (!getVulnerable()) {
                    choseRandDirection();
                }
            }
        }
        move();
    }
}
