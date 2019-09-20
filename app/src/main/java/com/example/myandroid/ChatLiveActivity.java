package com.example.myandroid;

import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.format.DateUtils;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.widget.TextView;
import java.util.*;
import android.util.*;

public class ChatLiveActivity extends AppCompatActivity implements TextView.OnClickListener, TextView.OnLongClickListener {
    private TextView cl,title;
    private String []chat={"aa","bb","cc","dd","ee","ff","gg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_live);
        cl=findViewById(R.id.chatlive);
        title=findViewById(R.id.title);
        title.setOnClickListener(this);
        title.setOnLongClickListener(this);
        cl.setOnClickListener(this);
        cl.setOnLongClickListener(this);
        cl.setGravity(Gravity.LEFT|Gravity.BOTTOM);
        cl.setLines(8);//测试是否需要设置
        cl.setMaxLines(8);
        cl.setMovementMethod(new ScrollingMovementMethod());//设置文本移动方式，测试阶段可不使用
    }
    /*短按添加语句*/
    public void onClick(View v){
        if(v.getId()==R.id.chatlive||v.getId()==R.id.title){
            int random=(int)(Math.random()*7);
//            String content3=String.format("%s %s", DateInfo.nowtime(),chat[random]);
//            String content2=String.format(cl.getText().toString(),DateInfo.nowtime(),chat[random]);
            String content=String.format("%s\n%s %s%s",cl.getText().toString(),DateInfo.nowtime(),chat[random],"haha");
//            String content4=String.format("\n%s %s%s",DateInfo.nowtime(),chat[random],"haha");
/*第一段语句不可用，第二段可用
* 所以cl.gettext（）和与之对应的%s指代*/
            cl.setText(content);
        }
    }
/*此代码为测试使用，测试内容为关于随机数对应的字符串生成与传送字符串的形成形式*/

    /*长按删除、清空*/
    public boolean onLongClick(View v){
        if(v.getId()==R.id.chatlive||v.getId()==R.id.title){
            cl.setText("");
        }
        return true;
    }


}
