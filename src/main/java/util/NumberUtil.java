package util;

import java.math.BigDecimal;

public class NumberUtil {
    public static void main(String[] args) {
//        mul(854,1.1);
//        System.out.println(854564/1.1);
//        System.out.println(854564*1.1);
//        int need = 50;
//        double d = 50/100.0;
//        BigDecimal a = new BigDecimal(d);
//        BigDecimal b = new BigDecimal(need);
//        need = a.multiply(b).intValue();
//        System.out.println(need+"=="+d);
        int 年龄 = 10;
        int 十年后的年龄 = 年龄+10;
        System.out.println(十年后的年龄);
    }
    public static void mul(long l,double d){
        BigDecimal a = new BigDecimal(l);
        BigDecimal b = new BigDecimal(d);
        long l0 = a.multiply(b).longValue();
        System.out.println(l0);
    }
}
