package com.mygdx.game;

public class Solitario {
    
    private final int SIZE = 7;
    
    String board[][] = new String[SIZE][SIZE];
    
    public Solitario() {
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
            }

            if(x - newX == -2 && y == newY && board[x+1][newY].equals(" *")) {
                board[x][y] = " .";
            	board[x+1][newY] = " .";
                board[newX][newY] = " *";
            }
            if(y - newY == -2 && x == newX && board[x][newY-1].equals(" *")) {
                board[x][y] = " .";
            	board[newX][y+1] = " .";
                board[newX][newY] = " *";
            }
            if(y - newY == 2 && x == newX && board[x][newY+1].equals(" *")) {
                board[x][y] = " .";
            	board[newX][y-1] = " .";
                board[newX][newY] = " *";
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
    
//    public static void main(String[] args) {
//        Solitario solitario = new Solitario();
//        solitario.moveToPosition(5, 3, 3, 3);
//        solitario.showBoard();
//    }
    
}
