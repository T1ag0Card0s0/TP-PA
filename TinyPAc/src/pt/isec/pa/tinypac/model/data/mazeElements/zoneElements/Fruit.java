package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class Fruit implements IMazeElement {

    public Fruit(){}
    @Override
    public TextColor getColor() {
        return TextColor.ANSI.MAGENTA;
    }
    @Override
    public char getSymbol() {
        return 'F';
    }
}
