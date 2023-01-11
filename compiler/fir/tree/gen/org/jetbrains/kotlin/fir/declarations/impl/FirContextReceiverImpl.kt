/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.fir.declarations.impl

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.fir.visitors.*
import org.jetbrains.kotlin.jvm.specialization.annotations.Monomorphic

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirContextReceiverImpl(
    override val source: KtSourceElement?,
    override var typeRef: FirTypeRef,
    override val customLabelName: Name?,
    override val labelNameFromTypeRef: Name?,
) : FirContextReceiver() {
    override fun <R, D, @Monomorphic VT : FirVisitor<R, D>> acceptChildren(visitor: VT, data: D) {
        typeRef.accept(visitor, data)
    }

    override fun <D, @Monomorphic TT: FirTransformer<D>> transformChildren(transformer: TT, data: D): FirContextReceiverImpl {
        transformTypeRef(transformer, data)
        return this
    }

    override fun <D> transformTypeRef(transformer: FirTransformer<D>, data: D): FirContextReceiverImpl {
        typeRef = typeRef.transform(transformer, data)
        return this
    }

    override fun replaceTypeRef(newTypeRef: FirTypeRef) {
        typeRef = newTypeRef
    }
}
