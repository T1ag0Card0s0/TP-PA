package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class Wrap implements IMazeElement {

    public Wrap(){}
    @Override
    public TextColor getColor() {
        return  TextColor.ANSI.WHITE;
    }
    @Override
    public char getSymbol() {
        return '█';
    }
}
