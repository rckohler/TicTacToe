package com.example.admin.tictactoe;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class TicTacMain extends AppCompatActivity {
    static Bitmap blank, x, o;
    static float screenWidth, screenHeight;
    TicTacView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        loadBitmaps();
        v = new TicTacView(this);
        setContentView(v);
    }
    private void loadBitmaps(){
        x = BitmapFactory.decodeResource(getResources(),R.drawable.x);
        o = BitmapFactory.decodeResource(getResources(),R.drawable.o);
    }
}
