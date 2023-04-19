package pt.isec.pa.tinypac.model.data.elements;
import pt.isec.pa.tinypac.model.data.IMazeElement;

public class ZoneElement implements IMazeElement {
    private char symbol;
    public ZoneElement(char symbol){
        this.symbol=symbol;
    }
    public void setSymbol(char symbol){this.symbol=symbol;}
    @Override
    public char getSymbol() {
        return symbol;
    }
}
