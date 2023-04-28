package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;

public class MazeInfo {
    private final Maze maze;
    private final MoveableElement [] elements;
    private int numOfFood;
    private final int []caveDoorCoords;
    private final int [][]wrapperCoordinates;
    private int numOfWrapperCoordinates;
    private final int []initPacManPosition;
    private final int []initGhostsPosition;
    private final int width,height;
    public MazeInfo(int height,int width){
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
        System.out.println("Fui Construido");
    }
    public int getBoardHeight(){return maze.getMaze().length;}
    public int getBoardWidth(){return maze.getMaze()[0].length;}
    public char [][]getMazeSymbols(){return maze.getMaze();}
    public int getNumOfFood(){return numOfFood;}
    public int[] getInitPacManPosition(){return initPacManPosition;}
    public int[] getInitGhostsPosition(){return initGhostsPosition;}
    public int[] getCaveDoorCoords(){return caveDoorCoords;}
    public MoveableElement[] getElements() {return elements;}

    public int[][] getWrapperCoordinates() {return wrapperCoordinates;}
    public int getWidth(){return width;}
    public int getHeight() {return height;}
    public MoveableElement getElement(char c){
        for (MoveableElement e: elements)
            if(e.getSymbol()==c)
                return e;
        return null;
    }
    public char getSymbol(int i,int j){return maze.getMaze()[i][j];}

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
    public void initElementsPosition(){
        elements[0]=new Blinky(this);
        elements[1]=new Clyde(this);
        elements[2]=new Inky(this);
        elements[3]=new Pinky(this);
        if(elements[4]==null){
            elements[4]= new PacMan(this);
        }else{
            PacMan pacMan=(PacMan)elements[4];
            pacMan.setWraperCoordinates(getWrapperCoordinates());
            pacMan.setX(getInitPacManPosition()[0]);
            pacMan.setY(getInitPacManPosition()[1]);
            pacMan.setNextDirection(-1);
            pacMan.setMaze(this);
            pacMan.setPoints(0);
        }
        for(MoveableElement e: elements){
            maze.set(e.getX(),e.getY(),e);
        }
    }

    public void evolve(){
      /*  for(char arr[]: maze.getMaze()){
            for(char c: arr){
                System.out.print(c);
            }
            System.out.println();
        }*/
        for(MoveableElement e: elements) {
            if(e instanceof Clyde c){
                c.setPCoords(getElement('P').getX(),getElement('P').getY());
            }
            e.evolve();
        }
    }
}
