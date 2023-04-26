package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;



public class Pinky extends Ghost {
    private final int [][]conner;
    private int nextConner;
    public Pinky(int x, int y, Maze maze) {
        super(x, y,'p', maze);
        char [][]symbols= maze.getMaze();
        this.conner=new int[][]{{0,0},{symbols[0].length,0},{0,symbols.length},{symbols[0].length,symbols.length}};
        this.nextConner=0;
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
