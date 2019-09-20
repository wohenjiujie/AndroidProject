package com.example.myandroid;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransformActivity extends AppCompatActivity implements View.OnClickListener {
    private Drawable icon;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        icon=getResources().getDrawable(R.drawable.rem);
        icon.setBounds(0,0,icon.getMinimumWidth(),icon.getMinimumHeight());
        findViewById(R.id.btnleft).setOnClickListener(this);
        findViewById(R.id.btnbottom).setOnClickListener(this);
        findViewById(R.id.btnright).setOnClickListener(this);
        findViewById(R.id.btntop).setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.btnleft){
            btn.setCompoundDrawables(icon,null,null,null);
        }else if(v.getId()==R.id.btnright){
            btn.setCompoundDrawables(null,null,icon,null);
        }else if(v.getId()==R.id.btntop){
            btn.setCompoundDrawables(null,icon,null,null);
        }else if(v.getId()==R.id.btnbottom){
            btn.setCompoundDrawables(null,null,null,icon);
        }
    }
}
