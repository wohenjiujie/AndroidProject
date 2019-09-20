package com.example.myandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.*;

public class PaomdActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView pmd;
    private boolean act=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paomd);
        pmd=findViewById(R.id.ctx);

        pmd.setOnClickListener(this);
        /*可优化的代码
        * findViewById(R.id.ctx).setOnClickListener(this);
        * 一句语言
        * */

        pmd.setSingleLine(true);
        pmd.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        pmd.setEllipsize(TextUtils.TruncateAt.START);//省略号在开头
        pmd.setFocusable(true);
        pmd.setFocusableInTouchMode(true);
    }

    public void onClick(View v){
        if(v.getId()==R.id.ctx){
            act=!act;
            if(act){
                pmd.setFocusableInTouchMode(false);
                pmd.setFocusable(false);
            }else{
                pmd.setFocusableInTouchMode(true);
                pmd.setFocusable(true);
            }
        }
    }
}
