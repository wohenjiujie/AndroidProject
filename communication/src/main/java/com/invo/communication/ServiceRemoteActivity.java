package com.invo.communication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.invo.communication.service.LoginService;

public class ServiceRemoteActivity extends AppCompatActivity {


    Button button;
    EditText editText1, editText2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_remote);

initView();
    }

    void initView() {
        button = findViewById(R.id.service_start);
        editText1 = findViewById(R.id.service_1);
        editText2 = findViewById(R.id.service_2);
        textView = findViewById(R.id.service_response);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"正在提交请求",Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String user = editText1.getText().toString();
                        String pass = editText2.getText().toString();
                        final String response = LoginService.loginByPost(user, pass);

                        if(response != null){
                            showResponse(response);
                        }else{
                            showResponse("请求失败....");
                        }
                    }
                }).start();
            }
        });
    }


    private void showResponse(final  String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }
}
