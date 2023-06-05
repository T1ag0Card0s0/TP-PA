package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;

public class Clyde extends Ghost {
    public Clyde(int y, int x, MazeInfo mazeInfo) {
        super('c',y,x,mazeInfo);
    }
    public boolean pacManInFieldOfVision(){
        int i = 0;
        for(boolean b: getNeighboors()){
            if(!b){
                int x=getX(),y=getY();
                while (getMazeElementSymbol(y,x)!='x'){
                    switch (i){
                        case 0-> y--;
                        case 1-> x++;
                        case 2-> y++;
                        case 3-> x--;
                    }
                    if(x==getXPacMan()&&getYPacMan()==y){
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
    public void myMove() {
        if(!pacManInFieldOfVision())
            randomMove();
        super.myMove();
    }
    @Override
    public String toString() {
        return "Clyde: "+super.toString()+"cor: "+"laranja";
    }
}
