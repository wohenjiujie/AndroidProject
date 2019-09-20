package com.example.invo.myplanttest.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by INvo on 2019-07-08.
 */
public class ToastUtil {
    private static Toast mToast;
    final static String myMsg = "Error!!!";

    public static void showToast(Context ctx, @Nullable String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
