package com.example.common.util;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @author: zhuhui bao
 * @date: 13:43 2020/1/15
 **/
public class Tools {

//    public static void main(String[] args) {
//
//       // param("a","b");
//       // param("a","b","c");
//        String[] arr = {};
//        arr = ArrayUtils.remove(arr, 0);
//        arr = ArrayUtils.add(arr, "abc");
//        arr = ArrayUtils.add(arr, "def");
//        arr = ArrayUtils.add(arr, "123");
//        param(arr);
//
////        BigDecimal bg = new BigDecimal("12.123");
////        bg = bg.setScale(1, RoundingMode.DOWN);
////
////        int i = 1;
////        test(i);
//    }

    public static void param(String... params) {
        for (String s : params) {
            System.out.println(s);
        }
    }

    public static void test(int i) {

        if (i == 1) {
            throw new ClassCastException("123");
        } else if (i == 2) {
            throw new ArithmeticException("456");
        }
    }


}
