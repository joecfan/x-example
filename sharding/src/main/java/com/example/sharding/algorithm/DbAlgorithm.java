package com.example.sharding.algorithm;

import com.baw.util.Context;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: zhuhui bao
 * @date: 9:37 2020/1/14
 * @Desc 分库算法
 **/
public class DbAlgorithm implements ComplexKeysShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<String> complexKeysShardingValue) {
        List<String> shardingResults = new ArrayList<>();

        /*LocalDate localDate = LocalDate.now();
        int dayOfMonth = localDate.getDayOfMonth();
        int tbId = dayOfMonth % 2;*/
        String dbId = Context.dbId.get();
        dbId = "db-" + dbId;

        shardingResults.add(dbId);

        return shardingResults;
    }
}
