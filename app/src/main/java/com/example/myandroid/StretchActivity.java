package com.example.myandroid;

import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StretchActivity extends AppCompatActivity implements View.OnClickListener {
    private static  ImageView scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);
        scale=findViewById(R.id.wallpaper);
        findViewById(R.id.fitxy).setOnClickListener(this);
        findViewById(R.id.fitstart).setOnClickListener(this);
        findViewById(R.id.fitcenter).setOnClickListener(this);
        findViewById(R.id.fitend).setOnClickListener(this);
        findViewById(R.id.center).setOnClickListener(this);
        findViewById(R.id.centercrop).setOnClickListener(this);
        findViewById(R.id.centerinside).setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.fitxy){
            scale.setScaleType(ImageView.ScaleType.FIT_XY);
        }else if(v.getId()==R.id.fitstart){
            scale.setScaleType(ImageView.ScaleType.FIT_START);
        }else if(v.getId()==R.id.fitcenter){
            scale.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else if(v.getId()==R.id.fitend){
            scale.setScaleType(ImageView.ScaleType.FIT_END);
        }else if(v.getId()==R.id.center){
            scale.setScaleType(ImageView.ScaleType.CENTER);
        }else if(v.getId()==R.id.centercrop){
            scale.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else if(v.getId()==R.id.centerinside){
            scale.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }
}
