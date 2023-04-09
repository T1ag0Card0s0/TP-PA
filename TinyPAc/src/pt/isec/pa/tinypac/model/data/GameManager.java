package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.*;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.*;
import pt.isec.pa.tinypac.ui.text.TextInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager {
    private final IGameEngine gameEngine;
    private int numFood;
    private final PacMan pacMan;
    private final ArrayList<ClientElement> elements;
    private final Maze maze;
    public GameManager(){
        this.maze=new Maze(32,32);
        this.elements=new ArrayList<>();
        this.pacMan=new PacMan(maze);
        this.gameEngine= new GameEngine();
        this.numFood=0;
        setNewLevel("Levels\\Level01.txt");
    }
    public Maze getMaze() {
        return maze;
    }
    private void addGhost(int x,int y){
        switch (elements.size()){
            case 0->elements.add(new Blinky(x,y,maze));
            case 1->elements.add(new Clyde(x,y,maze));
            case 2->elements.add(new Inky(x,y,maze));
            case 3->elements.add(new Pinky(x,y,maze));
        }

    }
    public void setNewLevel(String filePath){
        try {
            File file = new File(filePath);
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public IMazeElement elementFinder(char character,int x,int y){
        IMazeElement element=null;
        switch (character){
            case 'W'->{element= new Wraper(); pacMan.storeWraperCoordinates(x,y);}
            case 'o'->{element=new NormalFood();numFood++;}
            case 'F'->element=new Fruit();
            case 'M'->{pacMan.setxCoord(x);pacMan.setyCoord(y);}
            case 'O'->{element=new PowerFood();numFood++;}
            case 'Y' -> element = new GhostCaveDoor();
            case 'y'->{element=new GhostCave(); addGhost(x,y);}
            case 'x'->element=new Wall();
        }
        return element;
    }
    public void stopGame(){
        gameEngine.stop();
    }
    public void resumeGame(){
        gameEngine.resume();
    }
    public void startGame(TextInterface textInterface){
        MovePacMan movePacMan=new MovePacMan(pacMan,textInterface);
        AnimatePacMan animatePacMan=new AnimatePacMan(pacMan,textInterface,numFood);
        gameEngine.registerClient(animatePacMan);
        for(ClientElement element:elements){
            AnimateGhosts animateElement=new AnimateGhosts(element,textInterface);
            gameEngine.registerClient(animateElement);
        }
        gameEngine.registerClient(movePacMan);
        gameEngine.start(200);
        gameEngine.waitForTheEnd();
    }
}
