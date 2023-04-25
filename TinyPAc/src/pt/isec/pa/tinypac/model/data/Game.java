package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;

import java.util.ArrayList;

public class Game {
    private final int [][]wraperCoordinates;
    private final int[]caveDoorCoords;
    private int index;
    private PacMan pacMan;
    private final ArrayList<Ghost> ghosts;
    private Maze maze;
    private int currentLevel;
    private int numOfFood;
    public Game(int height,int width){
        this.currentLevel = 1;
        this.maze=new Maze(height,width);
        this.pacMan=null;
        this.ghosts=new ArrayList<>();
        this.wraperCoordinates=new int[2][2];
        this.index=0;
        this.numOfFood=0;
        this.caveDoorCoords=new int[2];
    }
    public void storeWraperCoordinates( int x, int y) {
        wraperCoordinates[index][0] = x;
        wraperCoordinates[index][1] = y;
        index++;
    }
    public void storeCaveDoorCoords(int x,int y){caveDoorCoords[0]=x;caveDoorCoords[1]=y;}
    public void setPacmanNextDirection(String keyPressed){if(pacMan!=null)pacMan.setNextDirection(keyPressed);}
    public void addGhost(int x,int y){
        if(ghosts.size()>4)return;
        switch (ghosts.size()){
            case 0-> ghosts.add(new Blinky(x,y,maze));
            case 1-> ghosts.add(new Clyde(x,y,maze));
            case 2-> ghosts.add(new Inky(x,y,maze));
            case 3-> ghosts.add(new Pinky(x,y,maze));
        }
    }
    public void NextLevel(){
        if(currentLevel>20)return;
        currentLevel++;
    }
    public boolean thereIsFood(){
        if(pacMan==null)return false;
        return !(numOfFood==pacMan.getNumOfFood());
    }
    public int[]getCaveDoorCoords(){return caveDoorCoords;}
    public int getCurrentLevel(){return currentLevel;}
    public int getPacManLives(){return pacMan.getLives();}
    public int getPoints(){return pacMan.getPoints();}
    public int getBoardHeight(){return maze.getMaze().length;}
    public int getBoardWidth(){return maze.getMaze()[0].length;}
    public char [][]getMazeSymbols(){return maze.getMaze();}
    public int getNumOfFood(){return numOfFood;}
    public void setNumOfFood(int value){numOfFood=value;}
    public void setPacMan(PacMan pacMan){this.pacMan=pacMan;}
    public Maze getMaze() {return maze;}
    public void setMaze(Maze newMaze){this.maze=newMaze;}
    public boolean pacManHasPower(){return pacMan.getPowerValue();}
    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }
    public MoveableElement getPacMan(){return pacMan;}
    public int [][]getWraperCoordinates(){return wraperCoordinates;}
    public boolean allGhostsInCave(){
        for(Ghost element:ghosts){
            if(!element.getInCave()) return false;
        }
        return true;
    }
    public void setMazeElement(int y,int x,IMazeElement element){maze.set(y,x,element);}
    public boolean evolve(){
        pacMan.move();
        for(Ghost ghost: ghosts){
            ghost.move();
        }
        return false;
    }
    public void setGhostsVulnerable(boolean value){
        pacMan.setPower(value);
        for (Ghost g: ghosts){
            g.setVulnerable(value);
        }
    }
    public int getVulnerableTime(){
        return pacMan.getPowerTime();
    }
}
