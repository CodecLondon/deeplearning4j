/*
 *  * Copyright 2016 Skymind, Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 */

package org.datavec.spark.transform.join;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.datavec.api.transform.join.Join;
import org.datavec.api.writable.Writable;

import java.util.Iterator;
import java.util.List;

/**
 * Doing two things here:
 * (a) filter out any unnecessary values, and
 * (b) extract the List<Writable> values from the JoinedValue
 *
 */
public class FilterAndFlattenJoinedValues implements FlatMapFunction<JoinedValue,List<Writable>> {

    private final FilterAndFlattenJoinedValuesAdapter adapter;

    public FilterAndFlattenJoinedValues(Join.JoinType joinType) {
        this.adapter = new FilterAndFlattenJoinedValuesAdapter(joinType);
    }

    @Override
    public Iterator<List<Writable>> call(JoinedValue joinedValue) throws Exception {
        return adapter.call(joinedValue).iterator();
    }
}
