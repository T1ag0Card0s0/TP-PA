package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.mazeElements.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        public void fillLevel(String filePath) throws IOException {
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int c;
            int row=0,column=0;
            while ((c = br.read()) != -1) {
                set(column,row,elementFinder((char)c));
                if((char)c == '\n'){ row++;column=0;}
                column++;
            }
            br.close();
            reader.close();
        }
    private IMazeElement elementFinder(char character){
        IMazeElement element=null;
        switch (character){
            case 'W'->{
                element=new Wrap('█');
            }
            case 'o'->{
                element=new NormalFood('.');
            }
            case 'F'->{
                element=new Fruit(character);
            }
            case 'M'->{
                element=new PacMan(character);
            }
            case 'O'->{
                element=new PowerFood('o');
            }
            case 'Y','y'->{
                element=new GhostCave('█');
            }
        }
        return element;
    }
}