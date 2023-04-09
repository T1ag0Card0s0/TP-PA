package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;

public class Clyde extends ClientElement  {

    public Clyde(int xCoord, int yCoord,Maze maze){
        super(xCoord,yCoord,'█',new TextColor.RGB(255,184,82),maze);
    }


}
