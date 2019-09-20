package com.example.study.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.study.R;

/**
 * Created by INvo
 * on 2019-06-01.
 */
public class MyListAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mLayoutInflater;

    MyListAdapter(Context context) {
        this.mContext=context;
        mLayoutInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        /*
        * 返回列表数量
        * */
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        /*
        * 高效的holder处理方法
        * */
        public ImageView imageView;
        public TextView tvTitle,tvTime,tvContent;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            holder.tvContent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        控件赋值（会覆盖xml中的预览）
        Glide.with(mContext).load("http://s9.rr.itc.cn/r/wapChange/20172_16_16/a9at4t3640870697619.jpg").into(holder.imageView);
        return convertView;
    }
}
