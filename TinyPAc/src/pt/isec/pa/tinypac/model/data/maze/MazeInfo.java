package pt.isec.pa.tinypac.model.data.maze;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Blinky;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Clyde;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Inky;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.moveableElements.pacman.PacMan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MazeInfo implements Serializable {
    private final Maze maze;
    private final MoveableElement[] moveableElements;
    private Element fruit;
    private final Element[] wrap;
    private final int[] caveDoors;
    private final int [] initGhostXY;
    private final int[] initPacManXY;
    private final int heigth, width;
    private boolean ghostsAdded;
    private int wrapindex, eatenGhost, count, numOfFood;
    public MazeInfo(int heigh,int width){
        maze = new Maze(heigh,width);
        this.caveDoors=new int[2];
        this.heigth=heigh;
        this.width=width;
        this.moveableElements=new MoveableElement[5];
        this.ghostsAdded=false;
        this.wrapindex=0;
        this.wrap=new Element[2];
        this.numOfFood=0;
        this.initGhostXY=new int[2];
        this.initPacManXY=new int[2];
        this.count = 0;
        this.eatenGhost=0;
    }
    public void InitElemPos(){
        for(MoveableElement moveableElement: moveableElements){
            moveableElement.setUnderElement((Element) maze.get(moveableElement.getInitY(),moveableElement.getInitX()));
            moveableElement.setXY(moveableElement.getInitX(),moveableElement.getInitY());
        }
    }
    public char pacManAte(){
        PacMan pacman = (PacMan) getMoveableElement('P');
        if(pacman==null){return ' ';}
        for (MoveableElement e : moveableElements) {
            if (e instanceof Ghost) {
                if (e.getY() == pacman.getY()&&e.getX() == pacman.getX()) {
                    if (pacman.getPower()) {
                        e.setUnderElement((Element) maze.get(initGhostXY[1], initGhostXY[0]));
                        e.setXY(initGhostXY[0], initGhostXY[1]);
                        eatenGhost++;
                    } else {
                        pacman.setUnderElement((Element) maze.get(initPacManXY[1], initPacManXY[0]));
                        pacman.setXY(initPacManXY[0], initPacManXY[1]);
                    }
                    return e.getSymbol();
                }
            }
        }
        if(pacman.getUnderElement()==null)return ' ';
        if(pacman.getUnderElement().getSymbol()=='F'){ count = 0;fruit.setSymbol(' ');}
        else if(pacman.getUnderElement().getSymbol() == '.'||pacman.getUnderElement().getSymbol()=='O')count++;
        if(count > 20){fruit.setSymbol('F');}
        else fruit.setSymbol(' ');
        return pacman.getUnderElement().getSymbol();
    }
    
    public void teleTransportPacMan(){
        MoveableElement e = getMoveableElement('P');
        if(e==null)return;
        if(e.getX()==wrap[0].getX()&&e.getY()==wrap[0].getY())
            e.setXY(wrap[1].getX(),wrap[1].getY());
        else
            e.setXY(wrap[0].getX(),wrap[0].getY());
    }
    public void initLevel(){
        for(MoveableElement moveableElement:moveableElements){
            moveableElement.setXY(moveableElement.getInitX(),moveableElement.getInitY());
        }
    }
    public boolean getPacManPower(){
        PacMan pacMan=(PacMan) getMoveableElement('P');
        if(pacMan==null)return false;
        return pacMan.getPower();
    }
    public int getNumOfEatenGhost(){return eatenGhost;}
    public MoveableElement []getMoveableElements(){
        return moveableElements;
    }
    public int getWidth(){return width;}
    public int getHeigth(){return heigth;}
    public int getPacManDirection(){return getMoveableElement('P').getCurrentDirection();}
    public IMazeElement get(int y,int x){return maze.get(y,x);}
    public int []getCaveDoors(){return caveDoors;}
    public char[][]getBoard(){return maze.getMaze().clone();}
    public MoveableElement getMoveableElement(char c){
        for(MoveableElement e:moveableElements)
            if(e.getSymbol() == c)
                return e;
        return null;
    }
    public int getNumOfFood() {return numOfFood;}
    public int[] getInitGhostXY() {return initGhostXY;}
    public int[] getInitPacManXY() {return initPacManXY;}
    public void setPacManNextDirection(int nextDirection){moveableElements[4].setNextDirection(nextDirection);}
    public void setInitElements(Element element){
        if(element==null) return;
        maze.set(element.getY(), element.getX(), element);
        switch (element.getSymbol()) {
            case 'y' -> {
                if (!ghostsAdded) {
                    moveableElements[0] = new Blinky(element.getY(),element.getX(),this);
                    moveableElements[1] = new Clyde(element.getY(),element.getX(),this);
                    moveableElements[2] = new Inky(element.getY(),element.getX(),this);
                    moveableElements[3] = new Pinky(element.getY(),element.getX(),this);
                    ghostsAdded=true;
                    initGhostXY[0]=element.getX();
                    initGhostXY[1]=element.getY();
                }
            }
            case 'W'->{
                if(wrapindex<2) {
                    wrap[wrapindex] = element;
                    wrapindex++;
                }
            }
            case 'Y'->{caveDoors[0]=element.getY();caveDoors[1]= element.getX();}
            case 'M' -> {
                initPacManXY[0]=element.getX();initPacManXY[1]=element.getY();
                if (moveableElements[4] == null)
                    moveableElements[4] = new PacMan(element.getY(), element.getX(),this);
            }
            case 'F'->{
                fruit=new Element(' ',element.getY(),element.getX());
                maze.set(fruit.getY(),fruit.getX(),fruit);
            }
            case 'o'->{element.setSymbol('.');numOfFood++;}
            case 'O'->numOfFood++;
        }
    }
    public void setVulnerable(boolean value){
        for(MoveableElement element:moveableElements)
            if(element instanceof Ghost ghost) ghost.setVulnerable(value);
    }
    public void set(int y,int x,IMazeElement element){
        maze.set(y,x,element);
    }
    public void setNumOfFood(int numOfFood) {
        this.numOfFood = numOfFood;
    }
}
