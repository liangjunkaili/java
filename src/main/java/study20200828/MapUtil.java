package study20200828;

import java.util.concurrent.ConcurrentSkipListMap;

public class MapUtil {
    public static void main(String[] args) {
        ConcurrentSkipListMap<String,String> map = new ConcurrentSkipListMap();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");
        map.forEach((key,value) -> {
            System.out.println(key+"="+value);
        });
    }
}
