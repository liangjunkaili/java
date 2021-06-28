package study20200904;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("1",2);
        map.put("2",1);
        List<Integer> list =
                map.entrySet().stream().map(
                        (entry) -> entry.getValue()).sorted().collect(Collectors.toList());
        for (Integer i:list){
            System.out.println(i);
        }
    }
}
