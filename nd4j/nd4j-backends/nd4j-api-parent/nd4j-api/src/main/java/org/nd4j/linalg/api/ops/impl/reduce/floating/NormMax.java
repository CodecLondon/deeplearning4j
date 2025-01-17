/*
 *  ******************************************************************************
 *  *
 *  *
 *  * This program and the accompanying materials are made available under the
 *  * terms of the Apache License, Version 2.0 which is available at
 *  * https://www.apache.org/licenses/LICENSE-2.0.
 *  *
 *  *  See the NOTICE file distributed with this work for additional
 *  *  information regarding copyright ownership.
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations
 *  * under the License.
 *  *
 *  * SPDX-License-Identifier: Apache-2.0
 *  *****************************************************************************
 */

package org.nd4j.linalg.api.ops.impl.reduce.floating;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.imports.NoOpNameFoundException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseReduceFloatOp;
import org.nd4j.linalg.api.ops.impl.reduce.bp.NormMaxBp;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.List;

/**
 * The max absolute value
 *
 * @author Adam Gibson
 */
public class NormMax extends BaseReduceFloatOp {
    public NormMax(SameDiff sameDiff, SDVariable i_v, boolean keepDims, int[] dimensions) {
        super(sameDiff, i_v, dimensions, keepDims);
    }

    public NormMax(SameDiff sameDiff, SDVariable i_v, SDVariable i_v2, int[] dimensions) {
        super(sameDiff, i_v, i_v2, dimensions);
    }

    public NormMax(SameDiff sameDiff, SDVariable input, int[] dimensions, boolean keepDims) {
        super(sameDiff, input, dimensions, keepDims);
    }

    public NormMax(SameDiff sameDiff, SDVariable input, int... dimensions) {
        super(sameDiff, input, dimensions);
    }

    public NormMax(SameDiff sameDiff, SDVariable i_v, boolean keepDims, SDVariable dimensions) {
        super(sameDiff, i_v, keepDims, dimensions);
    }

    public NormMax(SameDiff sameDiff, SDVariable i_v, SDVariable i_v2, SDVariable dimensions) {
        super(sameDiff, i_v, i_v2, dimensions);
    }

    public NormMax(SameDiff sameDiff, SDVariable input, SDVariable dimensions, boolean keepDims) {
        super(sameDiff, input, dimensions, keepDims);
    }

    public NormMax(SameDiff sameDiff, SDVariable input, SDVariable dimensions) {
        super(sameDiff, input, dimensions);
    }

    public NormMax(INDArray input, INDArray output, boolean keepDims, int... dimensions) {
        super(input, output, keepDims, dimensions);
    }

    public NormMax(INDArray x, INDArray y, INDArray z, int... dimensions) {
        super(x, y, z, dimensions);
    }

    public NormMax() {
    }

    public NormMax(INDArray x, INDArray z, int... dimensions) {
        super(x, null, z, dimensions);
    }

    public NormMax(INDArray x, int... dimensions) {
        super(x, dimensions);
    }

    public NormMax(INDArray x, boolean keepDims, int... dimensions) {
        super(x, keepDims, dimensions);
    }

    public NormMax(INDArray in, INDArray indArray, boolean keepDims) {
        super(in,indArray,keepDims);
    }

    public NormMax(INDArray x, INDArray y, INDArray z, boolean keepDims, int... dimensions) {
        super(x, y, z, keepDims, dimensions);
    }

    public NormMax(INDArray in, int[] dimensions, boolean keepDims) {
        super(in,keepDims,dimensions);
    }

    @Override
    public INDArray noOp() {
        return Transforms.abs(x());
    }


    @Override
    public int opNum() {
        return 4;
    }

    @Override
    public String opName() {
        return "reduce_normmax";
    }


    @Override
    public List<SDVariable> doDiff(List<SDVariable> grad) {
        //maxnorm(in) = max_i |x_i|
        //d maxnorm(in)/dx = 0 if x_i is not the max, or d|x|/dx otherwise
        return new NormMaxBp(sameDiff, arg(), grad.get(0), keepDims, dimensions).outputs();
    }

    @Override
    public String onnxName(){
        throw new NoOpNameFoundException("No onnx op opName found for " +  opName());
    }

    @Override
    public String tensorflowName(){
        throw new NoOpNameFoundException("No tensorflow op opName found for " +  opName());
    }
}
