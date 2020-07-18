package com.example.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author: zhuhui bao
 * @date: 9:01 2020/6/19
 **/
@Slf4j
public class Utils {

    public static void main(String[] args) {
        String s = subString(null, 0, 8);
        System.out.println(s);
    }

    public static String subString(String str, int beginIdx, int endIdx) {

        try {
            str = str == null ? "" : str;
            str = str.substring(beginIdx, endIdx);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return str;
    }



    /*public static void setObjField(Object vo, String fieldName, String fieldValue) {

        Field field;
        try {
            Field[] fields = vo.getClass().getDeclaredFields();
            field = vo.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(vo, fieldValue);
            Object o = field.get(vo);
            System.out.println("3333");
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
    }*/

    public static void setObjField(Object vo, String fieldName, Object fieldValue) {

        Field field;
        try {
            Field[] fields = vo.getClass().getDeclaredFields();
            field = vo.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(vo, fieldValue);
            Object o = field.get(vo);
            System.out.println("3333");
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
    }

    public boolean isEmpty(Object object) {

        boolean isEmpty = false;

        if (object == null) {
            isEmpty = true;

        } else {
            if (object instanceof List) {
                if (((List)object).isEmpty()) {
                    isEmpty = true;
                }
            } else if (StringUtils.isEmpty(String.valueOf(object))){
                isEmpty = true;
            }
        }
        return isEmpty;
    }
}
