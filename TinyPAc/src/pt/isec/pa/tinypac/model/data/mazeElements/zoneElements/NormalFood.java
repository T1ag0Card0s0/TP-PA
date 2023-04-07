package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class NormalFood implements IMazeElement {


    public NormalFood( ){}
    @Override
    public TextColor getColor() {
        return TextColor.ANSI.YELLOW;
    }
    @Override
    public char getSymbol() {
        return '.';
    }
}
