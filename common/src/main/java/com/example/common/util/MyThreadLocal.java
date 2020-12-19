package com.example.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhuhui bao
 * @date: 16:42 2020/11/29
 **/
public class MyThreadLocal {

    private static final ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>(8);
        }
    };

    public static void put(String key, Object obj) {
        context.get().put(key, obj);
    }

    public static Object get(String key) {
        return context.get().get(key);
    }

    public static void remove() {
        context.remove();
    }
}
