package com.example.common.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 18:15 2020/9/8
 **/
public class JsonUtil {

    public static void main(String[] args) {
        Vo vo = new Vo();
        vo.setAa("a");
        vo.setCc("c");

        Vo2 vo2 = new Vo2();
        vo2.setAa("a");
        BeanUtils.copyProperties(vo, vo2);


        System.out.println(123);
    }

    /**
     * ArryayList格式的队列转为json String
     *
     * @param list
     * @return
     */
    public static String list2Json(List list) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = null;
        try {
            mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                @Override
                public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
                    arg1.writeString("");
                }
            });
            jsonstr = mapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonstr;
    }

    /**
     * ArraList json转化为 Arraylist 对象
     *
     * @param jsonStr
     * @param t
     * @return
     */
    public static <T> List<T> json2List(String jsonStr, T t) {

        List<T> list = null;
        if (!StringUtils.isEmpty(jsonStr)) {
            list = (List<T>) JSONObject.parseArray(jsonStr, t.getClass());
        }
        return list;
    }

}
