package com.example.myandroid;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayInfo {
    public static int getScreenWidth(Context ctx){
        WindowManager wm=(WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE);
//        从系统服务中获取窗口管理器

        DisplayMetrics dm=new DisplayMetrics();
//        从默认显示其中获取显示参数保存到dm对象中

        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
//        返回屏幕参数值
    }

    public static int getScreenHeight(Context ctx){
        WindowManager wm=(WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static float getScreenDensity(Context ctx){
        WindowManager wm=(WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    //dp>>px
    public static int dip2px(Context context,float dpValue){
        //获取当前手机像素密度
        final float scale=context.getResources().getDisplayMetrics().density;
        return(int)(dpValue*scale+0.5f);//四舍五入取整
    }

    //px>>dp
    public static int px2dip(Context context,float pxValue){
        //获取当前手机像素密度
        final float scale=context.getResources().getDisplayMetrics().density;
        return(int)(pxValue*scale+0.5f);//四舍五入取整
    }
}
