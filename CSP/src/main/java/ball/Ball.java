package ball;

import java.util.*;

public class Ball {
    public static void main(String[] args){
        int i,j,k,n,t,L;
        System.out.println("n:");
        n=new Scanner(System.in).nextInt();
        System.out.println("L:");
        L=new Scanner(System.in).nextInt();
        System.out.println("t:");
        t=new Scanner(System.in).nextInt();
        int[] a=new int[n];
        int[] step=new int[n];
        for(i=0;i<n;i++){
           a[i]=new Scanner(System.in).nextInt();
           step[i]=1;
        }
        for(i=0;i<t;i++){
            for(j=0;j<n;j++){
                a[j]+=step[j];
                if(a[j]==L||a[j]==0){
                    step[j]=-step[j];
                }
            }
            for(j=0;j<n;j++){
                for(k=j+1;k<n;k++){
                    if(a[k]==a[j]){
                        step[k]=-step[k];
                        step[j]=-step[j];
                    }
                }
            }
        }
        for(i=0;i<n;i++){
            System.out.print(a[i]+"\t");
        }
    }
}
