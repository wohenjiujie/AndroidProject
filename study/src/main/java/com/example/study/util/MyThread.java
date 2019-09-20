package com.example.study.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

/**
 * Created by INvo on 2019-06-09.
 */
public class MyThread implements Runnable{
    private ProgressBar mBar;
    private int mType;
    private AlertDialog mDialog;
    private Context mContext;

    public MyThread(Context mContext) {
        this.mContext = mContext;
    }

    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            postDelayed(MyThread.this, 200);
        }
    };

    public void setProgress(ProgressBar bar, int type, @Nullable AlertDialog dialog) {
        this.mBar = bar;
        this.mType = type;
        this.mDialog = dialog;
        handler.sendEmptyMessage(0);
    }


    public ProgressBar getProgressBar() {
        return mBar;
    }

    public int getProgressType() {
        return mType;
    }

    public AlertDialog getProgressDiaolog() {
        return mDialog;
    }
    @Override
    public void run() {
        if (getProgressType() == 1) {
            if (getProgressBar().getProgress() < 100) {
                getProgressBar().setProgress(getProgressBar().getProgress() + 5);
                handler.sendEmptyMessage(0);
            } else {
                ToastUtil.showToast(mContext, "finished");
            }
        } else if (getProgressType() == 2) {
            if (getProgressBar().getProgress() < 100) {
                getProgressBar().setProgress(getProgressBar().getProgress() + 5);
                handler.sendEmptyMessage(0);
            } else {
                ToastUtil.showToast(mContext, "finished");
                getProgressDiaolog().dismiss();
            }
        }

    }
}


