package com.example.tictactoe;

import android.util.Log;

import java.util.Arrays;

public class game {
    public static final int LENGTH = 3;//board will be a 3 X 3 Board
    private int turn;//false for player1 and true for player 2
    public int [][] board;//a 3 by 3 board will be generated when calling constructor
    char[][] gameBoard ={
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}};

    public String[] arr = {"x","x","x"};
    public int getTurn() {
        return turn;
    }

    public game(){
        board = new int[LENGTH][LENGTH];//allocating memory for board
        reset();//set board to usable
    }
    public int returnToken(char token){
        if (token == 'x'){
            return 1;
        }
        else
            return 2;
    }
    public void play(int row, int column,char token){
        int currentTurn = turn;//know which player is playing right now
        if(column >= 0 && row >= 0 &&column < LENGTH && row < LENGTH && board[row][column] == 0 &&!isGameOver()) //checking if # passed in is a valid number, and place is a valid place
        {
            //if yes, then it means we can put value in 2d array
            board[row][column] = turn;
            if (turn == 1){
                insertBoard(row,column,token);
                board[row][column] = returnToken(token);
            }
            else{
                insertBoard(row,column,token);
                board[row][column] = returnToken(token);

            }
        }
        else
        {
            System.out.println("Game Terminates");
        }

    }
    public int won(){
        int rows = checkRows();
        int cols = checkCols();
        int twoDArray = check2d();
        if (rows > 0){
            find(rows);
            return rows;
        }
        if (cols > 0) {
            find(cols);
            return cols;
        }
        if (twoDArray > 0) {
            find(twoDArray);
            return twoDArray;
        }
        return 0;
        //it will return integer that represents which one wins
        //1 for player 1 && 2 for player 2
    }

    public int checkRows(){
        for (int i = 0; i < LENGTH;i++){
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
        }
        return 0;
    }
    public int checkCols(){
        for (int i = 0; i < LENGTH;i++){
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        return 0;
    }
    public int check2d(){
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        else if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[2][0];
        return 0;
    }
    public boolean unableToPlay(){
        boolean result = true;
        for (int i = 0; i < LENGTH;i++){
            for (int j = 0 ;j< LENGTH; j++){
                if (board[i][j] == 0)
                    result = false;
            }
        }
        return  result;
        //check each grid, as long is there are space it is playable
    }
    public boolean isGameOver(){
        return unableToPlay() || (won()>0);
    }

    public void reset(){
        for (int i = 0;i < LENGTH; i++){
            for (int j = 0;j< LENGTH;j++){
                board[i][j] = 0;
            }
        }
        turn = 1;
    }
    public void printBoard(char[][] board){
        for (char[] row: board){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("=------=");
    }
    public void insertBoard(int x,int y,char token){
        int position = -1;
        if (x == 0  && y == 0)
            position = 1;
        else if (x == 0 && y ==1)
            position = 2;
        else if (x == 0 && y == 2)
            position = 3;
        else if (x == 1 && y == 0)
            position = 4;
        else if (x == 1 && y == 1)
            position = 5;
        else if (x == 1 && y == 2)
            position = 6;
        else if (x == 2 && y == 0)
            position = 7;
        else if (x == 2 && y == 1)
            position = 8;
        else if (x == 2 && y == 2)
            position = 9;
        else
            position = 0;
        switch (position){
            case 1:
                gameBoard[0][0] = token;
                break;
            case 2:
                gameBoard[0][2] = token;
                break;
            case 3:
                gameBoard[0][4] = token;
                break;
            case 4:
                gameBoard[2][0] = token;
                break;
            case 5:
                gameBoard[2][2] = token;
                break;
            case 6:
                gameBoard[2][4] = token;
                break;
            case 7:
                gameBoard[4][0] = token;
                break;
            case 8:
                gameBoard[4][2] = token;
                break;
            case 9:
                gameBoard[4][4] = token;
                break;
        }
        printBoard(gameBoard);
    }

    public void clear(){
        gameBoard[0][0] = ' ';
        gameBoard[0][2] = ' ';
        gameBoard[0][4] = ' ';
        gameBoard[2][0] = ' ';
        gameBoard[2][2] = ' ';
        gameBoard[2][4] = ' ';
        gameBoard[4][0] = ' ';
        gameBoard[4][2] = ' ';
        gameBoard[4][4] = ' ';
        arr[0] = "x";
        arr[1] = "x";
        arr[2] = "x";
    }

    public String[] find(int x){
        //x is either 1 or 2 => only call this function in win
        for (int i = 0;i< 3;i++){//check row index
            if (board[0][0] == x && board[0][0] == board[0][1] && board[0][1] == board[0][2]){
                //first row
                arr[0] = "00";
                arr[1] = "01";
                arr[2] = "02";
            }
            else if (board[1][0] == x && board[1][0] == board[1][1] && board[1][1] == board[1][2]){
                //second row
                arr[0] = "10";
                arr[1] = "11";
                arr[2] = "12";
            }
            else if (board[2][0] == x && board[2][0] == board[2][1] && board[2][1] == board[2][2]){
                //third row
                arr[0] = "20";
                arr[1] = "21";
                arr[2] = "22";
            }
            else if (board[0][0] == x && board[0][0] == board[1][0] && board[1][0] == board[2][0]){
                //vertical1
                arr[0] = "00";
                arr[1] = "10";
                arr[2] = "20";
            }
            else if (board[0][1] == x && board[0][1] == board[1][1] && board[1][1] == board[2][1]){
                //vertical 2
                arr[0] = "01";
                arr[1] = "11";
                arr[2] = "21";
            }
            else if (board[0][2] == x && board[0][2] == board[1][2] && board[1][2] == board[2][2]){
                //vertical3
                arr[0] = "02";
                arr[1] = "12";
                arr[2] = "22";
            }

            else if (board[0][0] == x && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
                arr[0] = "00";
                arr[1] = "11";
                arr[2] = "22";
            }
            else if (board[0][2] == x && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
                arr[0] = "02";
                arr[1] = "11";
                arr[2] = "20";
            }

            else{
                System.out.println("Null Line");
                return null;

            }
        }
// all 8 sequences are tested => they'are all correct
        return arr;
    }
}
