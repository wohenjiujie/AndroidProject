package com.example.myandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.text.*;

/*两种方法
* 1 在动作窗口直接定义接口用this返回-----ChatLiveActivity中已实现过此方法
* 2 在内部类方法定义接口然后实例化*/
public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button click=findViewById(R.id.btnclick);
        Log.e("ButtonActivity", "click:" + click);
        click.setOnClickListener(new onClick());
        click.setOnLongClickListener(new onLongClick());
    }

    class onClick implements View.OnClickListener{
        public void onClick(View v){
            if(v.getId()==R.id.btnclick){
                Toast.makeText(ButtonActivity.this,"you click"+((TextView)v).getText(),Toast.LENGTH_SHORT).show();;
            }
        }

    }
/*两个方法中用的tostring
* 找不到gettext方法
* 已解决*/
    class onLongClick implements View.OnLongClickListener{
        public boolean onLongClick(View v){
            if(v.getId()==R.id.btnclick){
                Toast.makeText(ButtonActivity.this,"you longclick"+((TextView) v).getText(),Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
