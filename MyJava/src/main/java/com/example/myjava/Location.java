package com.example.myjava;

public class Location {
    static private int x=1;
    public static void add(int y,int z) {
        x+=(y+z);
    }
    public static int get() {
        return x;
    }

    public static void pro(int y,int z){
        x+=(y+z);
    }
}
