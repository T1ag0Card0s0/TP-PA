package pt.isec.pa.tinypac.ui.text;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;

import java.io.IOException;

public class TextInterface   {
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
        boolean mazeDrawn=true;
        createWindow();
        DrawMaze();
        DrawMoveableElement();
        try {
            while (!finish) {
               // DrawInfoSection();//quando perco os MoveableElements aprentam estar a null
                DrawMaze();
                if(fsm.getState()== EGameState.INITIAL_STATE){
                    if(!mazeDrawn) {
                        DrawMaze();
                        mazeDrawn = true;
                    }
                }else{
                    mazeDrawn=false;
                }
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke == null) continue;

                if (keyStroke.getCharacter() != null) {
                    fsm.KeyIsPressed(keyStroke.getCharacter().toString());
                } else {
                    fsm.KeyIsPressed(keyStroke.getKeyType().toString());
                }
                if (keyStroke.getKeyType() == KeyType.Escape) {
                    finish=true;
                }
            }
            CloseTerminal();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public TextColor getColor(char c){

        return (
                switch (c){
                    case 'x'-> TextColor.ANSI.BLUE;
                    case 'W'->TextColor.ANSI.WHITE;
                    case '.', 'O' -> TextColor.ANSI.YELLOW;
                    case 'F', 'Y' ->TextColor.ANSI.MAGENTA;
                    case 'P'->new TextColor.RGB(255,255,0);
                    case 'p'-> new TextColor.RGB(222,161,133);
                    case 'b'->TextColor.ANSI.RED;
                    case 'c'->new TextColor.RGB(255,140,0);
                    case 'i'->new TextColor.RGB(0,255,255);
                    default -> TextColor.ANSI.BLACK;
                }
                );
    }
    public void DrawMaze() {
        try {
            for (int i = 0; i < fsm.getBoardHeight(); i++) {
                for (int j = 0; j < fsm.getBoardWidth(); j++) {
                    DrawMazeElement(i,j);
                }
            }
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void DrawMazeElement( int x, int y){
        char symbol=fsm.getMazeSymbols()[x][y];
        switch (symbol){
            case 'b','c','i','p','P'->  textGraphics.setBackgroundColor(getColor(symbol));
            default -> {
                textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
                textGraphics.setForegroundColor(getColor(symbol));
            }
        }

        textGraphics.putString(x, y, symbol + "");
    }
    public void DrawMoveableElement(){
        try {
           for(MoveableElement element:fsm.getMoveableElements()){
               if(element == null)continue;
               int lastX=element.getLastX(),lastY= element.getLastY();
               int x=element.getX(),y=element.getY();
               char c= element.getSymbol();
               DrawMazeElement(lastX, lastY);
               if(element.getVulnerable())
                   textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
               else {
                   textGraphics.setBackgroundColor(getColor(c));
                   textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
               }
               textGraphics.putString(x,y, c+"");
            }
            terminal.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void DrawInfoSection(){
        try{
            textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
            textGraphics.putString(1, fsm.getBoardHeight()+1, "Nivel: "+fsm.getLevel());
            textGraphics.putString(1, fsm.getBoardHeight()+2, "Pontuação: "+fsm.getPacManPoints()+" ");
            textGraphics.putString(1, fsm.getBoardHeight()+3, "Vidas: "+fsm.getPacManLives());
            textGraphics.putString(1, fsm.getBoardHeight()+4, "Estado do Jogo: "+fsm.getState()+"    ");
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
