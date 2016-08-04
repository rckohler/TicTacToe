package com.example.admin.tictactoe;

/**
 * Created by admin on 8/3/2016.
 */
public class AI {
    Mark cpuMark;
    Board board;
    public AI(Board board, Mark cpuMark){
        this.board = board;
        this.cpuMark = cpuMark;
    }
    public void makeMove(){
        for(int i = 0; i < 9; i ++){
            if(board.sections.elementAt(i).mark == Mark.BLANK)
            {
                board.sections.elementAt(i).mark = cpuMark;
                i = 9;
            }
        }
    }

}
