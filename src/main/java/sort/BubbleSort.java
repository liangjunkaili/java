package sort;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName BubbleSort
 * @Description TODO
 * @Author junliang
 * @Date 2019/11/19 5:22 PM
 * @Version 1.0
 * 冒泡排序
 **/
public class BubbleSort {
    private static AtomicInteger integer = new AtomicInteger(0);
    public static void sort(int[] arr,int n){
        if (n<=1){
            return;
        }
        for (int i=0;i<n;i++){
            boolean flag = false;
            for (int j=0;j<n-(i+1);j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
                System.out.println("--"+integer.incrementAndGet());
            }
            if (!flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {200,100,53,42,32,22,4};
        sort(arr,arr.length);
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
