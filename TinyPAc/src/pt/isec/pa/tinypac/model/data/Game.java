package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elements.ZoneElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private final int [][]wraperCoordinates;
    private final int[]caveDoorCoords;
    private int index;
    private PacMan pacMan;
    private final ArrayList<Ghost> ghosts;
    private Maze maze;
    private int currentLevel;
    private  int numOfFood;
    public Game(){
        this.currentLevel=1;
        this.maze=new Maze(32,32);
        this.pacMan=null;
        this.ghosts=new ArrayList<>();
        this.wraperCoordinates=new int[2][2];
        this.index=0;
        this.numOfFood=0;
        this.caveDoorCoords=new int[2];
    }
    private String getLevelFilePath(){
        if(currentLevel<10)return "Levels\\Level0"+currentLevel+".txt";
        return "Levels\\Level"+currentLevel+".txt";
    }
    public void initGame(){
        try {
            File file = new File(getLevelFilePath());
            if(!file.exists()){
                currentLevel--;
                file=new File(getLevelFilePath());
            }
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int c;
            int row = 0, column = 0;
            while ((c = br.read()) != -1) {
                maze.set(column,row,elementFinder((char)c,column,row));
                column++;
                if ((char) c == '\n') {
                    row++;
                    column = 0;
                }
            }
            br.close();
            reader.close();
            pacMan.setWraperCoordinates(wraperCoordinates);
            for(Ghost element:ghosts){
                element.setCaveDoorCoords(caveDoorCoords);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private IMazeElement elementFinder(char c, int x, int y){
        ZoneElement element=new ZoneElement(c);
        switch (c){
            case 'M'->pacMan=new PacMan(x,y,maze);
            case 'y'->{addGhost(x,y);}
            case 'W'->storeWraperCoordinates(x,y);
            case 'O','o'->numOfFood++;
            case 'Y'->{caveDoorCoords[0]=x;caveDoorCoords[1]=y;}
        }
        return element;
    }
    public void storeWraperCoordinates( int x, int y) {
        wraperCoordinates[index][0] = x;
        wraperCoordinates[index][1] = y;
        index++;
    }
    public void setPacmanNextDirection(String keyPressed){
        pacMan.setNextDirection(keyPressed);
    }
    public void addGhost(int x,int y){
        if(ghosts.size()>4)return;
        switch (ghosts.size()){
            case 0-> ghosts.add(new Blinky(x,y,maze));
            case 1-> ghosts.add(new Clyde(x,y,maze));
            case 2-> ghosts.add(new Inky(x,y,maze));
            case 3-> ghosts.add(new Pinky(x,y,maze));
        }
    }
    public boolean NextLevel(){
        index=0;
        if(currentLevel>20)return false;
        currentLevel++;
        return true;
    }
    public boolean thereIsFood(){
        return !(numOfFood==pacMan.getNumOfFood());
    }
    public int getCurrentLevel(){return currentLevel;}
    public int getPacManLives(){return pacMan.getLives();}
    public int getPoints(){return pacMan.getPoints();}
    public int getBoardHeight(){return maze.getMaze().length;}
    public int getBoardWidth(){return maze.getMaze()[0].length;}
    public IMazeElement getMazeElement(int x,int y){return maze.get(x,y);}
    public int getNumOfFood(){return numOfFood;}
    public void setMaze(Maze newMaze){this.maze=newMaze;}
    public boolean pacManHasPower(){return pacMan.getPowerValue();}

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }
    public MoveableElement getPacMan(){return pacMan;}

    public boolean allGhostsInCave(){
        for(Ghost element:ghosts){
            if(!element.getInCave()) return false;
        }
        return true;
    }
}
