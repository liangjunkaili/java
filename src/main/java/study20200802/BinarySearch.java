package study20200802;

/**
 * 练习二分查找
 * 首先明确几点：
 * 1、数组是有序的
 * 2、适合静态数据
 * 3、由于是数组，所以要求内存上是连续的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,7,7,7,7,8,9,10};
        System.out.println(search(a,7 ));
//        System.out.println(search(a,6));
//        System.out.println(search(a,10));
//        System.out.println(search_v2(a,1,0,a.length-1));
//        System.out.println(search_v2(a,6,0,a.length-1));
//        System.out.println(search_v2(a,10,0,a.length-1));
        System.out.println(search_v3(a,7));
        System.out.println(search_v6(a,7));
        System.out.println(search_v7(a,7));
        System.out.println(search_v8(a,7));
    }
    public static int search(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        while (low<=high){
            int mid = low+((high-low)>>1);//注意运算优先级
            if (key==a[mid]){
                return mid;
            }else if (key>a[mid]){
                low = mid + 1;
            }else if (key<a[mid]){
                high = mid - 1;
            }
        }
        return -1;
    }
    public static int search_v2(int[] a,int key,int low,int high){
        if (low>high)
            return -1;
        int mid = low+((high-low)>>1);
        if (a[mid]==key){
            return a[mid];
        }else if (a[mid]>key){
            return search_v2(a, key, low, mid-1);
        }else {
            return search_v2(a, key, mid+1, high);
        }
    }
    //查找最后一个小于等于给定值的元素
    public static int search_v3(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        while (low<=high){
            int mid = low+((high-low)>>1);//注意运算优先级
            if (key<a[mid]){
                high = mid - 1;
            }else {
                if ((mid==high)||a[mid+1]>key){
                    return mid;
                }else {
                    low = mid+1;
                }
            }
        }
        return -1;
    }
    //查找第一个大于等于给定值的元素
    public static int search_v6(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        while (low<=high){
            int mid = low+((high-low)>>1);//注意运算优先级
            if (key>a[mid]){
                low = mid + 1;
            }else if (key<=a[mid]){
                if (mid==low||a[mid+1]<key){
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }
    //查找最后一个值等于给定值的元素
    public static int search_v7(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        while (low<=high){
            int mid = low+((high-low)>>1);//注意运算优先级
            if (key<a[mid]){
                high = mid - 1;
            }else if (key>=a[mid]){
                if (key==a[mid]&&a[mid+1]!=key){
                    return mid;
                }else
                    low = mid + 1;
            }
        }
        return -1;
    }
    //查找第一个值等于给定值的元素
    public static int search_v8(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        while (low<=high){
            int mid = low+((high-low)>>1);//注意运算优先级
            if (key>a[mid]){
                low = mid + 1;
            }else if (key<=a[mid]){
                if (key==a[mid]&&a[mid-1]!=key){
                    return mid;
                }else
                    high = mid - 1;
            }
        }
        return -1;
    }
}
