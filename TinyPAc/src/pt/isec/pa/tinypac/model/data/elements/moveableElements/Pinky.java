package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;


public class Pinky extends Ghost {
    private final int [][]corner;
    private int currentCorner;
    private final int width,height;
    public Pinky( MazeInfo maze) {
        super('p', maze);
        this.corner=new int[][]{{0,0},{maze.getWidth(),0},{0,maze.getHeight()},{maze.getWidth(),maze.getHeight()}};
        this.currentCorner=0;
        width=maze.getWidth();
        height = maze.getHeight();
    }
   public boolean GoToCorner(){
        if(Math.abs(getX()-corner[currentCorner][0])<=0.15*width&&Math.abs(getY()-corner[currentCorner][1])<=0.15*height) currentCorner++;
        if(currentCorner> corner.length) currentCorner=0;
        return true;
   }
    @Override
    public void evolve() {
        if(getInCave()) {
            lockedMovement();
            return;
        }
        if (!getVulnerable()) {
            if(getCurrentDirection()==-1)
                choseRandDirection();
        }
        move();
    }

}
