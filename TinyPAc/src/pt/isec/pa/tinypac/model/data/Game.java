package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;
import java.util.ArrayList;

public class Game {
    private final int [][]wraperCoordinates;
    private final int[]caveDoorCoords;
    private int index;
    private PacMan pacMan;
    private final ArrayList<Ghost> ghosts;
    private final Maze maze;
    private int numOfFood;
    public Game(int height,int width){
        this.maze=new Maze(height,width);
        this.pacMan=null;
        this.ghosts=new ArrayList<>();
        this.wraperCoordinates=new int[2][2];
        this.index=0;
        this.numOfFood=0;
        this.caveDoorCoords=new int[2];
    }
    public boolean thereIsFood(){
        if(pacMan==null)return false;
        return !(numOfFood==pacMan.getNumOfFood());
    }
    public boolean evolve(){
        pacMan.evolve();
        for(Ghost ghost: ghosts){
            if(ghost instanceof Clyde c){
                c.setPCoords(pacMan.getX(),pacMan.getY());
            }
            ghost.evolve();
        }
        return false;
    }
    public int[]getCaveDoorCoords(){return caveDoorCoords;}
    public int getPacManLives(){return pacMan.getLives();}
    public int getPoints(){return pacMan.getPoints();}
    public int getBoardHeight(){return maze.getMaze().length;}
    public int getBoardWidth(){return maze.getMaze()[0].length;}
    public char [][]getMazeSymbols(){return maze.getMaze();}
    public int getNumOfFood(){return numOfFood;}
    public Maze getMaze() {return maze;}
    public boolean pacManHasPower(){return pacMan.getPowerValue();}
    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }
    public MoveableElement getPacMan(){return pacMan;}
    public int [][]getWraperCoordinates(){return wraperCoordinates;}
    public int getVulnerableTime(){
        return pacMan.getPowerTime();
    }
    public boolean allGhostsInCave(){
        for(Ghost element:ghosts){
            if(!element.getInCave()) return false;
        }
        return true;
    }
    public void setMazeElement(int y,int x,IMazeElement element){maze.set(y,x,element);}
    public void setGhostsVulnerable(boolean value){
        pacMan.setPower(value);
        for (Ghost g: ghosts){
            g.setVulnerable(value);
        }
    }
    public void setNumOfFood(int value){numOfFood=value;}
    public void setPacMan(PacMan pacMan){this.pacMan=pacMan;}
    public void storeWraperCoordinates( int x, int y) {
        wraperCoordinates[index][0] = x;
        wraperCoordinates[index][1] = y;
        index++;
    }
    public void storeCaveDoorCoords(int x,int y){caveDoorCoords[0]=x;caveDoorCoords[1]=y;}
    public void addGhost(int x,int y){
        if(ghosts.size()!=0)return;
        ghosts.add(new Blinky(x,y,maze));
        ghosts.add(new Clyde(x,y,maze));
        ghosts.add(new Inky(x,y,maze));
        ghosts.add(new Pinky(x,y,maze));
    }

}
