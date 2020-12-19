package com.example.sharding.algorithm;

import com.example.sharding.util.Context;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 9:37 2020/1/14
 * @Desc 分表算法
 **/
public class TbAlgorithm implements ComplexKeysShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<String> complexKeysShardingValue) {

        List<String> shardingResults = new ArrayList<>();

        /*LocalDateTime localDateTime = LocalDateTime.now();
        int minute = localDateTime.getMinute();
        int tbId = minute % 2;*/
        String tbId = Context.tableId.get();
        tbId = complexKeysShardingValue.getLogicTableName()+"_" + tbId;

        shardingResults.add(tbId);

        return shardingResults;
    }
}
