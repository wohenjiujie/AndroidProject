package com.example.myandroid;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Callable;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button px =findViewById(R.id.btnpx);
        Button color=findViewById(R.id.btncolor);
        Button screen=findViewById(R.id.btnscreen);
        Button blank=findViewById(R.id.btnblank);
        Button gravity=findViewById(R.id.btngravity);
        Button scroll=findViewById(R.id.btnscroll);
        Button marquee=findViewById(R.id.btnmarquee);
        Button chatlive=findViewById(R.id.btnchatlive);
        Button click=findViewById(R.id.btnclick);
        Button stretch=findViewById(R.id.btnstretch);
        Button capture=findViewById(R.id.btncapture);
        Button transform=findViewById(R.id.btntransf);
        Button list=findViewById(R.id.btnlist);
        Button icon=findViewById(R.id.btnicon);
        Button picture=findViewById(R.id.btnpicture);
        Button cal=findViewById(R.id.btncal);

        px.setOnClickListener(this);
        color.setOnClickListener(this);
        screen.setOnClickListener(this);
        blank.setOnClickListener(this);
        gravity.setOnClickListener(this);
        scroll.setOnClickListener(this);
        marquee.setOnClickListener(this);
        chatlive.setOnClickListener(this);
        click.setOnClickListener(this);
        stretch.setOnClickListener(this);
        capture.setOnClickListener(this);
        transform.setOnClickListener(this);
        list.setOnClickListener(this);
        icon.setOnClickListener(this);
        picture.setOnClickListener(this);
        cal.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.btnpx){
            Intent intent=new Intent(this,Main2Activity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btncolor){
            Intent intent=new Intent(this,Main3Activity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnscreen){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnblank){
            Intent intent=new Intent(this,MarginPaddingActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btngravity){
            Intent intent=new Intent(this, GravityActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnscroll){
            Intent intent=new Intent(this,ScrollActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnmarquee){
            Intent intent=new Intent(this,PaomdActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnchatlive){
            Intent intent=new Intent(this,ChatLiveActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnclick){
            Intent intent=new Intent(this,ButtonActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnstretch){
            Intent intent=new Intent(this,StretchActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btncapture){
            Intent intent=new Intent(this,ScreenShotActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btntransf){
            Intent intent=new Intent(this,TransformActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnlist){
            Intent intent=new Intent(this,ListActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnicon){
            Intent intent=new Intent(this,IconActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnpicture){
            Intent intent=new Intent(this,PictureActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btncal){
            Intent intent=new Intent(Intent.ACTION_MAIN);
            ComponentName componentName = new ComponentName("com.example.junior", "com.example.junior.MainActivity");
            intent.setComponent(componentName);
            startActivity(intent);
        }
    }
}
