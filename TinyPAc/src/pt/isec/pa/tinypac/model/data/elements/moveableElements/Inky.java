package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;

public class Inky extends Ghost {

    public Inky(int x, int y, Maze maze) {
        super(x, y,'i',maze);
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
