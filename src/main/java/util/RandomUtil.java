package util;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    public static void main(String[] args) {
        /*Random random = new Random();
        System.out.println(random.nextInt(100));
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println(threadLocalRandom.nextInt(100));
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            System.out.println(secureRandom.nextInt(100));
        } catch (NoSuchAlgorithmException e) {

        }*/
//        LocalDateTime localDateTime = LocalDateTime.now();
//        localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
//
//        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
//        long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        long m0 = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        long m20 = LocalDateTime.of(LocalDate.now(), LocalTime.of(20,0,0)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        System.out.println(m0-milliSecond);
//        System.out.println(m20-milliSecond);
//        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
//        System.out.println(now);
//        LocalDate now1 = LocalDate.parse("2019-10-23");
//        System.out.println(now1.toString());
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        try {
            Field f = ArrayList.class.getDeclaredField("elementData");
            Field f1 = AbstractList.class.getDeclaredField("modCount");
            f.setAccessible(true);
            f1.setAccessible(true);
            list.trimToSize();
            Object[] elementData = (Object[]) f.get(list);
            int modCount = (int) f1.get(list);
            System.out.println(elementData.length);
            System.out.println(modCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
