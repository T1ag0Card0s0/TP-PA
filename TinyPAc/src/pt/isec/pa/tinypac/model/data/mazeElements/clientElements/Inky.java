package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;

public class Inky  extends ClientElement  {

    public Inky(int xCoord, int yCoord,Maze maze){
        super(xCoord,yCoord,'█',new TextColor.RGB(0,255,255),maze);
    }

}
