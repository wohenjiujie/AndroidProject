package com.example.study.listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.study.R;

/**
 * Created by INvo
 * on 2019-06-01.
 */
public class ListViewActivity extends Activity  {
    /*
     * ListView已经不常用，推荐使用recyclerView
     * */

    private ListView mLv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mLv1 = findViewById(R.id.lv_1);
        mLv1.setAdapter(new MyListAdapter(ListViewActivity.this));
        /*
        * onItemClickListener实现跳转
        * */
        mLv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "pos:" + position, Toast.LENGTH_SHORT).show();
                return false;
                /*
                * return false不会停止接下来的操作
                * return true会只执行longClickListener的操作
                * */
            }
        });
    }
}
