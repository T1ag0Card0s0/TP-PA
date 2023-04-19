package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.PacMan;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class TextInterface {
    static GameContext fsm;
    private static TextGraphics textGraphics;
    private static Terminal terminal;
    private boolean finish;
    public TextInterface(GameContext context) {
        TextInterface.fsm =context;
        terminal=null;
        textGraphics=null;
        finish=false;
    }
    public void createWindow() {
        try {
            TerminalSize size = new TerminalSize(50, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
            terminalFactory.setTerminalEmulatorTitle("TinyPAc");
            terminal = terminalFactory.createTerminal();
            terminal.enterPrivateMode();
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            textGraphics = terminal.newTextGraphics();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void start() {
        createWindow();
        DrawMaze();
        DrawMoveableElement();
        try {
            while (!finish) {
                DrawInfoSection();
                DrawMoveableElement();
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke != null) {
                    fsm.DirectionKeyIsPressed(keyStroke.getKeyType().toString());
                    if (Objects.requireNonNull(keyStroke.getKeyType()) == KeyType.Escape) {
                        finish = true;
                    }
                }
            }
            CloseTerminal();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public TextColor getColor(char c){
        TextColor color=TextColor.ANSI.BLACK;
        switch (c){
            case 'x'->color = TextColor.ANSI.BLUE;
            case 'W'->color =TextColor.ANSI.WHITE;
            case 'o', 'O' ->color = TextColor.ANSI.YELLOW;
            case 'F', 'Y' ->color=TextColor.ANSI.MAGENTA;
        }
        return color;
    }
    public void DrawMaze() {
        try {
            for (int i = 0; i < fsm.getBoardHeight(); i++) {
                for (int j = 0; j < fsm.getBoardWidth(); j++) {
                    IMazeElement temp = fsm.getMazeElement(i,j);
                    DrawMazeElement(temp,i,j);
                }
            }
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void DrawMazeElement(IMazeElement element, int x, int y){
        if (element != null) {
            textGraphics.setForegroundColor(getColor(element.getSymbol()));
            textGraphics.putString(x, y, element.getSymbol() + "");
        } else {
            textGraphics.putString(x, y, " ");
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        }
    }
    public void DrawMoveableElement(){
        try {
           /* for(MoveableElement element:fsm.getMoveableElements()){
                if(element.getCurrentDirection()==-1) {
                    int lastX=element.getLastX(),lastY= element.getLastY();
                    int x=element.getX(),y=element.getY();
                    textGraphics.setForegroundColor(element.getColor());
                    textGraphics.putString(x,y, element.getSymbol() + "");
                    //DrawMazeElement(fsm.getMazeElement(lastX, lastY), lastX, lastY);
                }
            }*/
            MoveableElement element = fsm.getPacMan();
            int lastX=element.getLastX(),lastY= element.getLastY();
            int x=element.getX(),y=element.getY();
            textGraphics.setForegroundColor(element.getColor());
            textGraphics.putString(x,y, element.getSymbol() + "");
            DrawMazeElement(fsm.getMazeElement(lastX, lastY), lastX, lastY);
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void DrawInfoSection(){
        try{
            textGraphics.putString(fsm.getBoardHeight()+1,5,"Nivel: "+fsm.getLevel());
           textGraphics.putString(fsm.getBoardHeight()+1,6,"Pontuação: "+fsm.getPacManPoints());
           textGraphics.putString(fsm.getBoardHeight()+1,7,"Vidas: "+fsm.getPacManLives());
           terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String ReadKey() {
        String Key="Character";
        try {
            KeyStroke keyStroke =terminal.pollInput();
            if(keyStroke!=null){
                Key= keyStroke.getKeyType().toString();
                System.out.println(Key);
            }
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
