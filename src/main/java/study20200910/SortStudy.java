package study20200910;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * 桶排序
 * 计数排序
 * 基数排序
 * @see DualPivotQuicksort
 * @see ArraysParallelSortHelpers
 * @see TimSort
 * @see ComparableTimSort
 */
public class SortStudy {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,2,5,7,8,9,6};
        SortStudy sortStudy = new SortStudy();
        sortStudy.countSort(a,a.length);
        Arrays.stream(a).forEach(out::println);
    }
    public void bucketSort(){

    }
    public void countSort(int[] a,int n){
        //1、先求数组中的最大值
        int max = a[0];
        for (int i=1;i<n;i++){
            if (a[i]>max){
                max = a[i];
            }
        }
        //2、求区间中每个值的数量
        int[] b = new int[max+1];
        for (int i=0;i<n;i++){
            b[a[i]]++;
        }
        //3、将每个值的数量进行叠加
        for (int i=1;i<=max;i++){
            b[i] = b[i-1]+b[i];
        }
        //4、进行排序
        int[] temp = new int[n];
        for (int i=n-1;i>=0;i--){
            int index = b[a[i]]-1;
            temp[index] = a[i];
            b[a[i]]--;
        }
        //5、copy数组
        for (int i=0;i<n;i++){
            a[i] = temp[i];
        }
    }
    public void cardinalNumberSort(){

    }
}
