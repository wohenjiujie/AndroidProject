import org.graalvm.compiler.phases.common.RemoveValueProxyPhase;

import java.rmi.Remote;
import java.util.Scanner;

public class Contoff {
    /*
    * 两个问题
    * 1.循环是圆周型的，到达最后一位最后回到初始进行再循环——如何再循环
    * 2.如果进行二循环，要注意被赋值为0的数组无法加入循环——如何排除
    */
    public static void main(String[] args){
        System.out.println("n:");
        int n=new Scanner(System.in).nextInt();
        System.out.println("k:");
        int k=new Scanner(System.in).nextInt();
        int[] x=new int[n-1];
        int count=1;
        int per=0;
        boolean[] y=new boolean[n-1];
        for(int i=0;i<n;i++){
            y[i]=true;
        }
        x[0]=count;
        /*if(x[0]%k==0||x[0]%10==k){
            x[0]=0;
        }*/
        for(int i=0;i<n;i++){
            if(per==n-1){
                for(i=0;i<n;i++){
                    if(x[i]!=0){
                        System.out.println(i+1);
                        break;
                    }
                }
            }
            if(x[i]==0||x[i]%k==0||x[i]%10==k){
//               x[i]=0;
                y[i]=false;
                per++;
               /* if(i==n-1){
                    i=0;
                }
                continue;*/
            }
            if(y[i+1]==false){
                continue;
            }
            count++;
            x[i+1]=count;
            if(i==n-1){
                i=0;
            }
        }
    }
}
