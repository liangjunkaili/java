package dataStructure;

public class Stack {
    private String[] arr;
    private int count;
    private int size;
    public Stack(int size){
        arr = new String[size];
        this.size = size;
        this.count = 0;
    }
    public String pop(){
        if (count==0){
            return null;
        }
        String v = arr[count-1];
        count--;
        return v;
    }
    public void push(String value){
        if (size==count){
            //扩容
            size = size<<1;
            String[] newArr = new String[size];
            System.arraycopy(arr,0,newArr,0,arr.length);
            arr = newArr;
        }
        arr[count] = value;
        count++;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("d");
        stack.push("d");
        stack.push("d");
        stack.push("d");
        stack.push("d");
        stack.push("d");
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.size+"=="+stack.count);
    }
}
