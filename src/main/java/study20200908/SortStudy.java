package study20200908;

import java.util.Arrays;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * 归并排序
 *
 * 快速排序
 */
public class SortStudy {

    public static void main(String[] args) {
        SortStudy sortStudy = new SortStudy();
        int[] a = new int[]{1,3,9,2,67,4,18};
        sortStudy.mergeSort_(a,0,a.length-1);
//        sortStudy.quickSort(a,0,a.length-1);
        Arrays.stream(a).forEach(out::println);
    }

    private void merge_(int[] a, int begin, int m, int end) {
        int[] temp = new int[end-begin+1];
        int i=begin,j=m+1,k=0;
        while (i<=m && j<=end){
            if (a[i] < a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }
        //把剩下的也放入到temp中
        while (i<=m){
            temp[k++] = a[i++];
        }
        while (j<=end){
            temp[k++] = a[j++];
        }
        //将temp数据拷贝回a
        for (int h=0;h<temp.length;h++){
            a[h+begin] = temp[h];
        }
    }

    public void mergeSort_(int[] a,int begin,int end){
        if (begin>=end)
            return;
        int m = (begin+end)/2;
        mergeSort_(a,begin,m);
        mergeSort_(a,m+1,end);
        merge_(a,begin,m,end);
    }

    public void quickSort(int[] a,int begin,int end){
        if (begin>=end)
            return;
        int point = partion(a,begin,end);
        quickSort(a,begin,point-1);
        quickSort(a,point+1,end);
    }

    private int partion(int[] a, int begin, int end) {
        int point = a[end];//每次都取最后一个元素为分区点
        int i = begin;//记录下开始点
        for (int j=begin;j<=end-1;j++){
            if (a[j]<point){
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                i++;
            }
        }
        int temp = a[i];
        a[i] = point;
        a[end] = temp;
        return i;
    }
}
