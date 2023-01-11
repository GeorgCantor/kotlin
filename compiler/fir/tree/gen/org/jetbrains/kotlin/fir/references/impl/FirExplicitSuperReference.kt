/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.fir.references.impl

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.references.FirSuperReference
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*
import org.jetbrains.kotlin.jvm.specialization.annotations.Monomorphic

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirExplicitSuperReference(
    override val source: KtSourceElement?,
    override val labelName: String?,
    override var superTypeRef: FirTypeRef,
) : FirSuperReference() {
    override fun <R, D, @Monomorphic VT : FirVisitor<R, D>> acceptChildren(visitor: VT, data: D) {
        superTypeRef.accept(visitor, data)
    }

    override fun <D, @Monomorphic TT: FirTransformer<D>> transformChildren(transformer: TT, data: D): FirExplicitSuperReference {
        superTypeRef = superTypeRef.transform(transformer, data)
        return this
    }

    override fun replaceSuperTypeRef(newSuperTypeRef: FirTypeRef) {
        superTypeRef = newSuperTypeRef
    }
}
