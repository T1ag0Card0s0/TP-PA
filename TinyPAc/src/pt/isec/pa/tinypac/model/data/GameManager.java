package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.elements.ZoneElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.Ghost;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.PacMan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameManager implements IGameEngineEvolve {
    private Game game;
    private int currentLevel;
    private MoveableElement[] elements;
    private int vulnerableTicks;
    public GameManager(){
        currentLevel=1;
        game=initSize(getLevelFilePath());
        elements=new MoveableElement[5];
        vulnerableTicks=0;
    }
    private Game initSize(String filepath){
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
        return new Game(row+2,column+2);
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
                game.setMazeElement(column,row,elementFinder((char)c,column,row));
                column++;
                if ((char) c == '\n') {
                    row++;
                    column = 0;
                }
            }
            br.close();
            reader.close();
            PacMan pacMan = (PacMan) game.getPacMan();
            pacMan.setWraperCoordinates(game.getWraperCoordinates());
            elements[0]=pacMan;
            int i = 1;
            for(Ghost element: game.getGhosts()){
                element.setCaveDoorCoords(game.getCaveDoorCoords());
                elements[i++]=element;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private IMazeElement elementFinder(char c, int x, int y){
        ZoneElement element=new ZoneElement(c);
        switch (c){
            case 'M'->game.setPacMan(new PacMan(x,y,game.getMaze()));
            case 'y'->game.addGhost(x,y);
            case 'W'->game.storeWraperCoordinates(x,y);
            case 'O'->game.setNumOfFood(game.getNumOfFood()+1);
            case 'o'->{game.setNumOfFood(game.getNumOfFood()+1);element.setSymbol('.');}
            case 'Y'->game.storeCaveDoorCoords(x,y);
        }
        return element;
    }
    private String getLevelFilePath(){
        System.out.println("Nivel:"+currentLevel);
        if(currentLevel<10)return "Levels\\Level0"+currentLevel+".txt";
        return "Levels\\Level"+currentLevel+".txt";
    }
    public boolean WinLevel(){
        return !game.thereIsFood();
    }
    public boolean LastLevel(){
        return currentLevel < 20;}
    public void setPacmanNextDirection(String keyPressed){
        PacMan pacMan = (PacMan) game.getPacMan();
        switch (keyPressed){
            case "ArrowUp"->pacMan.setNextDirection(0);
            case "ArrowRight"->pacMan.setNextDirection(1);
            case "ArrowDown"->pacMan.setNextDirection(2);
            case "ArrowLeft"->pacMan.setNextDirection(3);
        }
    }
    public void changelevel(){
        currentLevel++;
        elements=new MoveableElement[5];
        game=initSize(getLevelFilePath());
    }
    public int getPoints(){return game.getPoints();}
    public int getCurrentLevel(){return currentLevel;}
    public int getBoardHeight(){return game.getBoardHeight();}
    public int getBoardWidth(){return game.getBoardWidth();}
    public int getPacManLives(){return game.getPacManLives();}
    public char[][]getMazeSymbols(){return  game.getMazeSymbols();}
    public MoveableElement[] getMoveableElements(){
        return elements;
    }
    public void setVulnerable(boolean value){
        game.setGhostsVulnerable(value);
    }
    public boolean endOfVulnerability(long interval){
        System.out.println("TimePassed: "+(interval*vulnerableTicks));
        if(interval*(vulnerableTicks++)>game.getVulnerableTime()){
            vulnerableTicks=0;
            return true;
        }else{
            return false;
        }
    }
    public boolean pacManHasPower(){return game.pacManHasPower();}
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        if(game==null)
            return;
        if(game.evolve()) {
            gameEngine.stop();
        }
    }
    @Override
    public String toString() {
        return "Nivel: "+currentLevel+"\nPontuação: "+getPoints()+"\nVidas: "+game.getPacManLives();
    }
}
