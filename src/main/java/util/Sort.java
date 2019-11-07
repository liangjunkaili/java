package util;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Sort {
private static AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * 每比较一次，最大的就沉底了
     * 循环次数(比较次数)：n-1 n-2 ...... 2 1 的和（n-1）/2 * n
     * 交换次数：最好为0，最差为循环次数
     * 如何判断数组或者一个集合是否是有序的？
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        int length = array.length;
        if (length == 0 || length == 1)
           return array;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length - 1 - i; j++){
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                atomicInteger.incrementAndGet();
                }
            }
        }
        System.out.println(atomicInteger.get());
        return array;
    }

    public static void main(String[] args) {
        int[] array = {11,2,13,44,52,56,77,80,9,10};
//        int[] array = {10,9,8,7,6,5,4,3,2,1};
//        int[] array = {1,2,3,4,5,6,7,8,9,10};
        long start = System.currentTimeMillis();
        array = bubbleSort(array);
        System.out.println("cost "+(System.currentTimeMillis() - start)+"ms");
        //下面这条语句为什么会如此耗时？
//        Arrays.stream(array).forEach(i -> System.out.print(i));
        for (int i :array){
            System.out.println(i);
        }
        System.out.println("cost "+(System.currentTimeMillis() - start)+"ms");
    }
}
