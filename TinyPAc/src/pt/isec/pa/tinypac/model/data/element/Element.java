package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.maze.IMazeElement;


public class Element implements IMazeElement,Cloneable {
    private char symbol;
    private int x,y;
    public Element(char symbol,int y,int x){
        this.symbol=symbol;
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setSymbol(char symbol){this.symbol=symbol;}
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setXY(int x,int y){
        setX(x);setY(y);
    }
    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
