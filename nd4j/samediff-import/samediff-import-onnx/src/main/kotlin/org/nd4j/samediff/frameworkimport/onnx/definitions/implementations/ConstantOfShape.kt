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
package org.nd4j.samediff.frameworkimport.onnx.definitions.implementations

import onnx.Onnx
import org.nd4j.autodiff.samediff.SDVariable
import org.nd4j.autodiff.samediff.SameDiff
import org.nd4j.autodiff.samediff.internal.SameDiffOp
import org.nd4j.ir.OpNamespace
import org.nd4j.linalg.api.buffer.DataType
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.samediff.frameworkimport.hooks.PreImportHook
import org.nd4j.samediff.frameworkimport.hooks.annotations.HookResult
import org.nd4j.samediff.frameworkimport.hooks.annotations.PreHookRule
import org.nd4j.samediff.frameworkimport.onnx.ir.OnnxIRDataType
import org.nd4j.samediff.frameworkimport.onnx.ir.OnnxIRTensor

/**
 * A port of expand.py from onnx tensorflow for samediff:
 * https://github.com/onnx/onnx-tensorflow/blob/master/onnx_tf/handlers/backend/expand.py
 *
 * @author Adam Gibson
 */
@PreHookRule(nodeNames = [],opNames = ["ConstantOfShape"],frameworkName = "onnx")
class ConstantOfShape : PreImportHook  {
    override fun preProcess(
        op: SameDiffOp,
        sd: SameDiff,
        attributes: Map<String, Any>,
        descriptor: OpNamespace.OpDescriptor,
        outputNames: List<String>,
        isFinalOutput: Boolean
    ): HookResult {
        // Parameter docs below are from the onnx operator docs:
        // https://github.com/onnx/onnx/blob/master/docs/Operators.md#cast

        var inputShape = sd.getVariable(op.inputsToOp[0])
        val outputVarName: String? = if(isFinalOutput) {
            outputNames[0]
        } else null

        if(outputVarName != null && sd.hasVariable(outputVarName)) {
            sd.variables.remove(outputVarName)
            sd.ops.remove(outputVarName)
        }

        var outputVar: SDVariable? = null
        if(!attributes.containsKey("value")) {
            //zeros float 32 as according to onnx spec
            outputVar = sd.create(outputVarName,inputShape, DataType.FLOAT,"c",true)
        } else {
            val firstVal = attributes["value"] as INDArray
            outputVar = sd.create(inputShape,firstVal.dataType(),"c",false)
            val firstValue = firstVal.getDouble(0)
            outputVar = sd.assign(outputVarName,outputVar,sd.constant(firstValue))

        }


        return HookResult(outputVariables = mapOf(outputVar!!.name() to listOf(outputVar)),
            proceedWithInit = false)


    }



}