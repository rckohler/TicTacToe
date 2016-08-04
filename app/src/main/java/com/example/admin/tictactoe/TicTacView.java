package com.example.admin.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by admin on 8/3/2016.
 */
public class TicTacView extends View {
    Board board;
    Random random;
    Mark currentPlayer = Mark.X;
    Mark playerMark = Mark.X,cpuMark = Mark.O;
    AI ai;
    public TicTacView(Context context) {
        super(context);
        board = new Board();
        random = new Random();
        //assignMarks();
        ai = new AI(board,cpuMark);
    }
    private void assignMarks(){
        if (random.nextBoolean()){
            cpuMark = Mark.O;
            playerMark = Mark.X;
        }
        else{
            cpuMark = Mark.X;
            playerMark = Mark.O;
        }
    }
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(board.handleClick(currentPlayer,x,y)){
                    checkForWin();
                    changePlayer();
     //               ai.makeMove();
       //             checkForWin();
                }
                if(y>TicTacMain.screenHeight*.66f){
                    board.clearBoard();
                }
                break;
        }
        return true;
    }
    private void changePlayer(){
        if (currentPlayer==Mark.X) currentPlayer = Mark.O;
        else currentPlayer = Mark.X;
    }
    private void checkForWin(){
        if(board.horizontalWin()) Toast.makeText(getContext(),"Horizontal Win",Toast.LENGTH_SHORT).show();
        if(board.diagonalWin())  Toast.makeText(getContext(),"Diagonal Win",Toast.LENGTH_SHORT).show();
        if(board.verticalWin())  Toast.makeText(getContext(),"Vertical Win",Toast.LENGTH_SHORT).show();

    }
    protected void onDraw(Canvas canvas){
        board.update(canvas);
        try{
            Thread.sleep(100);
        }
        catch(Exception e){
            ;
        }
        invalidate();
    }

}
