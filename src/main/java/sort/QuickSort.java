package sort;

import java.util.Arrays;

/**
 * 每次都找数组的最后一个元素为分区点
 * 进行分区
 * 递归
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1,21,3,34,15,76,7,10,9,30};
        quickSort_v1(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(System.out::println);
    }
    public static void quickSort_v1(int[] arr,int low,int high){
        if (low>=high)return;
//        int p = partition(arr,low,high);
        int p = partition_v2(arr,low,high);
        quickSort_v1(arr, low, p-1);
        quickSort_v1(arr, p+1, high);
    }
    private static int partition(int[] arr,int low,int high){
        int point = arr[high];
        int begin = low;
        for(int i=low;i<=high-1;i++){
            if (arr[i]<point){
                int temp = arr[i];
                arr[i] = arr[begin];
                arr[begin] = temp;
                begin++;
            }
        }
        arr[high] = arr[begin];
        arr[begin] = point;
        return begin;
    }
    private static int partition_v2(int[] arr,int low,int high){
        int point = arr[low];
        int end = high;
        for (int i=high;i>0;i--){
            if (arr[i]>point){
                int temp = arr[i];
                arr[i] = arr[end];
                arr[end] = temp;
                end--;
            }
        }
        arr[low] = arr[end];
        arr[end] = point;
        return end;
    }
}
