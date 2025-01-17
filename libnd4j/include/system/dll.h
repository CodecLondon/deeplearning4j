/* ******************************************************************************
 *
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

//
// Created by agibsonccc on 3/5/16.
//

#ifndef NATIVEOPERATIONS_DLL_H
#define NATIVEOPERATIONS_DLL_H

#include <system/msvc.h>

#if defined _WIN32 || defined __CYGWIN__
  #ifdef __GNUC__
    #define ND4J_EXPORT __attribute__ ((dllexport))
  #else
    #define ND4J_EXPORT __declspec(dllexport) 
  #endif
  #define ND4J_LOCAL
#else
  #if __GNUC__ >= 4
    #define ND4J_EXPORT __attribute__ ((visibility ("default")))
    #define ND4J_LOCAL  __attribute__ ((visibility ("hidden")))
  #else
    #define ND4J_EXPORT
    #define ND4J_LOCAL
  #endif
#endif

#endif //NATIVEOPERATIONS_DLL_H
