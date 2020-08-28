package study20200803;

import study20200802.QuickSort;

import java.util.*;

/**
 * 定义：具有N个元素的序列，且满足ni>=n2i+1,ni>=n2i+2或ni<=n2i+1,ni<=n2i+2，前者是大顶堆，后者是小顶堆
 * 注意这里的i是从0开始的
 * 是一颗完全二叉树，所以一半的节点是叶子节点，因为只需要要从中间节点开始调整就行
 * 时间复杂度：N*logN
 */
public class Heap {

    public static void main(String[] args) {
//        int[] a = new int[]{1,12,31,34,16,27,81,19,10};
//        buildHeap(a,a.length-1);
//        Arrays.stream(a).forEach(System.out::println);
        String[] a = new String[]{"5","1","2","3","4"};
        System.out.println(demo(a)+"");
    }
    private static void buildHeap(int[] a,int n){
        for (int i=n/2;i>=0;i--){
            heapify(a,n,i);
        }
    }

    //构建大顶堆，将小的元素都下沉了
    private static void heapify(int[] a, int n, int i){
        while (true){//保证下沉完毕
            int maxPos = i;
            if (i*2+1<=n && a[i]<a[i*2+1]){
                maxPos = i*2+1;
            }
            if (i*2+2<=n && a[maxPos]<a[i*2+2]){
                maxPos = i*2+2;
            }
            if (maxPos==i)
                break;
            swap(a,i,maxPos);
            i = maxPos;
        }
    }
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean demo(String[] s){
        Set<String> strings = new HashSet<>();
        for (int i=0;i<s.length;i++){
            strings.add(s[i]);
        }
        //有重复
        if (strings.size()<5)return false;
        List<String> list = new ArrayList<>();
        for (String s1:strings
             ) {
            list.add(s1);
        }
        Collections.sort(list);
        int sum = 0;
        int begin = 0;
        //不包含W、w
        for (int i=0;i<list.size();i++){
            begin = Integer.parseInt(list.get(0));
            sum += Integer.parseInt(list.get(i));
        }
        if (sum == begin*5+1+2+3+4){
            return true;
        }
        return false;
    }
}
