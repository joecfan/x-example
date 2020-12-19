package com.example.common;

import com.example.common.util.MyThreadLocal;
import com.example.common.util.Utils;
import com.example.common.util.Vo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * @author: zhuhui bao
 * @date: 16:46 2020/11/29
 **/
@RunWith(SpringJUnit4ClassRunner.class)

public class MyThreadLocalTest {

    @Test
    public void Test001() {
        Vo vo = new Vo();
        vo.setAa("a");
      //  MyThreadLocal.put("11", vo);

        Object o = MyThreadLocal.get("11");

        MyThreadLocal.remove();;

        o = MyThreadLocal.get("11");
        System.out.println(123);
    }

}
