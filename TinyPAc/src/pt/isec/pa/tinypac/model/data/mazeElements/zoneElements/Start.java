package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class Start implements IMazeElement {
    @Override
    public TextColor getColor() {
        return TextColor.ANSI.BLACK;
    }

    @Override
    public char getSymbol() {
        return ' ';
    }
}
