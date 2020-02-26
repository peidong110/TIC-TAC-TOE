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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "TicTacToeMainActivity";
    //    private int mark1;
//    private int mark2;
    public  static final int LENGTH = 3;
    public Button[][] buttons = new Button[LENGTH][LENGTH];
    public Button resetButton;
    public Button startButton;
    public boolean role;
    boolean p1Go;

    String fuck;
    //    public ArrayList<Integer> anOrder;
//    public ArrayList<String>  location;
//    public TextView player1;
//    public TextView player2;
    public game boardGame;
    public String[] buttonInformation = {"-1","-1","-1","-1","-1","-1","-1","-1","-1"};
    public int[] saveColoredButtonX = {-1,-1,-1};
    public int[] saveColoredButtonY = {-1,-1,-1};
//    public int[] aiBoard = new int[9];

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        for (int i = 0;i<9;i++){
//            savedInstanceState.putInt("b"+i+"", buttonInformation[i]);
//        }
//        for (int i:anOrder){
//            savedInstanceState.putInt("b"+i, anOrder.get(i));
//            savedInstanceState.putString("l"+i,location.get(i));
//
//        }
//        savedInstanceState.getParcelable("aList",anOrder);
//        savedInstanceState.putStringArrayList("location",location);
//        savedInstanceState.putIntegerArrayList("order",anOrder);
//        System.out.println("1109 Order: "+anOrder+" location: "+location);
//
        savedInstanceState.putString("1", buttonInformation[0]);
        savedInstanceState.putString("2", buttonInformation[1]);
        savedInstanceState.putString("3", buttonInformation[2]);
        savedInstanceState.putString("4", buttonInformation[3]);
        savedInstanceState.putString("5", buttonInformation[4]);
        savedInstanceState.putString("6", buttonInformation[5]);
        savedInstanceState.putString("7", buttonInformation[6]);
        savedInstanceState.putString("8", buttonInformation[7]);
        savedInstanceState.putString("9", buttonInformation[8]);

        System.out.println("newA"+Arrays.toString(buttonInformation));

        savedInstanceState.putInt("cX1",saveColoredButtonX[0]);
        savedInstanceState.putInt("cX2",saveColoredButtonX[1]);
        savedInstanceState.putInt("cX3",saveColoredButtonX[2]);
        savedInstanceState.putInt("cY1",saveColoredButtonX[0]);
        savedInstanceState.putInt("cY2",saveColoredButtonX[1]);
        savedInstanceState.putInt("cY3",saveColoredButtonX[2]);

        for (int i =0;i< LENGTH;i++){
            savedInstanceState.putInt("cX"+i+"",saveColoredButtonX[i]);
            savedInstanceState.putInt("cY"+i+"",saveColoredButtonY[i]);
        }

//        System.out.println("!!!!!!!X ARRAY"+Arrays.toString(saveColoredButtonX));
//        System.out.println("!!!!!!!Y ARRAY"+Arrays.toString(saveColoredButtonY));

    }


    //    @Override
//    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
//        // Always call the superclass so it can save the view hierarchy state
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putInt("mark1", mark1);
//        savedInstanceState.putInt("mark2", mark2);
////        savedInstanceState.putInt("b1",buttonInformation[0]);
////        for (int i = 0;i< 9;i++){
////            savedInstanceState.putInt("b"+i, buttonInformation[i]);
////            //save all information for all 9 buttons
////        }
//        savedInstanceState.putInt("b0", buttonInformation[0]);
//        savedInstanceState.putInt("b1", buttonInformation[1]);
//        savedInstanceState.putInt("b2", buttonInformation[2]);
//        savedInstanceState.putInt("b3", buttonInformation[3]);
//        savedInstanceState.putInt("b4", buttonInformation[4]);
//        savedInstanceState.putInt("b5", buttonInformation[5]);
//        savedInstanceState.putInt("b6", buttonInformation[6]);
//        savedInstanceState.putInt("b7", buttonInformation[7]);
//        savedInstanceState.putInt("b8", buttonInformation[8]);
////        for (int i = 0;i< 9;i++){
////            if (buttonInformation[i] == 1|| buttonInformation[i] == 2){
//////                screenRotationPlay(i);
////                System.out.println("Key Board Pressed",buttonInformation);
////            }
////        }
//        savedInstanceState.putInt("x0", saveColoredButtonX[0]);
//        savedInstanceState.putInt("y0", saveColoredButtonY[0]);
//        savedInstanceState.putInt("x1", saveColoredButtonX[1]);
//        savedInstanceState.putInt("y1", saveColoredButtonY[1]);
//        savedInstanceState.putInt("x2", saveColoredButtonX[2]);
//        savedInstanceState.putInt("y2", saveColoredButtonY[2]);
//        Log.i(TAG, "onSaveInstanceState(Bundle)");
//    }
//    public void change(int x,Button b,int xCord,int yCord){
//        if (x != 0){
//            if (x == 1){
//                b.setText("x");
//
//            }
//            else
//                b.setText("o");
//        }
//    }
//
//    public void setRotationPlay(){
//        for (int i = 0;i< 9;i++){
//            if(buttonInformation[i] != "-1"){
//                screenRotationPlay(i);
//            }
//        }
//    }
//    public void screenRotationPlay(int token){
//        if (token == 0){
//            putKeys(buttons[0][0],0,0);
//        }
//        if (token == 1){
//            putKeys(buttons[0][1],0,1);
//        }
//        if(token == 2){
//            putKeys(buttons[0][2],0,2);
//        }
//        if (token == 3){
//            putKeys(buttons[1][0],1,0);
//        }
//        if (token == 4){
//            putKeys(buttons[1][1],1,1);
//        }
//        if (token == 5){
//            putKeys(buttons[1][2],1,2);
//        }
//        if (token == 6){
//            putKeys(buttons[2][0],2,0);
//        }
//        if (token == 7){
//            putKeys(buttons[2][1],2,1);
//        }
//        if (token == 8){
//            putKeys(buttons[2][2],2,2);
//        }
//    }
    public void resetColor(){
        for (int i =0;i< 3;i++){
            saveColoredButtonY[i] = -1;
            saveColoredButtonX[i] = -1;
        }
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
            for (int i = 0;i < 9;i++){
                if (!buttonInformation[i].equals("-1")){
                    int x = Integer.parseInt(buttonInformation[i].substring(0,1));
                    int y = Integer.parseInt(buttonInformation[i].substring(1,2));
                    char str = buttonInformation[i].charAt(2);
                    putKeys(buttons[x][y],x,y,str);
                }
            }
//            System.out.println("newA"+Arrays.toString(buttonInformation));
//            for (int i = 0; i< 9;i++){
//                if (!buttonInformation[i].equals("-1")){
//                    int x = Integer.parseInt(buttonInformation[i].substring(0,1));
//                    int y = Integer.parseInt(buttonInformation[i].substring(1,2));
//                    putKeys(buttons[x][y],x,y);
//                    putKeys(buttons[0][0],0,0);
//                    putKeys(buttons[0][1],0,1);
//                    putKeys(buttons[0][2],0,2);
//                    putKeys(buttons[1][0],1,0);
//                    putKeys(buttons[1][1],1,1);
//                    putKeys(buttons[1][2],1,2);
//                    putKeys(buttons[2][0],2,0);
//                    putKeys(buttons[2][1],2,1);
//                    putKeys(buttons[2][2],2,2);
//                }
//            }

            System.out.println("099"+Arrays.toString(buttonInformation));

            saveColoredButtonX[0] = savedInstanceState.getInt("cX1");
            saveColoredButtonX[1] = savedInstanceState.getInt("cX2");
            saveColoredButtonX[2] = savedInstanceState.getInt("cX3");
            saveColoredButtonY[0] = savedInstanceState.getInt("cY1");
            saveColoredButtonY[1] = savedInstanceState.getInt("cY2");
            saveColoredButtonY[2] = savedInstanceState.getInt("cY3");


//            anOrder =savedInstanceState.getIntegerArrayList("order");
//            location = savedInstanceState.getStringArrayList("location");
//            System.out.println("1109"+anOrder+"location"+location);
//            int x_co[] = new int[anOrder.size()];
//            int y_co[] = new int[anOrder.size()];

//            for (int si = 0;si < anOrder.size();si++){
//                x_co[si] = Character.getNumericValue(location.get(si).charAt(0));
//                y_co[si] = Character.getNumericValue(location.get(si).charAt(1));
////                System.out.println("1109 "+Character.getNumericValue(location.get(a).charAt(0)));
//                System.out.println("1109 a:"+x_co[si]+"b:"+y_co[si]);
//            }
//            for (int z = 0;z < anOrder.size();z++){
//                int play = boardGame.play(x_co[z],y_co[z]);
//                //if this button is empty then we can put keys here.
//                buttons[x_co[z]][y_co[z]].setText(xOrY(play));
////                putKeys(buttons[x_co[z]][y_co[z]],x_co[z],y_co[z]);
//            }


//            for (int i:anOrder){
//                savedInstanceState.getInt("b"+i);
//                savedInstanceState.getString("l"+i);
//                int loc_x = Character.getNumericValue(location.get(i).charAt(0));
//                int loc_y = Character.getNumericValue(location.get(i).charAt(1));
//                Button btn = buttons[loc_x][loc_y];
//            }

//            for (int a : anOrder){
//                int loc_x = Character.getNumericValue(location.get(a).charAt(0));
//                int loc_y = Character.getNumericValue(location.get(a).charAt(1));
//                Button btn = buttons[loc_x][loc_y];
//                putKeys(btn,loc_x,loc_y);
//
//            }



            System.out.println("& ARRAY"+Arrays.toString(buttonInformation));

            System.out.println("Win"+boardGame.isGameOver());
//            for (int z = 0; z<LENGTH;z++){
//                System.out.println("x: " +saveColoredButtonX[z]+"y: "+saveColoredButtonY[z]);
//                if (boardGame.isGameOver()){
//                    saveColoredButtonX[z] = savedInstanceState.getInt("cX"+z+"");
//                    saveColoredButtonY[z] = savedInstanceState.getInt("cY"+z+"");
//                    buttons[saveColoredButtonX[z]][saveColoredButtonY[z]].setBackgroundColor(Color.YELLOW);
//                }
//            }

//
//            System.out.println("X ARRAY"+Arrays.toString(saveColoredButtonX));
//            System.out.println("Y ARRAY"+Arrays.toString(saveColoredButtonY));

//            for (int z = 0; z<LENGTH;z++){
//                System.out.println("x: " +saveColoredButtonX[z]+"y: "+saveColoredButtonY[z]);
//                if (saveColoredButtonX[z] != -0 && saveColoredButtonY[z] != -1){
//                    saveColoredButtonX[z] = savedInstanceState.getInt("cX"+z+"");
//                    saveColoredButtonY[z] = savedInstanceState.getInt("cY"+z+"");
//                    buttons[saveColoredButtonX[z]][saveColoredButtonY[z]].setBackgroundColor(Color.YELLOW);
//                }
//            }

//            for (int j = 0;j<9;j++){
//                if (buttonInformation[j] == 1 || buttonInformation[j] ==2){
//                    screenRotationPlay(j);
//                }
//            }
//            if (buttonInformation[0]!= 0)
//                screenRotationPlay(0);
//            if (buttonInformation[1]!= 0)
//                screenRotationPlay(1);
//            if (buttonInformation[2]!= 0)
//                screenRotationPlay(2);
//            if (buttonInformation[3]!= 0)
//                screenRotationPlay(3);
//            if (buttonInformation[4]!= 0)
//                screenRotationPlay(4);
//            if (buttonInformation[5]!= 0)
//                screenRotationPlay(5);
//            if (buttonInformation[6]!= 0)
//                screenRotationPlay(6);
//            if (buttonInformation[7]!= 0)
//                screenRotationPlay(7);
//            if (buttonInformation[8]!= 0)
//                screenRotationPlay(8);
//
//            for (int i =0;i<9;i++){
//                if (buttonInformation[i] != 0){
//                    screenRotationPlay(i);
//                }
//            }
//            System.out.println("!!!!!!"+Arrays.toString(buttonInformation));

        }
//        for (int z = 0; z<LENGTH;z++){
//            System.out.println("x: " +saveColoredButtonX[z]+"y: "+saveColoredButtonY[z]);
//            if (saveColoredButtonX[z] != -0 && saveColoredButtonY[z] != -1){
//                buttons[saveColoredButtonX[z]][saveColoredButtonY[z]].setBackgroundColor(Color.YELLOW);
//
//            }
//        }
        resetButton.setOnClickListener(v ->
        {
            Log.i(TAG, "Reset Button Clicked");
            resetLayoutBoard();
            boardGame.reset();
            boardGame.clear();
//            resetColor();
            resetPadMatrix();
            role = false;

//            mark1 = 0;
//            mark2 = 0;
//            player1.setText("0");
//            player2.setText("0");
            //reset mark => same functionality with start
        });
        startButton.setOnClickListener(v->
        {
            resetLayoutBoard();
            boardGame.reset();
            boardGame.clear();
//            resetColor();
            resetPadMatrix();
            role = false;
            Log.i(TAG, "Start Button Clicked");
            //start only reset game but not reset marks
        });
    }
    public int returnPad(String a,String b){
        int pad = -1;
        if (a.equals("0") && b.equals("0")){
            pad = 0;
        }
        else if (a.equals("0") && b.equals("1")){
            pad = 1;
        }
        else if (a.equals("0") && b.equals("2")){
            pad = 2;
        }
        else if (a.equals("1") && b.equals("0")){
            pad = 3;
        }
        else if (a.equals("1") && b.equals("1")){
            pad = 4;
        }
        else if (a.equals("1") && b.equals("2")){
            pad = 5;
        }
        else if (a.equals("2") && b.equals("0")){
            pad = 6;
        }
        else if (a.equals("2") && b.equals("1")){
            pad = 7;
        }
        else if (a.equals("2") && b.equals("2")){
            pad = 8;
        }
        return pad;
    }
    public void resetPadMatrix(){
        for (int i = 0;i< 9;i++){
            buttonInformation[i] = "-1";
        }
    }

    public void revoke() {
        boardGame = new game();
//        role = false;
        p1Go = true;
//        anOrder = new ArrayList<>();
//        location = new ArrayList<>();
//        mark1 = 0;
//        mark2 = 0;
        resetButton = findViewById(R.id.resetButton);
        startButton = findViewById(R.id.startButton);
//        player1 = findViewById(R.id.mark1);
//        player2 = findViewById(R.id.mark2);
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
//        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setText("");
//        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setText("");
//        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setText("");
//        buttons[saveColoredButtonX[0]][saveColoredButtonY[0]].setBackgroundResource(android.R.drawable.btn_default);
//        buttons[saveColoredButtonX[1]][saveColoredButtonY[1]].setBackgroundResource(android.R.drawable.btn_default);
//        buttons[saveColoredButtonX[2]][saveColoredButtonY[2]].setBackgroundResource(android.R.drawable.btn_default);
    }



    public void color(){
        if (boardGame.won()> 0){
            Log.d(TAG, "WINNING");
            String[] arr = boardGame.find(boardGame.won());
            for (int i = 0;i < 3;i++){
                saveColoredButtonX[i] = Character.getNumericValue(arr[i].charAt(0));
                saveColoredButtonY[i] = Character.getNumericValue(arr[i].charAt(1));
                buttons[saveColoredButtonX[i]][saveColoredButtonY[i]].setBackgroundColor(Color.YELLOW);
            }
            System.out.println("& ARRAY"+Arrays.toString(buttonInformation));
            System.out.println("Winning! Matrix is"+Arrays.toString(arr));
        }
        else
        {
            Log.d(TAG, "NOT WINNING");

        }
    }
//    public void aiPlace(){
//        if (!boardGame.isGameOver() && role) {
//            int[] ranCor = randomPlace();
//            int x = ranCor[0];
//            int y = ranCor[1];
//            putKeys(buttons[x][y], x, y);
//            Log.d(TAG, "AI JUST PLACED A KEY AT x:"+x+"y:"+y);
//            role = false;
//
//        }
//        else
//        {
//            Log.d(TAG, "AI FAIL");
//        }
//    }

    @Override
    public void onClick(View view){
        if (!((Button)view).getText().toString().equals(""))
            return;
        System.out.println("Is Game over?: "+boardGame.isGameOver());
        System.out.println("FLG :"+p1Go);

        if (!boardGame.isGameOver())
        {
            switch (view.getId()) {
                case R.id.button00:{
                    Log.d(TAG, "00 onClick: ");
                    if (p1Go){
                        putKeys(buttons[0][0],0,0,'x');
                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[0][0],0,0,'o');
                        p1Go = true;
                    }
                    //if player1's role
//                    aiPlace();
                    break;
                }
                case R.id.button01:{
                    Log.d(TAG, "01 onClick: ");
                    if (p1Go){
                        putKeys(buttons[0][1],0,1,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[0][1],0,1,'o');
//                        p1Go = true;
                    }//                    role = true;

//                    aiPlace();
                    break;
                }
                case R.id.button02:{
                    Log.d(TAG, "02 onClick: ");
                    if (p1Go){
                        putKeys(buttons[0][2],0,2,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[0][2],0,2,'o');
//                        p1Go = true;
                    }//                    role = true;
//                    aiPlace();
                    break;
                }
                case R.id.button10:{
                    Log.d(TAG, "10 onClick: ");
                    if (p1Go){
                        putKeys(buttons[1][0],1,0,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[1][0],1,0,'o');
//                        p1Go = true;
                    }//                    role = true;
//                    aiPlace();

                    break;
                }
                case R.id.button11:{
                    Log.d(TAG, "11 onClick: ");
                    if (p1Go){
                        putKeys(buttons[1][1],1,1,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[1][1],1,1,'o');
//                        p1Go = true;
                    }//                    role = true;
//                    aiPlace();
                    break;
                }
                case R.id.button12:{

                    Log.d(TAG, "12 onClick: ");
                    if (p1Go){
                        putKeys(buttons[1][2],1,2,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[1][2],1,2,'o');
//                        p1Go = true;
                    }//                    role = true;
//                    aiPlace();

                    break;
                }
                case R.id.button20:{
                    Log.d(TAG, "20 onClick: ");
                    if (p1Go){
                        putKeys(buttons[2][0],2,0,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[2][0],2,0,'o');
//                        p1Go = true;
                    }//                    role = true;
//                    aiPlace();

                    break;
                }
                case R.id.button21:{
                    Log.d(TAG, "21 onClick: ");
                    if (p1Go){
                        putKeys(buttons[2][1],2,1,'x');
//                        p1Go = false;
                    }
                    else{
                        putKeys(buttons[2][1],2,1,'o');
//                        p1Go = true;
                    }//                    role = true;
//                    aiPlace();

                    break;
                }
                case R.id.button22: {
                    Log.d(TAG, "22 onClick: ");
                    if (p1Go){
                        putKeys(buttons[2][2],2,2,'x');
//                        p1Go = !p1Go;
                    }
                    else{
                        putKeys(buttons[2][2],2,2,'o');
//                        p1Go = !p1Go;
                    }//                    role = true;
//                    aiPlace();

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
//            role = !role;
//            anOrder.add(play);
            String newA =  cordX+""+cordY+""+x;
            System.out.println("newA"+newA+"pad Value"+getPadValue(cordX,cordY));
//            location.add(newA);
            buttonInformation[getPadValue(cordX,cordY)] = newA;
            p1Go = !p1Go;
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
