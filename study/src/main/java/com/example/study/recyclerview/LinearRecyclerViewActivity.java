package com.example.study.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.study.R;
import com.example.study.util.MyInterface;


public class LinearRecyclerViewActivity extends AppCompatActivity {
    final static int VERTICAL = 1;
    final static int HORIZONTAL = 2;
    private RecyclerView mRv;
    private Switch mOrientation;
    private TextView tvSwitch;
    LinearAdapter linearAdapter = new LinearAdapter(this, null, new MyInterface.myRecyclerViewClickListener() {
        @Override
        public void onClick(int pos) {
            Toast.makeText(LinearRecyclerViewActivity.this, "sss", Toast.LENGTH_SHORT).show();
        }
    });
    MyDecoration myDecoration = new MyDecoration(this);
    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_recycler_view);
        mRv = findViewById(R.id.rv_linear);
        mOrientation = findViewById(R.id.hor_ver);
        tvSwitch = findViewById(R.id.tv_switch);
        tvSwitch.setText("垂直");
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearAdapter.setType(VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(linearAdapter);
//        mRv.addItemDecoration(new MyDecoration(this,1));
        myDecoration.setType(VERTICAL);
//        myDecoration.initRect();
        mRv.addItemDecoration(myDecoration);


        mOrientation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvSwitch.setText("水平");
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    linearAdapter.setType(HORIZONTAL);
                    mRv.setLayoutManager(linearLayoutManager);
                    /*mRv.setAdapter(new LinearAdapter(LinearRecyclerViewActivity.this, null, new onItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Toast.makeText(LinearRecyclerViewActivity.this, "success to" + pos, Toast.LENGTH_SHORT).show();
                        }
                    },2));*/
                    mRv.setAdapter(linearAdapter);
//                    mRv.addItemDecoration(new MyDecoration(LinearRecyclerViewActivity.this, 2));
                    myDecoration.setType(HORIZONTAL);

//                    myDecoration.resetMyDecoration();
                    mRv.addItemDecoration(myDecoration);
                } else {
                    tvSwitch.setText("垂直");
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    linearAdapter.setType(VERTICAL);
                    mRv.setLayoutManager(linearLayoutManager);
                    /*mRv.setAdapter(new LinearAdapter(LinearRecyclerViewActivity.this, null, new onItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Toast.makeText(LinearRecyclerViewActivity.this, "success to" + pos, Toast.LENGTH_SHORT).show();
                        }
                    },1));*/
                    mRv.setAdapter(linearAdapter);
//                    mRv.addItemDecoration(new MyDecoration(LinearRecyclerViewActivity.this, 1));
                    myDecoration.setType(VERTICAL);
//                    myDecoration.resetMyDecoration();
                    mRv.addItemDecoration(myDecoration);
                }
            }
        });
    }
}



