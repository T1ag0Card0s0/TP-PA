package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;

public class Pinky extends Ghost {

    public Pinky(int y, int x, MazeInfo mazeInfo) {
        super('p',y,x,mazeInfo);
    }
    @Override
    public void myMove() {
        randomMove();
        super.myMove();
    }
}
