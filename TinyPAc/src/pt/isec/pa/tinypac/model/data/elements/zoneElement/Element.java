package pt.isec.pa.tinypac.model.data.elements.zoneElement;
import pt.isec.pa.tinypac.model.data.IMazeElement;

public class Element implements IMazeElement {
    private int x,y;
    private char symbol;
    public Element(char symbol,int x,int y){
        this.symbol=symbol;
        this.x=x;
        this.y=y;
    }
    public void setSymbol(char symbol){this.symbol=symbol;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    @Override
    public char getSymbol() {
        return symbol;
    }
}
