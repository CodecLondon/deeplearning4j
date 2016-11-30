package org.datavec.spark.transform.transform;

import org.datavec.api.transform.sequence.SequenceSplit;
import org.datavec.api.writable.Writable;
import org.datavec.spark.functions.FlatMapFunctionAdapter;

import java.util.List;

/**
 * Created by Alex on 17/03/2016.
 */
public class SequenceSplitFunctionAdapter implements FlatMapFunctionAdapter<List<List<Writable>>,List<List<Writable>>> {

    private final SequenceSplit split;
    public SequenceSplitFunctionAdapter(SequenceSplit split) {
        this.split = split;
    }

    @Override
    public Iterable<List<List<Writable>>> call(List<List<Writable>> collections) throws Exception {
        return split.split(collections);
    }
}
