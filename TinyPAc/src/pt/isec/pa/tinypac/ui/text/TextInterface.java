package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;

import java.io.IOException;
public class TextInterface {
    private TextGraphics textGraphics;
    private Terminal terminal;
    public TextInterface() {
        this.terminal=null;
        this.textGraphics=null;
    }
    public void DrawMaze(Maze maze){
        try {
            terminal= new DefaultTerminalFactory().createTerminal();
            terminal.enterPrivateMode();
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            textGraphics=terminal.newTextGraphics();
            for(int i = 0; i<maze.getWidth();i++){
                for(int j = 0 ; j< maze.getHeight();j++){
                     IMazeElement temp=maze.get(j,i);
                     if(temp!=null){
                        textGraphics.setForegroundColor(temp.getColor());
                        textGraphics.putString(j,i,temp.getSymbol()+"");
                     }else{
                         textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
                         textGraphics.putString(j,i,"█");
                     }
                }
            }
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
            terminal.flush();
            KeyStroke keyStroke = terminal.readInput();

            while(keyStroke.getKeyType() != KeyType.Escape) {
                terminal.flush();
                keyStroke = terminal.readInput();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(terminal!=null){
                try {
                    terminal.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }



}
