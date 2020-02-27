package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "TicTacToeMainActivity";
    private int mark1;
    private int mark2;
    public  static final int LENGTH = 3;
    public Button[][] buttons = new Button[LENGTH][LENGTH];
    public Button resetButton;
    public Button startButton;
    public boolean flag;
    public int round = 1;
    public TextView playerX;
    public TextView playerO;
    public String lastClick;
    public game boardGame;
    public String[] buttonInformation = {"-1","-1","-1","-1","-1","-1","-1","-1","-1"};
    public int[] saveColoredButtonX = {-1,-1,-1};
    public int[] saveColoredButtonY = {-1,-1,-1};
    public String[] winningPattern = {"-1","-1","-1"};
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("1", buttonInformation[0]);
        savedInstanceState.putString("2", buttonInformation[1]);
        savedInstanceState.putString("3", buttonInformation[2]);
        savedInstanceState.putString("4", buttonInformation[3]);
        savedInstanceState.putString("5", buttonInformation[4]);
        savedInstanceState.putString("6", buttonInformation[5]);
        savedInstanceState.putString("7", buttonInformation[6]);
        savedInstanceState.putString("8", buttonInformation[7]);
        savedInstanceState.putString("9", buttonInformation[8]);
        savedInstanceState.putString("First",winningPattern[0]);
        savedInstanceState.putString("Second",winningPattern[1]);
        savedInstanceState.putString("Third",winningPattern[2]);
        savedInstanceState.putInt("mark1",mark1);
        savedInstanceState.putInt("mark2",mark2);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        revoke();
        if (savedInstanceState != null){
            buttonInformation[0] = savedInstanceState.getString("1");
            buttonInformation[1] = savedInstanceState.getString("2");
            buttonInformation[2] = savedInstanceState.getString("3");
            buttonInformation[3] = savedInstanceState.getString("4");
            buttonInformation[4] = savedInstanceState.getString("5");
            buttonInformation[5] = savedInstanceState.getString("6");
            buttonInformation[6] = savedInstanceState.getString("7");
            buttonInformation[7] = savedInstanceState.getString("8");
            buttonInformation[8] = savedInstanceState.getString("9");
            System.out.println("DKK"+Arrays.toString(buttonInformation));
            winningPattern[0] = savedInstanceState.getString("First");
            winningPattern[1] = savedInstanceState.getString("Second");
            winningPattern[2] = savedInstanceState.getString("Third");
            mark1 = savedInstanceState.getInt("mark1");
            mark2 = savedInstanceState.getInt("mark2");
            System.out.println("-Win"+Arrays.toString(winningPattern));
            for (int i = 0;i < 9;i++) {
                if (!buttonInformation[i].equals("-1")) {
                    int x = Integer.parseInt(buttonInformation[i].substring(0, 1));
                    int y = Integer.parseInt(buttonInformation[i].substring(1, 2));
                    char str = buttonInformation[i].charAt(2);
                    putKeys(buttons[x][y], x, y, str);
                }
            }
            if (boardGame.won()>0){
                for (int i = 0;i< 3;i++){
                    int x_cord = Integer.parseInt(winningPattern[i].substring(0,1));
                    int y_cord = Integer.parseInt(winningPattern[i].substring(1,2));
                    buttons[x_cord][y_cord].setBackgroundColor(Color.YELLOW);
                }
            }
        }
        resetButton.setOnClickListener(v ->
        {
            Log.i(TAG, "Reset Button Clicked");
            resetLayoutBoard();
            boardGame.reset();
            boardGame.clear();
            resetPadMatrix();
            aiPlace('x');
            System.out.println("1189++"+lastClick+"");
            changeFlag(lastClick.charAt(2));
        });
        startButton.setOnClickListener(v->
        {
            resetLayoutBoard();
            boardGame.reset();
            boardGame.clear();
            resetPadMatrix();
            if (round == 1){
                aiPlace('x');
                round = 2;
            }
            else if (round == 2){
                round = 1;
                flag = !flag;

            }
            Log.i(TAG, "Start Button Clicked");
        });
    }
    public boolean changeFlag(char x){
        if (x == 'x'){
            return false;
        }
        else
            return true;
    }

    public void resetPadMatrix(){
        for (int i = 0;i< 9;i++){
            buttonInformation[i] = "-1";
        }
    }

    public void revoke() {
        boardGame = new game();
        flag = !flag;
        lastClick = "-1";

        resetButton = findViewById(R.id.resetButton);
        startButton = findViewById(R.id.startButton);
        playerX = findViewById(R.id.mark1);
        playerO = findViewById(R.id.mark2);
        buttons[0][0] = findViewById(R.id.button00);
        buttons[0][1] = findViewById(R.id.button01);
        buttons[0][2] = findViewById(R.id.button02);
        buttons[1][0] = findViewById(R.id.button10);
        buttons[1][1] = findViewById(R.id.button11);
        buttons[1][2] = findViewById(R.id.button12);
        buttons[2][0] = findViewById(R.id.button20);
        buttons[2][1] = findViewById(R.id.button21);
        buttons[2][2] = findViewById(R.id.button22);
        buttons[0][0].setOnClickListener(this);
        buttons[0][1].setOnClickListener(this);
        buttons[0][2].setOnClickListener(this);
        buttons[1][0].setOnClickListener(this);
        buttons[1][1].setOnClickListener(this);
        buttons[1][2].setOnClickListener(this);
        buttons[2][0].setOnClickListener(this);
        buttons[2][1].setOnClickListener(this);
        buttons[2][2].setOnClickListener(this);

        //instantiate all the buttons
    }
    public void resetLayoutBoard(){
        for (int i = 0;i< LENGTH;i++){
            for (int j = 0;j < LENGTH;j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
            }
        }
    }
    public void generateButton(){
        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setText("");
        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setText("");
        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setText("");
        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setBackgroundResource(android.R.drawable.btn_default);
        buttons[saveColoredButtonX[1]][saveColoredButtonY[1]].setBackgroundResource(android.R.drawable.btn_default);
        buttons[saveColoredButtonX[2]][saveColoredButtonY[2]].setBackgroundResource(android.R.drawable.btn_default);
    }
    public void color(){
        if (boardGame.won()> 0){
            Log.d(TAG, "WINNING");

            String[] arr = boardGame.find(boardGame.won());
            for (int i = 0;i < 3;i++){
                saveColoredButtonX[i] = Character.getNumericValue(arr[i].charAt(0));
                saveColoredButtonY[i] = Character.getNumericValue(arr[i].charAt(1));
                winningPattern[i] = arr[i];
                buttons[saveColoredButtonX[i]][saveColoredButtonY[i]].setBackgroundColor(Color.YELLOW);
            }
            if (buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].getText().toString().equals("x"))
            {
                playerX.setText(String.valueOf(++mark1));
            }
            else {
                playerO.setText(String.valueOf(++mark2));

            }
            Toast.makeText(MainActivity.this,buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].getText().toString()+ "   Won the game",
                    Toast.LENGTH_LONG).show();
            System.out.println("& ARRAY"+Arrays.toString(buttonInformation));
            System.out.println("Winning! Matrix is"+Arrays.toString(arr));
        }
        else{

            Log.d(TAG, "NOT WINNING");

        }
    }
    public void aiPlace(char token){
        if (!boardGame.isGameOver()) {
            int[] ranCor = randomPlace();
            int x = ranCor[0];
            int y = ranCor[1];
            putKeys(buttons[x][y], x, y, token);
            Log.d(TAG, "AI JUST PLACED A KEY AT x:"+x+"y:"+y);
        }
        else
        {
            Log.d(TAG, "AI FAIL");
        }
    }
    public void checkOver(){
        if (boardGame.isGameOver()){
            Toast.makeText(MainActivity.this, "Tic Tac or Tie, winner is already heighten if no then tie",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view){
        if (!((Button)view).getText().toString().equals(""))
            return;
        System.out.println("Is Game over?: "+boardGame.isGameOver());
        if (!boardGame.isGameOver())
        {
            System.out.println("ROUND :" + round);
            switch (view.getId()) {
                case R.id.button00:{
                    Log.d(TAG, "00 onClick: ");
                        if (flag){
                            putKeys(buttons[0][0],0,0,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[0][0],0,0,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button01:{
                    Log.d(TAG, "01 onClick: ");
                        if (flag){
                            putKeys(buttons[0][1],0,1,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[0][1],0,1,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button02:{
                    Log.d(TAG, "02 onClick: ");
                        if (flag){
                            putKeys(buttons[0][2],0,2,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[0][2],0,2,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button10:{
                    Log.d(TAG, "10 onClick: ");
                        if (flag){
                            putKeys(buttons[1][0],1,0,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[1][0],1,0,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button11:{
                    Log.d(TAG, "11 onClick: ");
                        if (flag){
                            putKeys(buttons[1][1],1,1,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[1][1],1,1,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button12:{

                    Log.d(TAG, "12 onClick: ");
                        if (flag){
                            putKeys(buttons[1][2],1,2,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[1][2],1,2,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button20:{
                    Log.d(TAG, "20 onClick: ");
                        if (flag){
                            putKeys(buttons[2][0],2,0,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[2][0],2,0,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button21:{
                    Log.d(TAG, "21 onClick: ");
                        if (flag){
                            putKeys(buttons[2][1],2,1,'x');
                            aiPlace('o');
                        }
                        else{
                            putKeys(buttons[2][1],2,1,'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
                case R.id.button22: {
                    Log.d(TAG, "22 onClick: ");
                //    if (round ==1){
                        if (flag){
                            putKeys(buttons[2][2],2,2,'x');
                            aiPlace('o');
                        }
                        else {
                            putKeys(buttons[2][2], 2, 2, 'o');
                            aiPlace('x');
                        }
                    checkOver();

                    break;
                }
            }
            color();
        }
        else{



            System.out.println("Game is over!");
            boardGame.printBoard(boardGame.gameBoard);
        }
    }
    public void putKeys(Button b,int cordX, int cordY,char x){
        if (b.getText().toString().equals("")){
            boardGame.play(cordX,cordY,x);
            //if this button is empty then we can put keys here.
            b.setText(String.valueOf(x));
            String newA =  cordX+""+cordY+""+x;
            lastClick = cordX+""+cordY+""+x;;
            System.out.println("newA"+newA+"pad Value"+getPadValue(cordX,cordY));
            buttonInformation[getPadValue(cordX,cordY)] = newA;
            flag = !flag;
        }
        else
            System.out.println("Can not place key here");
    }
    public String xOrY(int x){
        if (x == 1)
            return "x";
        else
            return "o";
    }

    public int[] randomPlace(){
        int[] rc = new int[2];
        int r = (int)(Math.random()*3);
        int c = (int)(Math.random()*3);
        while (!buttons[r][c].getText().toString().equals("")){
            r = (int)(Math.random()*3);
            c = (int)(Math.random()*3);
        }
        rc[0] = r;
        rc[1] = c;
        return rc;
    }
    public int getPadValue(int x, int y){
        int a = -1;
        if (x== 0 && y == 0){
            a = 0;
        }
        else if (x == 0 && y== 1){
            a = 1;
        }
        else if (x== 0 && y == 2){
            a = 2;
        }
        else if (x == 1 && y == 0){
            a = 3;
        }
        else if (x == 1 && y == 1){
            a = 4;
        }
        else if (x == 1 && y == 2){
            a = 5;
        }
        else if (x == 2 && y == 0){
            a = 6;
        }
        else if (x == 2 && y == 1){
            a = 7;
        }
        else if (x == 2 && y == 2){
            a = 8;
        }
        else {a = -1;}
        return a;
    }
    public int getPadValue(String x, String y){
        int a = -1;
        if (x.equals("0") && y.equals("0")){
            a = 0;
        }
        else if (x.equals("0") && y.equals("1")){
            a = 1;
        }
        else if (x.equals("0") && y.equals("2")){
            a = 2;
        }
        else if (x.equals("1") && y.equals("0")){
            a = 3;
        }
        else if (x.equals("1") && y.equals("1")){
            a = 4;
        }
        else if (x.equals("1") && y.equals("2")){
            a = 5;
        }
        else if (x.equals("2") && y.equals("0")){
            a = 6;
        }
        else if (x.equals("2") && y.equals("1")){
            a = 7;
        }
        else if (x.equals("2") && y.equals("2")){
            a = 8;
        }
        else {
            a = -1;}
        return a;
    }
}
