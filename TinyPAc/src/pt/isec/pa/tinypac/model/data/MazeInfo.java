package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.Element;

import javax.swing.plaf.PanelUI;

public class MazeInfo {
    private final Maze maze;
    private final MoveableElement [] elements;
    private Element fruit;
    private int numOfFood;
    private final int []caveDoorCoords;
    private final int [][]wrapperCoordinates;
    private int numOfWrapperCoordinates;
    private final int []initPacManPosition;
    private final int []initGhostsPosition;
    private final int currentLevel;
    private final int width,height;
    public MazeInfo(int height,int width,int currentLevel){
        this.width=width;
        this.height=height;
        this.maze=new Maze(height,width);
        this.numOfFood=0;
        this.wrapperCoordinates=new int[4][2];
        this.caveDoorCoords=new int [2];
        this.numOfWrapperCoordinates=0;
        this.initPacManPosition=new int[2];
        this.initGhostsPosition=new int[2];
        this.elements=new MoveableElement[5];
        this.currentLevel=currentLevel;
        System.out.println("Fui Construido");
    }

    public int getBoardHeight(){return maze.getMaze().length;}
    public int getBoardWidth(){return maze.getMaze()[0].length;}
    public char [][]getMazeSymbols(){return maze.getMaze();}
    public int getNumOfFood(){return numOfFood;}
    public int[] getInitPacManPosition(){return initPacManPosition;}
    public int[] getInitGhostsPosition(){return initGhostsPosition;}
    public int[] getCaveDoorCoords(){return caveDoorCoords;}
    public MoveableElement[] getMoveableElements() {return elements;}
    public int[][] getWrapperCoordinates() {return wrapperCoordinates;}
    public int getWidth(){return width;}
    public int getHeight() {return height;}
    public int getPacManPoints(){return ((PacMan)getMoveableElement('P')).getPoints();}
    public boolean pacManHasPower(){return ((PacMan)getMoveableElement('P')).getPowerValue();}
    public int getNumOfEatenFood(){return ((PacMan)getMoveableElement('P')).getNumOfFood();}
    public MoveableElement getMoveableElement(char c){
        for (MoveableElement e: elements) {
            if (e == null) continue;
            if (e.getSymbol() == c)
                return e;
        }
        return null;
    }
    public char getSymbol(int i,int j){return maze.getMaze()[i][j];}
    public IMazeElement getMazeElement(int i,int j){return maze.get(i,j);}
    public int getCurrentLevel(){return currentLevel;}
    public boolean allGhostsNotVulnerable(){
        for(MoveableElement e: elements){
            if(e instanceof Ghost g){
                if(g.getVulnerable()){
                    return false;
                }
            }
        }
        return true;
    }
    public void setWraperCoordinates(int x, int y){
        if(numOfWrapperCoordinates>wrapperCoordinates.length)return;
        wrapperCoordinates[numOfWrapperCoordinates][0]=x;
        wrapperCoordinates[numOfWrapperCoordinates][1]=y;
        numOfWrapperCoordinates++;
    }
    public void setCaveDoorCoords(int x,int y){
        caveDoorCoords[0]=x;
        caveDoorCoords[1]=y;
    }
    public void setInitPacManPosition(int x,int y){
        initPacManPosition[0]=x;
        initPacManPosition[1]=y;
    }
    public void setInitGhostsPosition(int x,int y){
        initGhostsPosition[0]=x;
        initGhostsPosition[1]=y;
    }
    public void setMazeElement(int i,int j,IMazeElement element){maze.set(i,j,element);}
    public void setNumOfFood(int value){numOfFood=value;}
    public void setEatenFood(int eatenFood){((PacMan)getMoveableElement('P')).setNumOfFood(eatenFood);}
    public void setVulnerable(boolean value){
        for(MoveableElement e: elements)
            if(e instanceof Ghost g)
                g.setVulnerable(value);
            else
                ((PacMan)e).setPower(value);
    }
    public void setPacmanNextDirection(String keyPressed){
        PacMan pacMan = (PacMan)getMoveableElement('P');
        switch (keyPressed){
            case "ArrowUp"->pacMan.setNextDirection(0);
            case "ArrowRight"->pacMan.setNextDirection(1);
            case "ArrowDown"->pacMan.setNextDirection(2);
            case "ArrowLeft"->pacMan.setNextDirection(3);
        }
    }
    public void setPoints(int points){((PacMan)getMoveableElement('P')).setPoints(points);}
    public void setFruitElement(Element fruit){this.fruit=fruit;}
    public void initElementsPosition(){
        for(MoveableElement m: elements)
            if(m!=null)
                maze.set(m.getX(),m.getY(),m.getUnderElement());
        elements[0]=new Blinky(this);
        elements[1]=new Clyde(this);
        elements[2]=new Inky(this);
        elements[3]=new Pinky(this);
        elements[4]= new PacMan(this);
        for(MoveableElement e: elements){
            maze.set(e.getX(),e.getY(),e);
        }
    }
    public void evolve(){
        for(MoveableElement e: elements) {
            if(e instanceof Ghost g){
                g.setPCoords(elements[4].getX(),elements[4].getY());
                if(g.getVulnerable()&&g.getX()==elements[4].getX()&&g.getY()==elements[4].getY())
                    ((PacMan)elements[4]).ateAGhost();
            }
            if(e instanceof PacMan p){
                if(p.getPowerValue())
                    setVulnerable(true);
                if(p.getNumOfFood()%20==0&&p.getNumOfFood()!=0){
                    fruit.setSymbol('F');
                    maze.set(fruit.getX(),fruit.getY(),fruit);
                }
            }

            e.evolve();
        }

    }
}
