package com.example.myandroid;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GravityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);
        LinearLayout zuo=findViewById(R.id.zuo);
        //zuo.setGravity(View.FOCUS_RIGHT);
        LinearLayout you=findViewById(R.id.you);

    }
}
