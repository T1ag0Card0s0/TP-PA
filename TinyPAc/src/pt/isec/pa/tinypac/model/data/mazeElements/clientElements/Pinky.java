package pt.isec.pa.tinypac.model.data.mazeElements.clientElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.Maze;

public class Pinky extends ClientElement  {

    public Pinky(int xCoord, int yCoord, Maze maze){
        super(xCoord,yCoord,'█',new TextColor.RGB(255,184,255),maze);
    }

    }
