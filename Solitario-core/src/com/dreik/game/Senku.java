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
    private Stack<HistorialEntry> historic = new Stack<HistorialEntry>();
    
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
        if(isEmptySpace(newX, newY)) {
            if(isLeftMovement(x, y, newX, newY)) {
                board[x][y] = " .";
            	board[x-1][newY] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.middlePosition.add(x-1, newY);
                entry.newPosition.add(newX, newY);
                historic.push(entry);
                GdxGame.xmlHistoric.addMove(newX, newY);
            }

            if(isRightMovement(x, y, newX, newY)) {
                board[x][y] = " .";
            	board[x+1][newY] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.middlePosition.add(x+1, newY);
                entry.newPosition.add(newX, newY);
                historic.push(entry);
                GdxGame.xmlHistoric.addMove(newX, newY);
            }
            if(isUpMovement(x, y, newX, newY)) {
                board[x][y] = " .";
            	board[newX][y+1] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.middlePosition.add(newX, y+1);
                entry.newPosition.add(newX, newY);
                historic.push(entry);
                GdxGame.xmlHistoric.addMove(newX, newY);
            }
            if(isDownMovement(x, y, newX, newY)) {
                board[x][y] = " .";
            	board[newX][y-1] = " .";
                board[newX][newY] = " *";
                
                HistorialEntry entry = new HistorialEntry();
                entry.prevPosition.add(x,y);
                entry.middlePosition.add(newX, y-1);
                entry.newPosition.add(newX, newY);
                historic.push(entry);
                GdxGame.xmlHistoric.addMove(newX, newY);
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
    	if(!historic.isEmpty()) {
        	board[(int) historic.peek().prevPosition.x][(int) historic.peek().prevPosition.y] = " *";
        	board[(int) historic.peek().newPosition.x][(int) historic.peek().newPosition.y] = " .";
        	board[(int) historic.peek().middlePosition.x][(int) historic.peek().middlePosition.y] = " *";
        	historic.pop();
        	GdxGame.instance.removeBoard();
        	GdxGame.instance.updateBoard();
    	}
    }
    
    private boolean isRightMovement(int x, int y, int newX, int newY) {
    	return x - newX == -2 && y == newY && board[x+1][newY].equals(" *");
    }
    
    private boolean isLeftMovement(int x, int y, int newX, int newY) {
    	return x - newX == 2 && y == newY && board[x-1][newY].equals(" *");
    }
    
    private boolean isDownMovement(int x, int y, int newX, int newY) {
    	return y - newY == -2 && x == newX && board[x][newY-1].equals(" *");
    }
    
    private boolean isUpMovement(int x, int y, int newX, int newY) {
    	return y - newY == 2 && x == newX && board[x][newY+1].equals(" *");
    }
    
    private boolean isEmptySpace(int newX, int newY) {
    	return board[newX][newY].equals(" .");
    }
    
}
