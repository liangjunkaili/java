package search;

/**
 * 前提！！！顺序表结构(数组)、
 * 有序数据集合（尽量是静态数据，不然需要一直排序）、
 * 数据量小的话可以选择遍历查找、
 * 太大的话对连续的内存要求太苛刻
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(binarySearch_v1(arr,1));
        System.out.println(binarySearch_v1(arr,10));
//        System.out.println(binarySearch_v2(arr,5,0,arr.length-1));
//        System.out.println(binarySearch_v2(arr,10,0,arr.length-1));
        System.out.println((10_000_000*8/1024/1024)+"MB");
    }
    public static int binarySearch_v1(int[] arr,int key){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){//一定要加=，不然两个边界值就找不到了
            int mid = low+((high-low)>>1);//防止low+high溢出，计算机对于位运算要快得多
            if (key>arr[mid]){
                low = mid+1;
            }else if (key<arr[mid]){
                high = mid-1;
            }else {
                return arr[mid];
            }
        }
        return -1;
    }
    public static int binarySearch_v2(int[] arr,int key,int low,int high){
        if (low>high){
            return -1;
        }
        int mid = low+((high-low)>>1);
        if (key==arr[mid]){
            return arr[mid];
        }else if (key>arr[mid]){
            return binarySearch_v2(arr,key,mid+1,high);
        }else if (key<arr[mid]){
            return binarySearch_v2(arr, key, low, mid-1);
        }
        return -1;
    }
}
