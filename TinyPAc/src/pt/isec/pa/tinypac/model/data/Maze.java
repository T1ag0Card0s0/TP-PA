package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.PacMan;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.*;

import java.io.*;

public final class Maze {
    private final IMazeElement[][] board;

    public Maze(int height, int width) {
        board = new IMazeElement[height][width];
    }

    public boolean set(int y, int x,IMazeElement element) {
        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length)
            return false;
        board[y][x] = element; // can be null
        return true;
    }

    public IMazeElement get(int y, int x) {
        if (y < 0 || y >= board.length || x < 0 || x >= board[0].length)
            return null;
        return board[y][x]; // can be null
    }

    public char[][] getMaze() {
        char[][] char_board = new char[board.length][board[0].length];
        for(int y=0;y<board.length;y++)
            for(int x=0;x<board[y].length;x++)
                if (board[y][x]==null)
                    char_board[y][x] = ' ';
                else
                    char_board[y][x] = board[y][x].getSymbol();
        return char_board;
    }
    public int getWidth(){ return board[0].length;}
    public int getHeight(){return board.length;}
    public IMazeElement findPacMan(){
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j<board[i].length;j++){
                if(board[i][j] instanceof Start){
                    PacMan pacMan=new PacMan(i,j);
                    set(i,j,pacMan);
                    return pacMan;
                }
            }
        }
        return null;
    }
    public void fillBoard(String filePath) {
        try {
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int c;
            int row = 0, column = 0;
            while ((c = br.read()) != -1) {
                set(column, row, elementFinder((char) c));
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
    private IMazeElement elementFinder(char character){
        IMazeElement element=null;
        switch (character){
            case 'W'->{
                element=new Wrap();
            }
            case 'o'->{
                element=new NormalFood();
            }
            case 'F'->{
                element=new Fruit();
            }
            case 'M'->{
                element=new Start();
            }
            case 'O'->{
                element=new PowerFood();
            }
            case 'Y','y'->{
                element=new GhostCave();
            }
            case 'x'->{
                element=new Wall();
            }
        }
        return element;
    }
}