package com.example.common;

import com.example.common.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.example.common.util.Utils.getContent;

/**
 * @author: zhuhui bao
 * @date: 17:57 2020/8/25
 **/
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilTests {

    @Test
    public void eval2Test001() {
        boolean b = Utils.eval2("1==1 and (1 == 2 or 1==1)");

        Assert.isTrue(b, "true");
    }

    @Test
    public void eval2Test002() {
        boolean b = Utils.eval2("!(1 between {1,2})");

        Assert.isTrue(!b, "true");
    }

    @Test
    public void eval2Test003() {
        Map<String, Integer> varMap = new HashMap<>();
        varMap.put("value1", 1);
        varMap.put("value2", 2);
        boolean b = Utils.eval2("#value1between {1,2} and #value2 == 2", varMap);

        Assert.isTrue(b, "不是逾期返回值");
    }

    @Test
    public void eval3Test003() {
        Map<String, Integer> varMap = new HashMap<>();
        varMap.put("value", 3);
        boolean b = Utils.eval3("#value between {1,2}", varMap);

        Assert.isTrue(b, "不是逾期返回值");
    }

    @Test
    public void getContentTest001() {
        String tempalte = "{name}你好,今年{age}岁！";
        Map<String,String> parameters = new HashMap<String, String>();
        parameters.put("name", "chris");
        parameters.put("age", "22");
        System.out.println(getContent(tempalte, parameters));
    }

    @Test
    public void getContentTest002() {
        String tempalte = "#name1 你好,今年#a2ge 岁！";

        Utils.getExpressionVar(tempalte);
    }

}
