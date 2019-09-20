package com.invo.communication.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.invo.communication.BuildConfig;


/**
 * Created by INvo on 2019-08-09.
 */
public class AsyncService extends IntentService {
    private static final String TAG = "AsyncService";



    public AsyncService() {
        super("com.invo.communication.service.AsyncService");
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
       Log.i(TAG, "onStartCommand");
       /*
       * 如果在这里沉睡（时间3秒），则在此期间无法点击按钮,因为onStartCommand位于主线程
       * */
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "begin onHandleIntent");
        //在onHandleIntent这里执行耗时任务，不会影响页面的处理——onHandleIntent位于分线程
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "end onHandleIntent");

    }
}
