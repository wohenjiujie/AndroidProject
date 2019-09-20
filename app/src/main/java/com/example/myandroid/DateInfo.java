package com.example.myandroid;

import java.util.*;
public class DateInfo {
    static String nowtime(){
        Date date=new Date();
        String time= String.format("%tT",date);
        return time;
    }
}
