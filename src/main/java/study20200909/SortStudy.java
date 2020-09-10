package study20200909;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * 冒泡排序
 * 插入排序
 * 选择排序
 */
public class SortStudy {

    public static void main(String[] args) {
        SortStudy sortStudy = new SortStudy();
        int[] a = new int[]{1,4,2,6,7,8,10};
//        sortStudy.bubbleSort(a,a.length);
//        sortStudy.insertSort(a,a.length);
        sortStudy.selectSort(a,a.length);
//        sortStudy.insertSort_v2(a,a.length);
        Arrays.stream(a).forEach(out::println);
    }
    public void bubbleSort(int[] a,int n){
        if (n<=1)
            return;
        for (int i=0;i<n;i++){
            boolean flag = false;
            for (int j=0;j<n-i-1;j++){
                if (a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }
    //分成一个已排序区间和一个未排序区间，刚开始已排序区间只有第一个元素
    public void insertSort(int[] a,int n){
        for (int i=1;i<n;i++){//未排序区间
            int value = a[i];
            int j = i-1;//已排序区间的大小
            for (;j>=0 && a[j]>value;j--){//找到要插入的元素的位置
                a[j+1] = a[j];
            }
            a[j+1] = value;
        }
    }
    public void insertSort_v2(int[] a,int n){
        for (int i=0; i<n; i++)
            for (int j=i; j>0 && a[j-1]>a[j]; j--) {
                int temp = a[j - 1];
                a[j-1] = a[j];
                a[j] = temp;
            }
    }
    public void selectSort(int[] a,int n){
        for (int i=0;i<n;i++){
            int min = a[i];
            int k = i;
            for (int j=i;j<n-i-1;j++){
                if (a[j]<min){
                    min = a[j];
                    k=j;
                }
            }
            int temp = a[i];
            a[i] = min;
            a[k] = temp;
        }
    }
}
