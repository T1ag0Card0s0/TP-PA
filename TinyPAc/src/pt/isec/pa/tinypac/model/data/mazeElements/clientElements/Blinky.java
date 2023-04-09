package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;

public class Blinky  extends ClientElement {

    public Blinky(int xCoord, int yCoord, Maze maze){
        super(xCoord,yCoord,'█',TextColor.ANSI.RED,maze);
    }
}
