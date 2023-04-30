package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    private MazeInfo mazeInfo;
    private int currentLevel;
    private int vulnerableTicks;
    private int lives;
    private int points;
    private int numOfEatenFood;
    public Game(){
        this.currentLevel=1;
        this.vulnerableTicks=0;
        this.lives=3;
        this.points=0;
        this.numOfEatenFood=0;
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
        return new MazeInfo(row+2,column+2,currentLevel);
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
            case 'F'->{element.setSymbol(' ');mazeInfo.setFruitElement(element);}
        }
        return element;
    }
    public void initElementsPosition(){
        mazeInfo.initElementsPosition();
        mazeInfo.setPoints(points);
        mazeInfo.setEatenFood(numOfEatenFood);
    }
    private String getLevelFilePath(){
        if(currentLevel<10)return "Levels\\Level0"+currentLevel+".txt";
        return "Levels\\Level"+currentLevel+".txt";
    }
    public boolean thereIsNoFood(){return (mazeInfo.getNumOfFood() == mazeInfo.getNumOfEatenFood());}
    public boolean LastLevel(){return currentLevel >= 20;}
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
        numOfEatenFood=0;
        currentLevel++;
        initGame();
    }
    public int getPoints(){return points=mazeInfo.getPacManPoints();}
    public int getCurrentLevel(){return currentLevel;}
    public int getBoardHeight(){return mazeInfo.getBoardHeight();}
    public int getBoardWidth(){return mazeInfo.getBoardWidth();}
    public int getPacManLives(){return lives;}
    public char[][]getMazeSymbols(){return  mazeInfo.getMazeSymbols();}
    public MoveableElement[] getMoveableElements(){return mazeInfo.getMoveableElements();}
    public IMazeElement getMazeElement(int x,int y){return mazeInfo.getMazeElement(x,y);}
    public void setGameEngineInterval(long interval){
        for (MoveableElement element: mazeInfo.getMoveableElements()){
            element.setGameEngineInterval(interval);
        }
    }
    public void setVulnerable(boolean value){mazeInfo.setVulnerable(value);}
    public void setPacmanNextDirection(String keyPressed){mazeInfo.setPacmanNextDirection(keyPressed);}
    public boolean endOfVulnerability(long interval){
        if(mazeInfo.allGhostsNotVulnerable()){
            vulnerableTicks=0;
            setVulnerable(false);
            return true;
        }
        if(interval*(vulnerableTicks++)>((PacMan)mazeInfo.getMoveableElement('P')).getPowerTime()){
            vulnerableTicks=0;
            setVulnerable(false);
            return true;
        }else{
            return false;
        }
    }
    public boolean pacManHasPower(){return mazeInfo.pacManHasPower();}
    public void evolve() {
       mazeInfo.evolve();
    }
}
