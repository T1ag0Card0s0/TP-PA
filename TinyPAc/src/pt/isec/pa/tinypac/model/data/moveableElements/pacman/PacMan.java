package pt.isec.pa.tinypac.model.data.moveableElements.pacman;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;

public class PacMan extends MoveableElement {
    private boolean power;
    public PacMan(int y, int x, MazeInfo mazeInfo) {
        super('P',y,x,mazeInfo);
        this.power=false;
    }
    public boolean getPower(){return power;}
    public void setPower(boolean value){this.power = value;}
    @Override
    public String toString() {
        return "PacMan: "+super.toString()+"cor: "+"amarelo";
    }
}
