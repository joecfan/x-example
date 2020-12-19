package com.example.sharding.service;

import com.example.sharding.entity.TbTest;

import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 11:09 2020/1/14
 **/
public interface ShardingService {

    List<TbTest> queryList(String shardingId);

    int inser(TbTest sharding);
}
