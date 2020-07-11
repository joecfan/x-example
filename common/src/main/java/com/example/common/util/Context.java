package com.example.common.util;

/**
 * @author: zhuhui bao
 * @date: 11:22 2020/1/14
 **/
public class Context {

    public static ThreadLocal<String> dbId = new ThreadLocal<>();

    public static ThreadLocal<String> tableId = new ThreadLocal<>();


}
