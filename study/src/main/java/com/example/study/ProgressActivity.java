package com.example.study;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.study.util.MyThread;
import com.example.study.util.ToastUtil;

public class ProgressActivity extends AppCompatActivity {
    /*
     * ProgressDialog方法已在高版本api中弃用
     * */


    private ProgressBar pb1;
    MyThread myThread = new MyThread(this);
    PopupWindow myPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        pb1 = findViewById(R.id.pb_1);
        onClick click = new onClick();
        findViewById(R.id.pb_test).setOnClickListener(click);
        findViewById(R.id.pb_diy1).setOnClickListener(click);
        findViewById(R.id.pb_diy2).setOnClickListener(click);
        findViewById(R.id.pop_dialog).setOnClickListener(click);
    }

    private class onClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pb_test:
                    ToastUtil.showToast(ProgressActivity.this,"test button");
                    myThread.setProgress(pb1, 1, null);
                    break;
                case R.id.pb_diy1:
                    ToastUtil.showToast(getApplicationContext(), "test");
                    ProgressDialog progressDialog = new ProgressDialog(ProgressActivity.this);
                    progressDialog.setTitle("diy");
                    progressDialog.setMessage("diy");
                    progressDialog.show();
                    break;
                case R.id.pb_diy2:
                    AlertDialog.Builder mBuilder1 = new AlertDialog.Builder(ProgressActivity.this);
                    View myBar = LayoutInflater.from(ProgressActivity.this).inflate(R.layout.invo_progress_bar, null);
                    final ProgressBar invo = myBar.findViewById(R.id.pb_invo);
                    mBuilder1.setTitle("diy1").setMessage("diy").setView(myBar).setCancelable(false);
                    final AlertDialog dialog = mBuilder1.show();
                    myThread.setProgress(invo,2,dialog);
                    Log.d("onClick", "getApplicationContext():" + getApplicationContext());
                    Log.d("onClick", "ProgressActivity.this:" + ProgressActivity.this);
                    break;
                case R.id.pop_dialog:
                    View myPopView = getLayoutInflater().inflate(R.layout.layout_pop_view, null);
                    TextView pop1 = myPopView.findViewById(R.id.tv_pop1);
                    myPop = new PopupWindow(myPopView, findViewById(R.id.pop_dialog).getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
                    myPop.setOutsideTouchable(true);
                    myPop.setFocusable(true);
                    myPop.showAsDropDown(findViewById(R.id.pop_dialog));
                    pop1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myPop.dismiss();
                            //diy
                            ToastUtil.showToast(ProgressActivity.this,"diy");
                        }
                    });

                    break;
            }
        }
    }
}
