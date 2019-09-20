package com.example.myandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ScreenShotActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private TextView chat;
    private ImageView shot;
    private String []text={"aa","bb","cc","dd","ee","ff","gg"};
    private boolean check=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);

        chat=findViewById(R.id.chat);//chat视图
        shot=findViewById(R.id.imageshot);//view for shot

        chat.setDrawingCacheEnabled(true);//设置缓存可用状态
        /*findViewById(R.id.imageshot).setDrawingCacheEnabled(true);
        注意不能直接引用上述语句，因为没有指明设置哪一个视图的绘制缓存
        所以需要进行特殊声明*/

        check=findViewById(R.id.chat).isDrawingCacheEnabled();
        findViewById(R.id.imageshot).setBackgroundColor(getResources().getColor(R.color.huang));

        //监听
        findViewById(R.id.buttonchat).setOnClickListener(this);
        findViewById(R.id.buttonchat).setOnLongClickListener(this);
        findViewById(R.id.buttonshot).setOnClickListener(this);

        chat.setGravity(Gravity.LEFT| Gravity.TOP);
        chat.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClick(View v){
       if(v.getId()==R.id.buttonchat){
           int random=(int)(Math.random()*7);
           String content=String.format("%s\n%s %s",chat.getText().toString(),DateInfo.nowtime(),text[random]);
           chat.setText(content);
       }else if(v.getId()==R.id.buttonshot){
           if(check){
               shot.setDrawingCacheQuality(GridView.DRAWING_CACHE_QUALITY_AUTO);
               Bitmap screenshot=chat.getDrawingCache();
               shot.setImageBitmap(screenshot);
                /*注意这里在截图完毕后不能马上关闭绘图缓存，因为画面渲染需要时间，
                如果立即关闭缓存，渲染画面就会找不到位图对象，会报错
                “java.lang.IllegalArgumentException: Cannot draw recycled bitmaps”*/
                handle.postDelayed(resetcache,200);//延迟200ms，再执行runnable
           }else{
               shot.setImageDrawable(getResources().getDrawable(R.drawable.warning));
           }
       }
    }

    private Handler handle=new Handler();
    private Runnable resetcache=new Runnable() {
        @Override
        public void run() {
            chat.setDrawingCacheEnabled(false);
            chat.setDrawingCacheEnabled(true);
            /*经测试这两个语句不能替换顺序*/
        }
    };

    public boolean onLongClick(View v){
        if(v.getId()==R.id.buttonchat){
//            (TextView)findViewById(R.id.chat).setText("");//直接引用会报错
            chat.setText("");
        }
        return true;
    }
}
