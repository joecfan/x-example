package com.example.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author: zhuhui bao
 * @date: 13:43 2020/1/15
 **/
public class Tools {

    public static void main(String[] args) {

        BigDecimal bg = new BigDecimal("12.123");
        bg = bg.setScale(1, RoundingMode.DOWN);

        int i = 1;
        test(i);
    }

    public static void test(int i) {

        if (i == 1) {
            throw new ClassCastException("123");
        } else if (i == 2) {
            throw new ArithmeticException("456");
        }
    }


}
