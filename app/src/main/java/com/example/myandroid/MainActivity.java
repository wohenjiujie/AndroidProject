package com.example.myandroid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
/*获取屏幕参数*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView seek=findViewById(R.id.seek);
        seek.setTextColor(Color.CYAN);
        int width=DisplayInfo.getScreenWidth(this);
        int height=DisplayInfo.getScreenHeight(this);
        float density=DisplayInfo.getScreenDensity(this);
        String text1= String.format("width:%dpx,height:%dpx,density:%f",width,height,density);
        seek.setText(text1);
        seek.setTextSize(30);
        seek.setVisibility(View.VISIBLE);

    }



}
