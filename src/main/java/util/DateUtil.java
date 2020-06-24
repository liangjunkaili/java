package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate  localDate = LocalDate.now();
        System.out.println(localDate.compareTo(LocalDate.parse("2019-11-27")));
    }
}
