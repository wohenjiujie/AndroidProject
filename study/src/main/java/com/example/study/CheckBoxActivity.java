package com.example.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class CheckBoxActivity extends AppCompatActivity {
    private CheckBox cb1, cb2;
    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        cb1 = findViewById(R.id.cb_1);
        cb2 = findViewById(R.id.cb_2);
        iv1 = findViewById(R.id.iv_1);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(CheckBoxActivity.this, isChecked ? "xxx" : "yyy", Toast.LENGTH_SHORT).show();
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Glide.with(CheckBoxActivity.this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559136529169&di=a6fee0298ac19f33c3145dc1777eeeee&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20161117%2F91358f6fc74f494eb65ef984909ef6d2_th.jpg").into(iv1);
                iv1.setVisibility(isChecked?View.VISIBLE:View.GONE);
            }
        });
    }
}
