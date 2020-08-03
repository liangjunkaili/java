package study20200802;

import java.util.Arrays;

/**
 *
 * 练习快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = new int[]{1,12,31,34,16,27,81,19,10};
        System.out.println(maxK(a,0,a.length-1,3));
        sort(a,0,a.length-1);
        Arrays.stream(a).forEach(System.out::println);
    }
    public static int maxK(int[] a,int low,int high,int k){
        if (low>=high)
            return-1;
//        int point = a[high];
//        int begin = low;
//        for (int i=low;i<high;i++){
//            if (a[i]>point){
//                int temp = a[i];
//                a[i] = a[begin];
//                a[begin] = temp;
//                begin++;
//            }
//        }
//        a[high] = a[begin];
//        a[begin] = point;
        int begin = partitiona_v2(a, low, high);
        if (begin==k-1){
            return a[begin];
        }else if (begin>k-1){
            return maxK(a,low,begin-1,k);
        }else {
            return maxK(a,begin+1,high,k);
        }
    }
    public static void sort(int[] a,int low,int high){
        if (low>=high)return;
//        int p = partitiona_v2(a,low,high);
        int point = a[high];
        int begin = low;
        for (int i=low;i<high;i++){
            if (a[i]>point){
                int temp = a[i];
                a[i] = a[begin];
                a[begin] = temp;
                begin++;
            }
        }
        a[high] = a[begin];
        a[begin] = point;
        sort(a,low,begin-1);
        sort(a,begin+1,high);
    }

    private static int partition(int[] a, int low, int high) {
        int point = a[high];
        int begin = low;
        for (int i=low;i<high;i++){
            if (a[i]<point){
                int temp = a[i];
                a[i] = a[begin];
                a[begin] = temp;
                begin++;
            }
        }
        a[high] = a[begin];
        a[begin] = point;
        return begin;
    }
    private static int partitiona_v2(int[] a, int low, int high) {
        int point = a[high];
        int begin = low;
        for (int i=low;i<high;i++){
            if (a[i]>point){
                int temp = a[i];
                a[i] = a[begin];
                a[begin] = temp;
                begin++;
            }
        }
        a[high] = a[begin];
        a[begin] = point;
        return begin;
    }
}
