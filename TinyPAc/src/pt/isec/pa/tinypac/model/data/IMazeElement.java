package pt.isec.pa.tinypac.model.data;

import com.googlecode.lanterna.TextColor;

import java.awt.*;

public interface IMazeElement {
    TextColor getColor();
    char getSymbol();  // returns the symbol of this element
    //   The list of symbols is available
    //   in the description of this work
}