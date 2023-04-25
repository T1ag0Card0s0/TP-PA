package pt.isec.pa.tinypac.model.data.elements.moveableElements;

import pt.isec.pa.tinypac.model.data.Maze;
public class Clyde extends Ghost {

    public Clyde(int x, int y, Maze maze) {
        super(x, y,'c',maze);
    }
    @Override
    public boolean move() {
        if(getInCave()) {
            lockedMovement();
        } else if(!super.move()){
            choseRandDirection();
        }
        return true;
    }
}
