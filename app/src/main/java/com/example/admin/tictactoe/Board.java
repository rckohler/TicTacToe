package com.example.admin.tictactoe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Vector;

/**
 * Created by admin on 8/3/2016.
 */
enum Mark{X,O,BLANK}
public class Board {
    static int COLS = 3, ROWS = 3;

Vector<Section> sections = new Vector<>();
    public Board(){
        createEmptyBoard();
    }
    private void createEmptyBoard(){
        float sectionSize=TicTacMain.screenWidth*.33f;

        for(int row = 0; row < ROWS; row ++)
            for (int col = 0; col < COLS; col++) {
                RectF sectionBounds = new RectF(col*sectionSize,row*sectionSize,(col+1)*sectionSize,(row+1)*sectionSize);
                sections.add(new Section(col, row, sectionBounds));
            }
    }
    public void clearBoard(){
        for (int i = 0; i < sections.size(); i++){
            sections.elementAt(i).mark = Mark.BLANK;
        }
    }
    public boolean handleClick(Mark mark, float tapX, float tapY){
        boolean ret = false;
        int n = sections.size();
        for(int i = 0; i < n; i++){
            if(sections.elementAt(i).handleTap(mark, tapX,tapY))ret = true;
        }
        return ret;
    }
    public boolean horizontalWin(){
        boolean ret = false;

        for(int i = 0; i < ROWS; i++)
        {
            if(sections.elementAt(0+i*COLS).mark!=Mark.BLANK){
                if(sections.elementAt(0+i*COLS).mark == sections.elementAt(1+i*COLS).mark && sections.elementAt(1+i*COLS).mark == sections.elementAt(2+i*COLS).mark) ret = true;
            }
        }
        return ret;
    }
    public boolean verticalWin(){
        boolean ret = false;
            //0 1 2
            //3 4 5
            //6 7 8
            for (int i = 0; i < COLS; i++)
                if(sections.elementAt(i).mark!=Mark.BLANK)
                    if(sections.elementAt(i).mark == sections.elementAt(i+COLS).mark && sections.elementAt(i+COLS).mark == sections.elementAt(i+2*COLS).mark) ret = true;
            return ret;
    }

    public boolean diagonalWin(){
        boolean ret = false;
        //0 1 2
        //3 4 5
        //6 7 8
        if(sections.elementAt(0).mark != Mark.BLANK && sections.elementAt(0).mark == sections.elementAt(4).mark && sections.elementAt(4).mark == sections.elementAt(8).mark) ret = true;
        if(sections.elementAt(6).mark != Mark.BLANK && sections.elementAt(6).mark == sections.elementAt(4).mark && sections.elementAt(4).mark == sections.elementAt(2).mark) ret = true;
        return ret;
    }
    public void update(Canvas canvas){
        int n = sections.size();
        for(int i = 0; i < n; i++){
            sections.elementAt(i).update(canvas);
        }
    }


}

class Section{
    int col, row;
    Mark mark;
    RectF outerBounds;
    RectF innerBounds;
    Paint paint = new Paint();
    public Section (int col, int row, RectF bounds){
        this.col = col;
        this.row = row;
        this.outerBounds = bounds;
        float wallThickness = (float)(bounds.width()*.05);
        mark = Mark.BLANK;
        this.innerBounds = new RectF(bounds.left+wallThickness,bounds.top+wallThickness,bounds.right-wallThickness,bounds.bottom-wallThickness);
    }
    private void drawSymbol(Canvas canvas){
        switch(mark){
            case X:
                canvas.drawBitmap(TicTacMain.x,null,innerBounds,null);
                break;
            case O:
                canvas.drawBitmap(TicTacMain.o,null,innerBounds,null);
                break;
            case BLANK:
                break;
        }
    }
    private boolean wasTapped(float tapX, float tapY){
        boolean ret = false;
            if (innerBounds.contains(tapX,tapY))
                ret = true;
        return ret;
    }

    public boolean handleTap(Mark mark, float tapX, float tapY){
        boolean ret = false;
        if (wasTapped(tapX,tapY)&& this.mark == Mark.BLANK)
        {
            this.mark = mark;
            ret = true;
        }
        return ret;
    }
    public void update(Canvas canvas){
        paint.setColor(Color.BLACK);
        canvas.drawRect(outerBounds,paint);
        paint.setColor(Color.WHITE);
        canvas.drawRect(innerBounds,paint);
        drawSymbol(canvas);
    }

}
