package com.example.template.common.config.datasource.sharding.range;

import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p> 自定义分表规则类 </p>
 *
 * @author zhouhuihui
 * @description 范围分片算法
 * @date 2022/08/29
 */
@Slf4j
public class MyTableRangeShardingAlgorithm implements RangeShardingAlgorithm<Byte> {

    @Override
    public Collection<String> doSharding(Collection<String> tableNameList, RangeShardingValue<Byte> shardingValue) {
        log.info("[MyTableRangeShardingAlgorithm] shardingValue: [{}]", shardingValue);
        Set<String> tableNameResultList = new LinkedHashSet<>();
        Range<Byte> rangeValue = shardingValue.getValueRange();
        Byte lower = rangeValue.lowerEndpoint();
        Byte upper = rangeValue.upperEndpoint();
        // between 0 and 1
        // 根据性别值选择表
        for (String tableNameItem : tableNameList) {
            if (tableNameItem.endsWith(String.valueOf(lower))
                    || tableNameItem.endsWith(String.valueOf(upper))) {
                tableNameResultList.add(tableNameItem);
            }
        }
        return tableNameResultList;
    }

}
