/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.fir.references.impl

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.references.FirDelegateFieldReference
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.fir.visitors.*
import org.jetbrains.kotlin.jvm.specialization.annotations.Monomorphic

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirDelegateFieldReferenceImpl(
    override val source: KtSourceElement?,
    override val candidateSymbol: FirBasedSymbol<*>?,
    override val resolvedSymbol: FirDelegateFieldSymbol,
) : FirDelegateFieldReference() {
    override val name: Name get() = Name.identifier("\$delegate")

    override fun <R, D, @Monomorphic VT : FirVisitor<R, D>> acceptChildren(visitor: VT, data: D) {}

    override fun <D, @Monomorphic TT: FirTransformer<D>> transformChildren(transformer: TT, data: D): FirDelegateFieldReferenceImpl {
        return this
    }
}
