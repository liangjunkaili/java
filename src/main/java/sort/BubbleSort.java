package sort;

import java.util.Arrays;
import java.util.Collections;
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
        if (n<=1) return;
        for (int i=0;i<n;i++){
            boolean flag = false;
            int k = n - (i+1);
            for (int j=0;j<k;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    public static void insertSort(int[] a){
        for (int i = 1; i < a.length; ++i) {//从第二个开始挨个取数
            int value = a[i];
            int j = i - 1;//计算已排序区间的最大索引
            for (; j >= 0; --j) {//从后往前遍历已排序区间
                if (a[j] > value) {//如果当前插入的元素小于当前元素，将当前元素后移一位
                    a[j+1] = a[j];
                } else {//如果当前插入的元素大于当前元素，跳出循环
                    break;
                }
            }
            a[j+1] = value;//在当前元素的后面插入
        }
    }

    public static void selectSort(int[] a){
        int length = a.length;
        for (int i=0;i<length;i++){
            int min = a[i];
            int j=i+1;
            int k = i;
            for (;j<length;j++){
                if (a[j]<min){
                    min = a[j];
                    k = j;
                }
            }
            a[k] = a[i];
            a[i] = min;
        }
    }
    public static void quickSort(int[] a,int begin,int end){
        if (begin>=end) return;//终止条件
        int q = partition(a, begin, end);//每次循环进行分区，元素交换
        quickSort(a, begin, q-1);
        quickSort(a, q+1,end );
    }
    private static int partition(int[] a,int begin,int end){
        int pivot = a[end];//取最后一个元素最为分区点
        int i = begin;
        for (int j=begin;j<end;j++){
            if (a[j]<pivot){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }
        int temp = a[i];
        a[i] = a[end];
        a[end] = temp;
        return i;
    }

    public static void mergeSort(int[] a,int begin,int end){
        if (begin>=end)return;
        int q = (begin+end)/2;
        mergeSort(a,begin,q);
        mergeSort(a,q+1,end);
        merge(a,begin,q,end);
    }

    private static void merge(int[] a,int begin,int q,int end) {
        int[] temp = new int[a.length];
        int i=begin,j=q+1,k=0;
        while (i<q && j<end){
            if (a[i]<=a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }
        int start = i,stop=q;
        if (j<end){
            start = j;
            stop=end;
        }
        while (start<=stop){
            temp[k++] = a[start++];
        }
        for (i=0;i<end-begin;i++){
            a[begin+i] = temp[i];
        }
    }

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        Runtime r = Runtime.getRuntime();
        r.gc();
        long startMem = r.freeMemory(); // 开始时的剩余内存
        int[] arr = {200,53,42,22,32,4,100,40};
        quickSort(arr,0,arr.length-1);
        System.out.println(arr[arr.length-3]);
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println();
        long orz = startMem - r.freeMemory(); // 剩余内存 现在
        System.out.println(orz/1024/1024+"MB");
        System.out.println(System.currentTimeMillis()-beginTime+"ms");
    }
}
