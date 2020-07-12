package com.example.mock.service.impl;

/**
 * @author: zhuhui bao
 * @date: 21:38 2020/7/11
 **/
public class StaticService {

    private static StaticService ins = new StaticService();

    private StaticService () {

    }

    public static StaticService get() {
        return ins;
    }

    public static String test() {
        return "test";
    }

    public String test2() {
        return "test";
    }
}
