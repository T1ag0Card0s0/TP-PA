package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.*;
import pt.isec.pa.tinypac.model.data.elements.zoneElement.ZoneElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameManager implements IGameEngineEvolve {
    private MazeInfo mazeInfo;
    private int currentLevel;
    private int vulnerableTicks;
    private long interval;
    private MoveableElement [] elements;
    public GameManager(){
        this.currentLevel=1;
        this.vulnerableTicks=0;
        this.interval=0;
        this.elements=new MoveableElement[5];
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
            initElementsPosition();
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
        ZoneElement element=new ZoneElement(c);
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
    private void initElementsPosition(){
        elements[0]=new Blinky(mazeInfo);
        elements[1]=new Clyde(mazeInfo);
        elements[2]=new Inky(mazeInfo);
        elements[3]=new Pinky(mazeInfo);
        if(elements[4]==null){
            elements[4]= new PacMan(mazeInfo);
        }else{
            PacMan pacMan=(PacMan)elements[4];
            pacMan.setWraperCoordinates(mazeInfo.getWrapperCoordinates());
            pacMan.setX(mazeInfo.getInitPacManPosition()[0]);
            pacMan.setY(mazeInfo.getInitPacManPosition()[1]);
            pacMan.setNextDirection(-1);
            pacMan.setMaze(mazeInfo);
            pacMan.setPoints(0);
        }
    }
    private String getLevelFilePath(){
        if(currentLevel<10)return "Levels\\Level0"+currentLevel+".txt";
        return "Levels\\Level"+currentLevel+".txt";
    }
    public boolean thereIsFood(){
        return mazeInfo.getNumOfFood() == ((PacMan) getElement('P')).getNumOfFood();
    }
    public MoveableElement getElement(char c){
        for (MoveableElement e: elements)
            if(e.getSymbol()==c)
                return e;
        return null;
    }
    public boolean LastLevel(){
        return currentLevel < 20;}
    public void setPacmanNextDirection(String keyPressed){
        PacMan pacMan = (PacMan) getElement('P');
        switch (keyPressed){
            case "ArrowUp"->pacMan.setNextDirection(0);
            case "ArrowRight"->pacMan.setNextDirection(1);
            case "ArrowDown"->pacMan.setNextDirection(2);
            case "ArrowLeft"->pacMan.setNextDirection(3);
        }
    }
    public boolean pacManWasEaten(){
        PacMan pacMan = (PacMan) getElement('P');
        if(pacMan.getPowerValue())return false;
        for(MoveableElement e: elements)
            if(e instanceof Ghost)
                if(e.getX()==pacMan.getX()&&e.getY()==pacMan.getY()) {
                    pacMan.setLives(pacMan.getLives()-1);
                    return true;
                }
        return false;
    }
    public void changelevel(){
        currentLevel++;
        elements=new MoveableElement[5];
        initGame();
    }
    public void setVulnerable(boolean value){
        for(MoveableElement e: elements)
            if(e instanceof Ghost g)
                g.setVulnerable(value);
            else
                ((PacMan)e).setPower(value);
    }
    public int getPoints(){return ((PacMan)getElement('P')).getPoints();}
    public int getCurrentLevel(){return currentLevel;}
    public int getBoardHeight(){return mazeInfo.getBoardHeight();}
    public int getBoardWidth(){return mazeInfo.getBoardWidth();}
    public int getPacManLives(){return ((PacMan)getElement('P')).getLives();}
    public char[][]getMazeSymbols(){return  mazeInfo.getMazeSymbols();}
    public MoveableElement[] getMoveableElements(){return elements;}
    public void setGameEngineInterval(long interval){
        this.interval=interval;
        for (MoveableElement element: elements){
            element.setGameEngineInterval(interval);
        }
    }
    public boolean endOfVulnerability(long interval){
        if(interval*(vulnerableTicks++)>((PacMan)getElement('P')).getPowerTime()){
            vulnerableTicks=0;
            setVulnerable(false);
            return true;
        }else{
            return false;
        }
    }
    public boolean pacManHasPower(){return ((PacMan)getElement('P')).getPowerValue();}
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        for(MoveableElement e: elements) {
            if(e instanceof Clyde c){
                c.setPCoords(getElement('P').getX(),getElement('P').getY());
            }
            e.evolve();
        }
    }
}
