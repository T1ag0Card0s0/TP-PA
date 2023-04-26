package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;
public class Clyde extends Ghost {
    int xPCoord,yPCoord;
    public Clyde(int x, int y, Maze maze) {
        super(x, y,'c',maze);

    }
    public boolean pacManInFieldOfVision(){
        char[][] c=getMaze();
        /*for(int i = 0; i<c[i].length;i++){
            for (char[] chars : c) {
                System.out.print(chars[i]);
            }
            //System.out.println();
        }*/
        int i = 0;
        for(boolean b: getNeighboors()){
            if(b){
                int x=getX(),y=getY();
                while (c[x][y]!='x'){
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
    public boolean move() {
        if(getInCave()) {
            lockedMovement();
        } else if(!pacManInFieldOfVision()&&!super.move()){
            choseRandDirection();
            return true;
        }

        return true;
    }
}
