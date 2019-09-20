package com.invo.communication.util;

/**
 * Created by INvo on 2019-08-04.
 */
public interface OnProgressListener {
    void onFinish(String result);
    void onCancel(String result);
    void onUpdate(String request, int progress, int sub_progress);
    void onBegin(String request);
}
