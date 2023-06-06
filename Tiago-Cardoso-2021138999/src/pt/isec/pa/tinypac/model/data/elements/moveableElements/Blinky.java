package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;

public class Blinky extends Ghost {
    public Blinky( MazeInfo maze) {
        super('b', maze);
    }

    @Override
    public void evolve() {
        if(getInCave()) {
            lockedMovement();
            return;
        }
        if(!getVulnerable()) {
            if (getCurrentDirection() == -1) {
                choseRandDirection();
            }
        }
        move();
    }
}
