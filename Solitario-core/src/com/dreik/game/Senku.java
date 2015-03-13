package com.dreik.game;

import java.util.Stack;

import com.badlogic.gdx.math.Vector2;

class HistorialEntry {
	public Vector2 prevPosition = new Vector2();
	public Vector2 newPosition = new Vector2();
	public Vector2 middlePosition = new Vector2();
}

public class Senku {
    
    private final int SIZE = 7;
    private Stack<HistorialEntry> historial = new Stack<HistorialEntry>();
    
    String board[][] = new String[SIZE][SIZE];
    
    public Senku() {
        createBoard();
    }
    
    private void createBoard() {
        for(int y = 0; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                board[x][y] = " *";
                cutBoardLimits(x, y);
            } 
            board[SIZE / 2][SIZE / 2] = " .";
        }
    }
    
    public void cutBoardLimits(int x, int y) {
        if(x < 2 && y < 2)
            board[x][y] = "  ";
        else if(x < 2 && y > 4)
            board[x][y] = "  ";
        else if(x > 4 && y < 2)
            board[x][y] = "  ";
        else if(x > 4 && y > 4)
            board[x][y] = "  ";
    }
    
    public void moveToPosition(int x, int y, int newX, int newY) {
        if(board[newX][newY].equals(" .")) {
            if(x - newX == 2 && y == newY && board[x-1][newY].equals(" *")) {
                board[x][y] = " .";
            	board[x-1][newY] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.newPosition.add(x-1, newY);
                entry.middlePosition.add(newX, newY);
                historial.push(entry);
            }

            if(x - newX == -2 && y == newY && board[x+1][newY].equals(" *")) {
                board[x][y] = " .";
            	board[x+1][newY] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.newPosition.add(x+1, newY);
                entry.middlePosition.add(newX, newY);
                historial.push(entry);
            }
            if(y - newY == -2 && x == newX && board[x][newY-1].equals(" *")) {
                board[x][y] = " .";
            	board[newX][y+1] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.newPosition.add(newX, y+1);
                entry.middlePosition.add(newX, newY);
                historial.push(entry);
            }
            if(y - newY == 2 && x == newX && board[x][newY+1].equals(" *")) {
                board[x][y] = " .";
            	board[newX][y-1] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.newPosition.add(newX, y-1);
                entry.middlePosition.add(newX, newY);
                historial.push(entry);
            }
            
        }

    }
    
    public String[][] getBoard() {
        return board;
    }
    
    public void showBoard() {
        String auxString = "";
        for(int y = 0; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                auxString += board[x][y];
            } 
            auxString += "\n";
        }
        System.out.println(auxString);
    }
    
    public void back() {
    	board[(int) historial.peek().prevPosition.x][(int) historial.peek().prevPosition.y] = " *";
    	board[(int) historial.peek().newPosition.x][(int) historial.peek().newPosition.y] = " .";
    	board[(int) historial.peek().middlePosition.x][(int) historial.peek().middlePosition.y] = " *";
    	historial.pop();
    }
    
}
