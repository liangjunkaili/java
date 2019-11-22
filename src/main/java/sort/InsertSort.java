package sort;

/**
 * @ClassName InsertSort
 * @Description TODO
 * @Author junliang
 * @Date 2019/11/19 6:28 PM
 * @Version 1.0
 **/
public class InsertSort {
    public static void sort(int[] a,int n){
        if (n<=1){
            return;
        }
        for (int i=1;i<n;i++){
            int v = a[i];
            int j = i-1;
            for (;j>=0;j--){
                if (a[j]>v){
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            a[j+1] = v;
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
