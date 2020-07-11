package com.example.sharding.service.impl;

import com.baw.sharding.dao.TbTestMapper;
import com.baw.sharding.entity.TbTest;
import com.baw.sharding.entity.TbTestExample;
import com.baw.sharding.service.ShardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 11:10 2020/1/14
 **/
@Service
public class ShardingServiceImpl implements ShardingService {
    @Autowired
    private TbTestMapper shardingMapper;

    @Override
    public List<TbTest> queryList(String shardingId) {

        TbTestExample shardingExample = new TbTestExample();
        shardingExample.createCriteria().andShardingIdEqualTo(shardingId);
        List<TbTest> shardingList = shardingMapper.selectByExample(shardingExample);
        return shardingList;
    }

    @Override
    public int inser(TbTest sharding) {

        int inserts = shardingMapper.insert(sharding);
        return inserts;
    }
}
