package com.example.myandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
/*colordebug*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView tv_code_six = findViewById(R.id.six);
        tv_code_six.setBackgroundColor(0x00ff00);
        TextView tv_code_eight = findViewById(R.id.eight);
        tv_code_eight.setBackgroundColor(0xff00ff00);
    }
}
