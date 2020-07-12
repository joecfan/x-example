package com.example.mock.service.impl;

import com.example.mock.service.MockService;
import org.springframework.stereotype.Service;

/**
 * @author: zhuhui bao
 * @date: 21:37 2020/7/11
 **/
@Service
public class MockServiceImpl implements MockService {
    @Override
    public String testMock() {
        return StaticService.test();
    }
}
