package com.example.mock;

import com.example.mock.service.MockService;
import com.example.mock.service.impl.StaticService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//使用这个跑单测会加载整个容器
//@RunWith(SpringJUnit4ClassRunner.class)
//使用这个跑单测，所有的服务都需要mock，不会加载容器支持静态方法mock
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//在这个位置指定需要被mock的静态资源类   @1
@PrepareForTest({ StaticService.class })
@SpringBootTest
public class MockTestBak {

    @Autowired
    private MockService mockService;

    @Before
    public void before() {
        //静态方法mock @2

    }

    @Test
    public void search() {
        PowerMockito.mockStatic(StaticService.class);
        PowerMockito.when(StaticService.test()).thenReturn("mock!!!");

        //System.out.println(StaticService.test());
        System.out.println(mockService.testMock());
    }

}

