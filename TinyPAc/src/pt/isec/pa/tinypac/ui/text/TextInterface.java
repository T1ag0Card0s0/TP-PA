package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;

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
