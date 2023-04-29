package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game implements IGameEngineEvolve {
    private MazeInfo mazeInfo;
    private int currentLevel;
    private int vulnerableTicks;
    private long interval;
    private int lives;
    private int points;
    public Game(){
        this.currentLevel=1;
        this.vulnerableTicks=0;
        this.interval=0;
        this.lives=3;
        this.points=0;
        initGame();
    }
    public void initGame(){
        this.mazeInfo=initSize(getLevelFilePath());
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
                mazeInfo.setMazeElement(column,row,elementFinder((char)c,column,row));
                column++;
                if ((char) c == '\n') {
                    row++;
                    column = 0;
                }
            }
            br.close();
            reader.close();
            mazeInfo.initElementsPosition();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private MazeInfo initSize(String filepath){
        int row = 0, column = 0;
        try {
            File file = new File(filepath);
            if(!file.exists()){
                currentLevel--;
                file=new File(filepath);
            }
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int c;
            while ((c = br.read()) != -1) {
                column++;
                if ((char) c == '\n') {
                    row++;
                    column = 0;
                }
            }
            br.close();
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return new MazeInfo(row+2,column+2);
    }
    private IMazeElement elementFinder(char c, int x, int y){
        Element element=new Element(c,x,y);
        switch (c){
            case 'M'->mazeInfo.setInitPacManPosition(x,y);
            case 'y'->mazeInfo.setInitGhostsPosition(x,y);
            case 'W'->mazeInfo.setWraperCoordinates(x,y);
            case 'O'->mazeInfo.setNumOfFood(mazeInfo.getNumOfFood()+1);
            case 'o'->{mazeInfo.setNumOfFood(mazeInfo.getNumOfFood()+1);element.setSymbol('.');}
            case 'Y'->mazeInfo.setCaveDoorCoords(x,y);
        }
        return element;
    }
    public void initElementsPosition(){
        mazeInfo.initElementsPosition();
        ((PacMan)mazeInfo.getMoveableElement('P')).setPoints(points);
    }
    private String getLevelFilePath(){
        if(currentLevel<10)return "Levels\\Level0"+currentLevel+".txt";
        return "Levels\\Level"+currentLevel+".txt";
    }
    public boolean thereIsFood(){
        return mazeInfo.getNumOfFood() == ((PacMan)mazeInfo.getMoveableElement('P')).getNumOfFood();
    }
    public boolean LastLevel(){
        return currentLevel >= 20;}
    public boolean pacManWasEaten(){
        PacMan pacMan = (PacMan) mazeInfo.getMoveableElement('P');
        if(pacMan.getPowerValue())return false;
        for(MoveableElement e: mazeInfo.getMoveableElements())
            if(e instanceof Ghost)
                if(e.getX()==pacMan.getX()&&e.getY()==pacMan.getY()) {
                    lives--;
                    return true;
                }
        return false;
    }
    public void changelevel(){
        points=0;
        lives=3;
        currentLevel++;
        initGame();
    }
    public int getPoints(){return points=((PacMan)mazeInfo.getMoveableElement('P')).getPoints();}
    public int getCurrentLevel(){return currentLevel;}
    public int getBoardHeight(){return mazeInfo.getBoardHeight();}
    public int getBoardWidth(){return mazeInfo.getBoardWidth();}
    public int getPacManLives(){return lives;}
    public char[][]getMazeSymbols(){return  mazeInfo.getMazeSymbols();}
    public MoveableElement[] getMoveableElements(){return mazeInfo.getMoveableElements();}
    public void setGameEngineInterval(long interval){
        this.interval=interval;
        for (MoveableElement element: mazeInfo.getMoveableElements()){
            element.setGameEngineInterval(interval);
        }
    }
    public void setVulnerable(boolean value){mazeInfo.setVulnerable(value);}
    public void setPacmanNextDirection(String keyPressed){mazeInfo.setPacmanNextDirection(keyPressed);}
    public void setLives(int value){this.lives=value;}
    public void setPoints(int points){this.points=points;}
    public boolean endOfVulnerability(long interval){
        if(interval*(vulnerableTicks++)>((PacMan)mazeInfo.getMoveableElement('P')).getPowerTime()){
            vulnerableTicks=0;
            setVulnerable(false);
            return true;
        }else{
            return false;
        }
    }
    public boolean pacManHasPower(){return ((PacMan)mazeInfo.getMoveableElement('P')).getPowerValue();}
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
       mazeInfo.evolve();
    }
}
