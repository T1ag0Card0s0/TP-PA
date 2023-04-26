package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;

public class Blinky extends Ghost {
    public Blinky(int x, int y, Maze maze) {
        super(x,y,'b', maze);
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
