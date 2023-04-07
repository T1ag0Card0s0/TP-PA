package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class GhostCave implements IMazeElement {


    public GhostCave( ){}
    @Override
    public TextColor getColor() {
        return TextColor.ANSI.BLACK;
    }
    @Override
    public char getSymbol() {
        return ' ';
    }
}
