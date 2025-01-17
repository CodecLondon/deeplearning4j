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
package org.nd4j.samediff.frameworkimport.onnx.importer

import org.junit.Test
import org.nd4j.common.io.ClassPathResource
import org.nd4j.linalg.factory.Nd4j
import java.io.File
import java.util.*

class TestOnnxFrameworkImporter {

    @Test
    fun testAgeRace() {
        val importer = OnnxFrameworkImporter()
        val file = ClassPathResource("agerace_v2.onnx").file
        val result  = importer.runImport(file.absolutePath, emptyMap())
        val arr = Nd4j.ones(1, 3, 224, 224)
        result.batchOutput().inputs(Collections.singletonMap("input", arr))
            .output("output").output()
        println(result.summary())
        result.asFlatFile(File("agerace-samediff.fb"))
    }


}