package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;



public class Pinky extends Ghost {
    private final int [][]corner;
    private int currentCorner;
    private final int width,height;
    public Pinky(int x, int y, Maze maze) {
        super(x, y,'p', maze);
        char [][]symbols= maze.getMaze();
        this.corner=new int[][]{{0,0},{symbols[0].length,0},{0,symbols.length},{symbols[0].length,symbols.length}};
        this.currentCorner=0;
        width=maze.getMaze()[0].length;
        height = maze.getMaze().length;
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
        } else if(!super.move()){
            choseRandDirection();
        }
    }

}
