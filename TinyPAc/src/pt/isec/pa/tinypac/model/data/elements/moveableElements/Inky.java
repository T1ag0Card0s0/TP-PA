package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.MazeInfo;

public class Inky extends Ghost {

    public Inky(MazeInfo maze) {
        super('i',maze);
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
