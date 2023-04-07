package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.ClientElement;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.Start;

import java.io.IOException;
public class TextInterface {
    private TextGraphics textGraphics;
    private Terminal terminal;
    public TextInterface() {
        this.terminal=null;
        this.textGraphics=null;
    }
    public void createWindow() throws IOException {
        TerminalSize size = new TerminalSize(50, 50);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
        terminalFactory.setTerminalEmulatorTitle("TinyPAc");
        terminal = terminalFactory.createTerminal();
        terminal.enterPrivateMode();
        terminal.clearScreen();
        terminal.setCursorVisible(false);
        textGraphics=terminal.newTextGraphics();
    }
    public void DrawMaze(Maze maze) {
        try {
            for (int i = 0; i < maze.getHeight(); i++) {
                for (int j = 0; j < maze.getWidth(); j++) {
                    IMazeElement temp = maze.get(i, j);
                    if (temp != null) {
                        textGraphics.setForegroundColor(temp.getColor());
                        textGraphics.putString(i, j, temp.getSymbol() + "");
                    } else {
                        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                    }
                }
            }
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void startGame(){
        IGameEngine gameEngine = new GameEngine();
        Maze maze = new Maze(32,32);
        maze.setNewLevel("Levels\\Level01.txt");
        PacMan pacMan=new PacMan(maze,this);
        maze.setPosition(pacMan);
        ShowBoard boardContent=new ShowBoard(maze,pacMan,this);
        gameEngine.registerClient(boardContent);
        gameEngine.registerClient(pacMan);
        gameEngine.start(100);
        gameEngine.waitForTheEnd();
    }
    public void DrawClientMazeElement(ClientElement element){
        try {
            element.move();
            textGraphics.setForegroundColor(element.getColor());
            textGraphics.putString(element.getxCoord(), element.getyCoord(), element.getSymbol() + "");
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public String ReadArrow() {
        String Key=" ";
        try {
            Key=terminal.readInput().getKeyType().toString();
            if(Key.equals("Escape"))  CloseTerminal();
        }catch (IOException e){
            e.printStackTrace();
        }
        return Key;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void CloseTerminal(){
        if(terminal!=null){
            try{
                terminal.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
