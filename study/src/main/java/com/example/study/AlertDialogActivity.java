package com.example.study;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.study.util.ToastUtil;

import java.security.interfaces.DSAKey;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        onClick click = new onClick();
        findViewById(R.id.dialog_1).setOnClickListener(click);
        findViewById(R.id.dialog_2).setOnClickListener(click);
        findViewById(R.id.dialog_3).setOnClickListener(click);
        findViewById(R.id.dialog_4).setOnClickListener(click);
        findViewById(R.id.dialog_5).setOnClickListener(click);
    }

    private class onClick implements View.OnClickListener {
        final String[] array = new String[]{"1", "2","3"};
        final String[] notification = new String[]{"haha", "hoho","hehe"};

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_1:
                    //默认样式

                    /*
                    * ：AlertDialog创建语句public AlertDialog.Builder (Context context)中
                    * 不能使用getApplicationContext()得到的context，而必须使用Activity
                    *将 new AlertDialog.Builder(getApplicationContext()) 改为 new AlertDialog.Builder(xx.this)
                    * */
                    AlertDialog.Builder mBuilder1 = new AlertDialog.Builder(AlertDialogActivity.this);
                    mBuilder1.setTitle("diy1").setMessage("diy1").setIcon(R.drawable.mood_smile)
                            .setNegativeButton("diy1", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ToastUtil.showToast(getApplicationContext(), "diy1");
                                }
                            }).setPositiveButton("diy1", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showToast(getApplicationContext(), "diy1");

                        }
                    }).setNeutralButton("diy1", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showToast(getApplicationContext(), "diy1");

                        }
                    }).show();
                    break;
                case R.id.dialog_2:
                    //单选样式
                    AlertDialog.Builder mBuilder2 = new AlertDialog.Builder(AlertDialogActivity.this);
                    mBuilder2.setTitle("diy2")
                            .setItems(array, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showToast(getApplicationContext(),notification[which]);
//                            dialog.dismiss();
                            Log.d("onClick", "dialog:" + dialog);
                        }
                    }).show();
                    break;
                case R.id.dialog_3:
                    //radioButton样式
                    /*
                    * 默认是点击对话框以外的地方对话框消失
                    * 这里设置点击对话框完成后对话框消失
                    * */
                    AlertDialog.Builder mBuilder3 = new AlertDialog.Builder(AlertDialogActivity.this);
                    mBuilder3.setTitle("diy3")
                            .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ToastUtil.showToast(getApplicationContext(), notification[which]);
                                }
                            }).setCancelable(true).show();
                    break;
                case R.id.dialog_4:
                    //多选
                    AlertDialog.Builder mBuilder4 = new AlertDialog.Builder(AlertDialogActivity.this);
                    final boolean[] isSelected = new boolean[]{false, true, false};
                    mBuilder4.setTitle("diy4")
                            .setMultiChoiceItems(array, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    ToastUtil.showToast(getApplicationContext(), array[which] + ":" + isChecked);
                                }
                            }).setPositiveButton("diy4", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("diy4", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setCancelable(false).show();
                    break;
                case R.id.dialog_5:
//                    自定义dialog——此处为实现editText，editText既可以在activity中使用也可以被其他元件调用
                    
                    /*
                    * 如何在自定义监听里关闭或者传入Dialog
                    * log
                    * 第一种方法
                    * 用LayoutInflater载入xml
                    *
                    * 第二种方法直接getLayout id
                    * mBuilder.setTitle("diy4").setView(R.layout.activity_edit_text).setCancelable(false).show();
                    * 元件属性功能设置用id find
                    * 但第二种可能会出现找不到ctx的问题，所以一般使用第一种
                    * */
                    final AlertDialog.Builder mBuilder5= new AlertDialog.Builder(AlertDialogActivity.this);
                    View myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_edit_text, null);
                    final Button login = myView.findViewById(R.id.ed_diy);
                    mBuilder5.setTitle("diy4").setView(myView).setCancelable(false);
                    final AlertDialog dialog = mBuilder5.show();
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.showToast(getApplicationContext(),"succeed");
                            dialog.dismiss();
                        }
                    });
                    break;
            }
        }
    }
}
