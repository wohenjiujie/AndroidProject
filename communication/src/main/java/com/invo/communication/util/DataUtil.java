package com.invo.communication.util;

import java.util.Date;

/**
 * Created by INvo on 2019-07-31.
 */
public class DataUtil {
    public static String nowTime(){
        Date date=new Date();
        String time= String.format("%tT",date);
        return time;
    }
}
