package com.invo.communication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.invo.communication.service.AsyncService;
import com.invo.communication.util.DataUtil;
import com.invo.communication.util.ToastUtil;

public class IntentServiceActivity extends AppCompatActivity {
    private TextView testText;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        testText = findViewById(R.id.service_test);
        findViewById(R.id.async_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testText.setText(DataUtil.nowTime() + "Service Start...");
            }
        });
        handler.postDelayed(runnable, 100);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), AsyncService.class);
            startService(intent);
        }
    };
}
