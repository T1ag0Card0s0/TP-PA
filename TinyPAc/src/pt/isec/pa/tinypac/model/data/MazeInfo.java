package pt.isec.pa.tinypac.model.data;

public class MazeInfo {
    private final Maze maze;
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
        System.out.println("Fui Construido");
    }
    public int getBoardHeight(){return maze.getMaze().length;}
    public int getBoardWidth(){return maze.getMaze()[0].length;}
    public char [][]getMazeSymbols(){return maze.getMaze();}
    public int getNumOfFood(){return numOfFood;}
    public int[] getInitPacManPosition(){return initPacManPosition;}
    public int[] getInitGhostsPosition(){return initGhostsPosition;}
    public int[] getCaveDoorCoords(){return caveDoorCoords;}

    public int[][] getWrapperCoordinates() {return wrapperCoordinates;}
    public int getWidth(){return width;}
    public int getHeight() {return height;}

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
    public void setMazeElement(int y,int x,IMazeElement element){maze.set(y,x,element);}
    public void setNumOfFood(int value){numOfFood=value;}
}
