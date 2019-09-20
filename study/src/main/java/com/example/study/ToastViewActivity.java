package com.example.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.study.util.ToastUtil;

import java.util.zip.Inflater;

public class ToastViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_view);
        onClick click = new onClick();

        findViewById(R.id.toast_1).setOnClickListener(click);
        findViewById(R.id.toast_2).setOnClickListener(click);
        findViewById(R.id.toast_3).setOnClickListener(click);
        findViewById(R.id.toast_4).setOnClickListener(click);
    }

    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.toast_1:
                    //默认
                    Toast.makeText(ToastViewActivity.this, "this is default toast", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.toast_2:
                    Toast toastCenter = Toast.makeText(getApplicationContext(), "this is center toast", Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER, 0, 0);
                    toastCenter.show();
                    break;
                case R.id.toast_3:
                    Toast toastDiy = new Toast(getApplicationContext());
                    LayoutInflater myInflater = LayoutInflater.from(getApplicationContext());
                    View myToastView = myInflater.inflate(R.layout.toast_layout, null);
                    ImageView iv = myToastView.findViewById(R.id.iv_toast);
                    TextView tv = myToastView.findViewById(R.id.tv_toast);
                    tv.setText("this is a image toast");
                    iv.setImageResource(R.drawable.mood);
                    toastDiy.setView(myToastView);
                    toastDiy.setDuration(Toast.LENGTH_LONG);
                    toastDiy.show();
                    break;
                case R.id.toast_4:
                    ToastUtil.showToast(getApplicationContext(),"this is a package toast");
                    break;
            }
        }
    }
}
