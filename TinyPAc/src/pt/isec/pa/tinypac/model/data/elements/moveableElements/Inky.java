package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;

public class Inky extends Ghost {
    private final int [][]corner;
    private int currentCorner;
    private final int width,height;
    public Inky(MazeInfo maze) {
        super('i',maze);
        this.corner=new int[][]{{maze.getWidth(),maze.getHeight()},{0,maze.getHeight()},{maze.getWidth(),0},{0,0}};
        this.currentCorner=0;
        width=maze.getWidth();
        height = maze.getHeight();
    }
    public void GoToCorner(){
        if(currentCorner> corner.length) currentCorner=0;
        if(Math.abs(getX()-corner[currentCorner][0])<=0.15*width
                &&Math.abs(getY()-corner[currentCorner][1])<=0.15*height) currentCorner++;
        travelTo(corner[currentCorner][0],corner[currentCorner][1]);
        System.out.println(currentCorner);
    }
    @Override
    public void evolve() {
        if (getInCave()) {
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
