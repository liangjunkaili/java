package dataStructure;

import java.util.Arrays;

public class Array {
    static String[] arr = new String[5];
    private static int count = 0;
    public static void main(String[] args) {
        lru("a");
        lru("a");
        lru("a");
        lru("a");
        lru("a");
        lru("b");
        lru("c");
        lru("d");
        lru("a");
        lru("e");
        lru("b");
        lru("a");
        System.out.println(Arrays.toString(arr));
    }
    public static void lru(String value){
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (arr[i]==null){
                arr[i] = value;
                count++;
                return;
            }
            if (value.equals(arr[i])){
                System.arraycopy(arr,i+1,arr,i,count-(i+1));
                if (arr[i+1]==null){
                    arr[i] = value;
                }else {
                    arr[count-1] = value;
                }
                return;
            }
        }
    }
}
