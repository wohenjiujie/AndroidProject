package com.example.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import com.example.study.gridview.GridViewActivity;
import com.example.study.listview.ListViewActivity;
import com.example.study.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListener();
    }

    private void setListener() {
        Selector click = new Selector();
        findViewById(R.id.radio_btn).setOnClickListener(click);
        findViewById(R.id.et_btn).setOnClickListener(click);
        findViewById(R.id.cb_btn).setOnClickListener(click);
        findViewById(R.id.lv_btn).setOnClickListener(click);
        findViewById(R.id.gv_btn).setOnClickListener(click);
        findViewById(R.id.rv_btn).setOnClickListener(click);
        findViewById(R.id.toast_btn).setOnClickListener(click);
        findViewById(R.id.dialog_btn).setOnClickListener(click);
        findViewById(R.id.pb_btn).setOnClickListener(click);
        findViewById(R.id.popwindow_btn).setOnClickListener(click);

    }

    private class Selector implements View.OnClickListener {
        Intent intent = new Intent();

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.et_btn:
                    intent.setClass(MainActivity.this, EditTextActivity.class);
                    break;
                case R.id.radio_btn:
                    intent.setClass(getApplicationContext(), RadioButtonActivity.class);
                    break;
                case R.id.cb_btn:
                    intent.setClass(getApplicationContext(), CheckBoxActivity.class);
                    break;
                case R.id.lv_btn:
                    intent.setClass(getApplicationContext(), ListViewActivity.class);
                    break;
                case R.id.gv_btn:
                    intent.setClass(getApplicationContext(), GridViewActivity.class);
                    break;
                case R.id.rv_btn:
                    intent.setClass(getApplicationContext(), RecyclerViewActivity.class);
                    break;
                case R.id.toast_btn:
                    intent.setClass(getApplicationContext(), ToastViewActivity.class);
                    break;
                case R.id.dialog_btn:
                    intent.setClass(getApplicationContext(), AlertDialogActivity.class);
                    break;
                case R.id.pb_btn:
                    intent.setClass(getApplicationContext(), ProgressActivity.class);
                    break;
                case R.id.popwindow_btn:
                    intent.setClass(getApplicationContext(), PopWindowActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
