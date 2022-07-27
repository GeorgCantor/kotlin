/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.js.checkers

import org.jetbrains.kotlin.fir.analysis.checkers.expression.*
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsDefinedExternallyCallChecker
import org.jetbrains.kotlin.fir.analysis.js.checkers.expression.FirJsQualifierChecker

object JsExpressionCheckers : ExpressionCheckers() {
    override val annotationCallCheckers: Set<FirAnnotationCallChecker>
        get() = setOf(
            FirJsQualifierChecker,
        )

    override val qualifiedAccessCheckers: Set<FirQualifiedAccessChecker>
        get() = setOf(
            FirJsDefinedExternallyCallChecker,
        )
}
