package pt.isec.pa.tinypac.model.data;
import pt.isec.pa.tinypac.model.data.mazeElements.IMazeElement;
import pt.isec.pa.tinypac.model.data.mazeElements.clientElements.ClientElement;
import pt.isec.pa.tinypac.model.data.mazeElements.zoneElements.*;

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

}