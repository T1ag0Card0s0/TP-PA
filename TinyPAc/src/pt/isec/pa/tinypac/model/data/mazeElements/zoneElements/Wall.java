package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class Wall implements IMazeElement {
    public Wall(){}
    @Override
    public TextColor getColor() {
        return TextColor.ANSI.BLUE;
    }
    @Override
    public char getSymbol() {
        return '█';
    }
}
