/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.example.jdbc.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

public final class SingleKeyModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
    
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
       String currentPartValue = generatorEach(shardingValue.getValue());
    	for (String each : availableTargetNames) {
            if (each.endsWith(currentPartValue)) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Long> values = shardingValue.getValues();
        for (Long value : values) {
            for (String each : availableTargetNames) {
                if (each.endsWith(generatorEach(value))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
    
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(generatorEach(i))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
    
    private String generatorEach(Long keyValue){
    	String modeValue = keyValue % 256 + "";
    	if (modeValue.length() == 1) {
    		modeValue = "00"+modeValue;
		}else if (modeValue.length() == 2) {
    		modeValue = "0"+modeValue;
		}
    	return modeValue;
    }
}
