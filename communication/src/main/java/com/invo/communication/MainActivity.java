package com.invo.communication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.invo.communication.util.ToastUtil;

public class MainActivity extends AppCompatActivity {
    Selector click = new Selector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFunction();
    }

    private void setFunction() {

        findViewById(R.id.c_message).setOnClickListener(click);
        findViewById(R.id.c_async).setOnClickListener(click);
        findViewById(R.id.c_async_service).setOnClickListener(click);
        findViewById(R.id.service_test).setOnClickListener(click);
    }

    private class Selector implements View.OnClickListener {
        Intent intent = new Intent();

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.c_message:
                    intent.setClass(getApplicationContext(), MessageActivity.class);
                    break;
                case R.id.c_async:
                    ToastUtil.showToast(getApplicationContext(), "haha");
                    intent.setClass(getApplicationContext(), AsyncTaskActivity.class);
                    break;
                case R.id.c_async_service:
                    intent.setClass(getApplicationContext(), IntentServiceActivity.class);
                    break;
                case R.id.service_test:
                    intent.setClass(getApplicationContext(), ServiceRemoteActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
