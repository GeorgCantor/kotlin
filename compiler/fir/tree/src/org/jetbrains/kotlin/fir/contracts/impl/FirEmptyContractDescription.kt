/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.contracts.impl

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.contracts.FirContractDescription
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.FirVisitor
import org.jetbrains.kotlin.jvm.specialization.annotations.Monomorphic

object FirEmptyContractDescription : FirContractDescription() {
    override val source: KtSourceElement? get() = null

    override fun <R, D, @Monomorphic VT : FirVisitor<R, D>> acceptChildren(visitor: VT, data: D) {}

    override fun <D, @Monomorphic TT: FirTransformer<D>> transformChildren(transformer: TT, data: D): FirContractDescription {
        return this
    }
}