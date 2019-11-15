package util;

import java.math.BigDecimal;

public class NumberUtil {
    public static void main(String[] args) {
        mul(854,1.1);
        System.out.println(854564/1.1);
        System.out.println(854564*1.1);
    }
    public static void mul(long l,double d){
        BigDecimal a = new BigDecimal(l);
        BigDecimal b = new BigDecimal(d);
        long l0 = a.multiply(b).longValue();
        System.out.println(l0);
    }
}
