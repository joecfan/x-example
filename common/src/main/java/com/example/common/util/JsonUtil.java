package com.example.common.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 18:15 2020/9/8
 **/
public class JsonUtil {

    /**
     * ArryayList格式的队列转为json String
     *
     * @param list
     * @return
     */
    public String list2Json(List list) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = null;
        try {
            jsonstr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
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
    public <T> List<T> json2List(String jsonStr, T t) {

        List<T> list = null;
        if (!StringUtils.isEmpty(jsonStr)) {
            list = (List<T>) JSONObject.parseArray(jsonStr, t.getClass());
        }
        return list;
    }

}
