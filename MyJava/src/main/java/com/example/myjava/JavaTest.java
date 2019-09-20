package com.example.myjava;

import java.beans.IntrospectionException;
import java.util.*;

import javax.xml.transform.Result;

public class JavaTest {
    public static void main(String []args){

//        new a();
        A aliment = new A();
        System.out.println(++A.a);
//        System.out.println("sss");

    }
}

class A {
        static int a;

    public A() {

        }

}

