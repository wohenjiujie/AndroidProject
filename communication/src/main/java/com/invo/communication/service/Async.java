package com.invo.communication.service;

import android.os.AsyncTask;
import android.util.Log;

import com.invo.communication.BuildConfig;
import com.invo.communication.util.OnProgressListener;

/**
 * Created by INvo on 2019-08-04.
 */
public class Async extends AsyncTask<String, Integer, String> {
    private String mBook;
    private OnProgressListener mListener;

    public void setOnProgressListener(OnProgressListener listener) {
        mListener = listener;
    }

    public Async(String title) {
        super();
        mBook = title;
    }

    @Override
    protected String doInBackground(String... strings) {
        int ratio = 0;
        for (; ratio <= 100; ratio += 5) {
            // 睡眠200毫秒模拟网络通信处理
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(ratio);
            /*
            * 如果ratio大于50则取消任务
            * */
//             if (ratio >= 50) {
//             cancel(false);
//             }
        }
        if (BuildConfig.DEBUG) Log.d("Async", strings[0]);
        return strings[0];
    }


    @Override
    protected void onCancelled(String s) {
        /*触发监听器的取消事件*/
        mListener.onCancel(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        /*触发监听器的进度更新事件*/
        mListener.onUpdate(mBook, values[0], 0);
    }

    @Override
    protected void onPostExecute(String s) {
        /*触发监听器的结束事件*/
        mListener.onFinish(s);
    }

    @Override
    protected void onPreExecute() {
        /*触发监听器的开始事件*/
        mListener.onBegin(mBook);
    }
}
