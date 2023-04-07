package pt.isec.pa.tinypac.model.data.mazeElements;

import com.googlecode.lanterna.TextColor;

public interface IMazeElement {
    TextColor getColor();
    char getSymbol();  // returns the symbol of this element
    //   The list of symbols is available
    //   in the description of this work
}