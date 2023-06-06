package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;

public class Inky extends Ghost {
    public Inky(int y, int x, MazeInfo mazeInfo) {
        super('i', y, x, mazeInfo);
    }

    @Override
    public void myMove() {
        randomMove();
        super.myMove();
    }
}
