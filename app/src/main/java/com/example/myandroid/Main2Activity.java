package com.example.myandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
/*字体研究*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int dip=DisplayInfo.dip2px(this,20L);
        TextView large=findViewById(R.id.large);
        large.setPadding(dip,dip,dip,dip);
    }
}
