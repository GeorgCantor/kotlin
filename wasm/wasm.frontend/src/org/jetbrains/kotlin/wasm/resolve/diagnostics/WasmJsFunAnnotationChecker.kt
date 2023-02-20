/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.wasm.resolve.diagnostics

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext
import org.jetbrains.kotlin.resolve.descriptorUtil.isEffectivelyExternal
import org.jetbrains.kotlin.resolve.source.getPsi

// TODO: Implement in K2: KT-56849
object WasmJsFunAnnotationChecker : DeclarationChecker {
    private val jsFunFqName = FqName("kotlin.JsFun")

    override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
        if (descriptor !is MemberDescriptor) return
        val jsFun = descriptor.annotations.findAnnotation(jsFunFqName) ?: return
        if (!descriptor.isEffectivelyExternal() || !DescriptorUtils.isTopLevelDeclaration(descriptor)) {
            val jsFunPsi = jsFun.source.getPsi() ?: declaration
            context.trace.report(ErrorsWasm.WRONG_JS_FUN_TARGET.on(jsFunPsi))
        }
    }
}