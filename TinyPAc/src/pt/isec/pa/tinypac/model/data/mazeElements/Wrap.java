package pt.isec.pa.tinypac.model.data.mazeElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.awt.*;

public class Wrap implements IMazeElement {
    private char symbol;
    private final TextColor cor = TextColor.ANSI.WHITE;
    public Wrap(char symbol){
        this.symbol=symbol;
    }
    @Override
    public TextColor getColor() {
        return cor;
    }
    @Override
    public char getSymbol() {
        return this.symbol;
    }
}
