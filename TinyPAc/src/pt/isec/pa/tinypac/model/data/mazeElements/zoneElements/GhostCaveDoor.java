package pt.isec.pa.tinypac.model.data.mazeElements.zoneElements;


import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;

public class GhostCaveDoor implements IMazeElement {
    public GhostCaveDoor(){}
    @Override
    public TextColor getColor() {
        return TextColor.ANSI.MAGENTA;
    }

    @Override
    public char getSymbol() {
        return '▬';
    }
}
