package com.example.study.util;

/**
 * Created by INvo on 2019-06-09.
 */
public class MyInterface {

    public interface myRecyclerViewClickListener {
        /*
         * 在内部写一个接口方便在外方法构造
         * 参数可在需要的时候改写
         * */
        void onClick(int pos);
    }
}
