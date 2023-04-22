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
import pt.isec.pa.tinypac.model.fsm.game.GameContext;

import java.io.IOException;

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
                if(fsm.WinLevel()){
                    terminal.clearScreen();
                    terminal.flush();
                    continue;
                }
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke == null)continue;

                if(keyStroke.getCharacter()!=null){
                    fsm.KeyIsPressed(keyStroke.getCharacter().toString());
                }else{
                    fsm.KeyIsPressed(keyStroke.getKeyType().toString());
                }
                if (keyStroke.getKeyType() == KeyType.Escape) {
                    finish = true;
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
            case '.', 'O' ->color = TextColor.ANSI.YELLOW;
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
           for(MoveableElement element:fsm.getMoveableElements()){
                    int lastX=element.getLastX(),lastY= element.getLastY();
                    int x=element.getX(),y=element.getY();
                    textGraphics.setForegroundColor(element.getColor());
                    textGraphics.putString(x,y, element.getSymbol() + "");
                    DrawMazeElement(fsm.getMazeElement(lastX, lastY), lastX, lastY);
            }
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void DrawInfoSection(){
        try{
            textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            textGraphics.putString(1, fsm.getBoardHeight()+1, "Nivel: "+fsm.getLevel());
            textGraphics.putString(1, fsm.getBoardHeight()+2, "Pontuação: "+fsm.getPacManPoints());
            textGraphics.putString(1, fsm.getBoardHeight()+3, "Vidas: "+fsm.getPacManLives());
            textGraphics.putString(1, fsm.getBoardHeight()+4, "Estado do Jogo: "+fsm.getGameEngineState()+" ");
            textGraphics.putString(1, fsm.getBoardHeight()+5, "Pressiona 'esc' para sair");
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
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
