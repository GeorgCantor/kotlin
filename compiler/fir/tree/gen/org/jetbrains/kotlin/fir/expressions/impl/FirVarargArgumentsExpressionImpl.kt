/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.fir.expressions.impl

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*
import org.jetbrains.kotlin.jvm.specialization.annotations.Monomorphic

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirVarargArgumentsExpressionImpl(
    override val source: KtSourceElement?,
    override var typeRef: FirTypeRef,
    override val annotations: MutableList<FirAnnotation>,
    override val arguments: MutableList<FirExpression>,
    override var varargElementType: FirTypeRef,
) : FirVarargArgumentsExpression() {
    override fun <R, D, @Monomorphic VT : FirVisitor<R, D>> acceptChildren(visitor: VT, data: D) {
        typeRef.accept(visitor, data)
        annotations.forEach { it.accept(visitor, data) }
        arguments.forEach { it.accept(visitor, data) }
        varargElementType.accept(visitor, data)
    }

    override fun <D, @Monomorphic TT: FirTransformer<D>> transformChildren(transformer: TT, data: D): FirVarargArgumentsExpressionImpl {
        typeRef = typeRef.transform(transformer, data)
        transformAnnotations(transformer, data)
        arguments.transformInplace(transformer, data)
        varargElementType = varargElementType.transform(transformer, data)
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirVarargArgumentsExpressionImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun replaceTypeRef(newTypeRef: FirTypeRef) {
        typeRef = newTypeRef
    }
}
