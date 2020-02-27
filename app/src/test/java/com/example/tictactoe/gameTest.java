package com.example.tictactoe;

import org.junit.Test;
import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
public class gameTest {
    public static double toloerance;
    @Test
    public void test1(){
        game game1 = new game();
        game1.play(0,0,'o');
        game1.play(0,1,'x');
        game1.play(1,1,'o');
        game1.play(1,2,'x');
        game1.play(2,2,'o');
        game1.play(0,2,'x');
        game1.printBoard(game1.gameBoard);
        int x = Integer.parseInt(game1.arr[0].substring(0,1));
        int y = Integer.parseInt(game1.arr[0].substring(1,2));
        if (game1.board[x][y] == 2){
            System.out.println("o won");
        }
        else if (game1.board[x][y] == 1){
            System.out.println("x won");
        }
    }
    @Test
    public void test2(){
        game game1 = new game();
        game1.play(0,0,'x');
        game1.play(1,1,'o');
        game1.play(0,1,'x');
        game1.play(2,0,'o');
        game1.play(0,2,'x');
        game1.play(1,2,'o');
        game1.printBoard(game1.gameBoard);
        if (!game1.arr[0].equals("-1")){
            int x = Integer.parseInt(game1.arr[0].substring(0,1));
            int y = Integer.parseInt(game1.arr[0].substring(1,2));
            if (game1.board[x][y] == 2){
                System.out.println("o won");
            }
            else if (game1.board[x][y] == 1){
                System.out.println("x won");
            }
        }

    }
    @Test
    public void test3(){
        game game1 = new game();
        game1.play(0,0,'x');
        game1.play(1,0,'o');
        game1.play(2,0,'x');
        game1.play(2,1,'o');
        game1.play(1,1,'x');
        game1.play(0,1,'o');
        game1.play(0,2,'x');
        game1.play(1,2,'o');//should not add

        game1.printBoard(game1.gameBoard);
        if (!game1.arr[0].equals("-1")){
            int x = Integer.parseInt(game1.arr[0].substring(0,1));
            int y = Integer.parseInt(game1.arr[0].substring(1,2));
            if (game1.board[x][y] == 2){
                System.out.println("o won");
            }
            else if (game1.board[x][y] == 1){
                System.out.println("x won");
            }
        }
    }
}
