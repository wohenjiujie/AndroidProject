package com.invo.communication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.invo.communication.service.Async;
import com.invo.communication.util.OnProgressListener;

public class AsyncTaskActivity extends AppCompatActivity implements OnProgressListener {
    private TextView at_async, at_tv;
    private ProgressBar at_pb;
    private Spinner at_sp;
    private static int BAR_HORIZONTAL = 1;
    private static int DIALOG_CIRCLE = 2;
    private static int DIALOG_HORIZONTAL = 3;
    private int mShowMode;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        at_async = findViewById(R.id.at_async);
        at_tv = findViewById(R.id.at_tv);
        at_pb = findViewById(R.id.at_pb);
        at_sp = findViewById(R.id.at_sp);
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<>(this,
                R.layout.item_select, bookArray);
        at_sp.setPrompt(at_tv.getText());
        at_sp.setAdapter(styleAdapter);
        at_sp.setOnItemSelectedListener(new StyleSelectedListener());
        at_sp.setSelection(0);

    }

    private static String[] bookArray = {"三国演义", "西游记", "红楼梦"};
    private static int[] styleArray = {BAR_HORIZONTAL, DIALOG_CIRCLE, DIALOG_HORIZONTAL};


    class StyleSelectedListener implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            /*将加载模式、书籍名称传入startTask*/
            startTask(styleArray[i], bookArray[i]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }

    private void startTask(int mode, String msg) {
        mShowMode = mode;
        Async async = new Async(msg);
        async.setOnProgressListener(this);
        async.execute(msg);
    }

    private void closeDialog() {
        if (progressDialog != null && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFinish(String result) {
        String desc = String.format("您要阅读的《%s》已经加载完毕", result);
        at_async.setText(desc);
        if (BuildConfig.DEBUG) Log.d("AsyncTaskActivity", result);
        closeDialog();
    }

    @Override
    public void onCancel(String result) {
        String desc = String.format("您要阅读的《%s》已经取消加载", result);
        at_async.setText(desc);
        closeDialog();
    }

    @Override
    public void onUpdate(String request, int progress, int sub_progress) {
        String desc = String.format("%s当前加载进度为%d%%", request, progress);
        at_async.setText(desc);
        if (mShowMode == BAR_HORIZONTAL) {
            at_pb.setProgress(progress);
            at_pb.setSecondaryProgress(sub_progress);
        } else if (mShowMode == DIALOG_HORIZONTAL) {
            progressDialog.setProgress(progress);
            progressDialog.setSecondaryProgress(sub_progress);
        }
    }

    @Override
    public void onBegin(String request) {
        at_async.setText(request + "开始加载");
        if (progressDialog == null || progressDialog.isShowing() != true) {
            if (mShowMode == DIALOG_CIRCLE) {
                progressDialog = ProgressDialog.show(this, "稍等", request + "页面加载中……");
            } else if (mShowMode == DIALOG_HORIZONTAL) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("稍等");
                progressDialog.setMessage(request + "页面加载中……");
                progressDialog.setIcon(R.drawable.ic_search);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
            }
        }
    }
}
