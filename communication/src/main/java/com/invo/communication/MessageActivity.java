package com.invo.communication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.invo.communication.util.DataUtil;
import com.invo.communication.util.ToastUtil;

import static java.lang.Thread.sleep;

public class MessageActivity extends AppCompatActivity {
    private TextView showMessage;
    private boolean Play = false;
    private int Begin=0, Continue = 1, Stop = 2;
    static private String[] myNews = {
            "中国航天员在天宫二号泡茶，羡煞歪果仁",
            "美国大选顺利闭幕，特朗普高票当选",
            "越南撤销日本获得订单的核电站计划",
            "上海建成卓越全球城市，新市镇轨交全覆盖",
            "土耳其老人怀抱受伤山羊错跑入医院急诊室",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();
    }

    private void init() {
        showMessage = findViewById(R.id.c_show_message);
        showMessage.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.c_stop_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(getApplication(),"stop");
                Play = false;
            }
        });
        findViewById(R.id.c_start_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(getApplication(),"start");

                if (Play != true) {
                    Play = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(Begin);
                            while (Play == true) {
                                try {
                                    sleep(20);
                                } catch (InterruptedException e) {
                                    ToastUtil.showToast(getApplicationContext(), "sleep false!!!");
                                }
                                Message message = Message.obtain();
                                message.what = Continue;
                                message.obj = myNews[(int) (Math.random() * 5)];
                                handler.sendMessage(message);
                            }
                            try {
                                sleep(20);
                            } catch (InterruptedException e) {
                                ToastUtil.showToast(getApplicationContext(), "sleep false!!!");
                            }
                            handler.sendEmptyMessage(Stop);
                            Play = false;

                        }
                    }).start();
                }
            }
        });
    }

    private Handler handler=new Handler() {

        @Override
        public void handleMessage(Message msg) {
            String news = showMessage.getText().toString();
            if (msg.what == Begin) {
                news = String.format("%s%s %s\n", news, DataUtil.nowTime(), "Start To Show NEWS: ");
            } else if (msg.what == Stop) {
                news = String.format("%s%s %s\n", news, DataUtil.nowTime(), "End To Show NEWS: ");
            } else if (msg.what == Continue) {
                news = String.format("%s%s %s\n", news, DataUtil.nowTime(), msg.obj);
            }
            showMessage.setText(news);
        }
    };
}
