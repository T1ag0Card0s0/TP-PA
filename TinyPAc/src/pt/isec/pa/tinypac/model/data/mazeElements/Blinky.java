package pt.isec.pa.tinypac.model.data.mazeElements;

import com.googlecode.lanterna.TextColor;
import pt.isec.pa.tinypac.model.data.IMazeElement;

import java.awt.Color;

public class Blinky implements IMazeElement {
    private char symbol;
    private final TextColor cor = TextColor.ANSI.RED;
    public Blinky(char symbol){
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
