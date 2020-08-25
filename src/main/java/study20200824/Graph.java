package study20200824;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 图
 */
public class Graph {

    public static void main(String[] args) {
        Map<String,String[]> map = new HashMap<>();
        map.put("liangjun",new String[]{"liangwei","likaili"});
        map.put("liangwei",new String[]{"zhaojing"});
        map.put("likaili",new String[]{"likaiming","lifuchun"});
        //队列的入队和出队
        Deque<String> deque = new ArrayDeque<>();
        for (String key :map.keySet()){
            if (!deque.contains(key)){
                deque.add(key);
            }
            String[] values = map.get(key);
            for (int i = 0; i < values.length; i++) {
                if (!deque.contains(values[i])) {
                    deque.add(values[i]);
                }
            }
        }
        deque.stream()
                .filter(name -> name.equals("likaili"))
                .forEach((name) -> {
                    System.out.println(name);
                    System.out.println("success");
                });
    }
}
