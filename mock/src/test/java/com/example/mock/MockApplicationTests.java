package com.example.mock;

import com.example.mock.service.MockService;
import com.example.mock.service.impl.StaticService;
//import org.junit.Test;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


// Test注解不能引用这个Test
//import org.junit.jupiter.api.Test;
@PowerMockIgnore( {"javax.management.*", "javax.net.ssl.*"})
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest({ StaticService.class })
@SpringBootTest


public class MockApplicationTests {

	@Autowired
	private MockService mockService;

	/**
	 * mock静态方法
	 */
	@Test
	public void testStaticMethod() {

		PowerMockito.mockStatic(StaticService.class);
		PowerMockito.when(StaticService.test()).thenReturn("mock123!!!");

		System.out.println(mockService.testMock());
	}

	/**
	 * mock工厂类方法
	 * @throws Exception
	 */
	@Test
	public void testStaticMethod2() {

		StaticService staticService = PowerMockito.mock(StaticService.class);
		PowerMockito.mockStatic(StaticService.class);
		PowerMockito.when(StaticService.get()).thenReturn(staticService);
		PowerMockito.when(staticService.test2()).thenReturn("mock123456!!!");

		System.out.println(StaticService.get().test2());
        Assert.assertEquals("mock123456!!!", StaticService.get().test2());
	}

}
