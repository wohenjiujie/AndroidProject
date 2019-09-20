package com.example.study.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.study.R;
import com.example.study.util.MyInterface;

import java.util.List;


/**
 * Created by INvo on 2019-06-02.
 */
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {
    /*
     * 问题：
     * 1.rect分割线会重复，则造成分割线重叠
     * { 1.remove方法改写
     *   2.传rect复用
     *   3.监听事件中setEmpty
     * }
     * */
    private Context mContext;
    private List<String> mList;
//    private onItemClickListener mListener;
    private int mType;
//    private MyInterface myInterface = new MyInterface();
    private MyInterface.myRecyclerViewClickListener myClickListener;

    public LinearAdapter(Context context,@Nullable List<String> list,@Nullable MyInterface.myRecyclerViewClickListener listener) {
        /*
        * 新建一个暂时可空的list
        * */
        this.mContext = context;
        this.mList = list;
        this.myClickListener=listener;
    }

    public void setType(int type) {
        this.mType = type;

    }

    public int getType() {
        return mType;
    }

    /*
     * RecyclerView.Adapter api指代范型（Adapter<VH extends RecyclerView.ViewHolder>），所以构造一个
     * LinearViewHolder继承RecyclerView.ViewHolder
     * 所以返回值为LinearAdapter.LinearViewHolder
     * */
    @NonNull
    @Override

    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;  //改写
        /*
        * 该方法return了一个ViewHolder，ViewHolder返回LinearViewHolder
        * LinearViewHolder中包含了一个布局
        * int i 相当于int viewType
        * 可用getItemViewType控制展示不同item的内容
        * */
        if (mType == 1) {
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_linear_item_ver,viewGroup,false));
        } else
            return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_linear_item_hor,viewGroup,false));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder viewHolder, final int i) {
        /*
        * 所有的继承都可改写，此处修饰int i为final
        * 有时间可以改写跳转
        * */
        viewHolder.mTv.setText("diy...");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return 100;//先定好以后再改写
    }


    class LinearViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;
//        private Switch mSwitch;

        public LinearViewHolder(@NonNull final View itemView) {
            super(itemView);
            if (mType == 1) {
                mTv = itemView.findViewById(R.id.rv_linear_item_ver);

            }else
                mTv = itemView.findViewById(R.id.rv_linear_item_hor);
        }
    }
}

//class removeMyDeroration

class MyDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int mType;
    private Switch mChange;
    private Rect mRect,rectVertical,rectHorizontal;


    public MyDecoration(Context context) {
        this.mContext = context;
//        this.mType = type;
//        this.mChange = change;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getType() {
        return mType;
    }

    public void setRect(Rect rect) {
        this.mRect = rect;
    }


    public void initRect() {
        rectVertical.set(0, 0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration));
        rectHorizontal.set(0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration), 0);

    }
    public void resetMyDecoration() {
        this.mRect.setEmpty();
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        /*
        * 思路：
        * 在LinearRecyclerViewActivity中只new一个MyDecoration
        * 注意只能new一次，要保证是在同一个内存地址中
        * 然后二者相互返回各自的rect
        * 读取并重置
        * 所以在声明方法中要进行更改
        * 额外写一个type
        * */
//        rectVertical.set(0, 0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration));
//        rectHorizontal.set(0,0,mContext.getResources().getDimensionPixelSize(R.dimen.decoration),0);

        super.getItemOffsets(outRect, view, parent, state);
        if (mType == 1) {
            /*outRect.set(0, 0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration));
            mChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        outRect.setEmpty();
                    }
                }
            });*/
//            outRect.setEmpty();
//            outRect.set(rectVertical);
//            mRect.setEmpty();
            outRect.set(0, 0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration));
//            this.mRect=outRect;
//            mRect.set(0, 0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration));

//            setRect(outRect);
//            mRect.setEmpty();
//            setRect(outRect);
//            setRect(outRect);
//            outRect = rectVertical;
        } else if (mType == 2) {

            /*outRect.set(0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration), 0);
            mChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        outRect.setEmpty();
                    }
                }
            });*/
//            outRect = getRect();
//            outRect.setEmpty();
//            outRect.setEmpty();
//            mRect.set(0,0,0,0);
//            mRect.setEmpty();
//            mRect.set(0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration), 0);
            outRect.set(0, 0, mContext.getResources().getDimensionPixelSize(R.dimen.decoration), 0);
//            this.mRect = outRect;
//            outRect = rectHorizontal;
//            setRect(outRect);
//            mRect.setEmpty();
        }
//        super.getItemOffsets(outRect, view, parent, state);

    }


}

/*class RemoveMyDecoration extends RecyclerView {
    private Context mContext;

    public RemoveMyDecoration(@NonNull Context context) {
        super(context);
//        this.mContext = context;
    }

    @Override
    public void removeItemDecoration(@NonNull ItemDecoration decor) {
        super.removeItemDecoration(decor);
    }
}*/

/*interface onItemClickListener {
    *//*
    * 在内部写一个接口方便在外方法构造
    * 参数可在需要的时候改写
    * *//*
    void onClick(int pos);
}*/






