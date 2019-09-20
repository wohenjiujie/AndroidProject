package com.example.jump;
import java.util.*;

public class Jump {
        public static void main(String[] args){
            System.out.println("input:\n");
            int sum=0;
            int i=0;
            int x;
            while(true){
                x=new Scanner(System.in).nextInt();
                if(x==0){
                    System.out.print("sum="+sum+"\ngame over");
                    System.exit(0);
                }
                else {
                    sum+=x;
                    if(x==1){
                        i=0;
                    }
                    else if(x==2){
                        sum+=i;
                        i+=2;
                    }
                    System.out.print("sum="+sum+"\n");
                }
            }
        }
    }

