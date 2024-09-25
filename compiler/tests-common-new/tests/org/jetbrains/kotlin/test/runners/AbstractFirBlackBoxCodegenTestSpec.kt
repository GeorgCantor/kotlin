/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.test.runners

import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.backend.BlackBoxCodegenSuppressor
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.AdditionalFilesDirectives.SPEC_HELPERS
import org.jetbrains.kotlin.test.directives.ConfigurationDirectives.WITH_STDLIB
import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives.FULL_JDK
import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives.WITH_REFLECT
import org.jetbrains.kotlin.test.frontend.fir.FirFailingTestSuppressor
import org.jetbrains.kotlin.test.model.TestFile
import org.jetbrains.kotlin.test.runners.codegen.AbstractFirBlackBoxCodegenTestBase
import org.jetbrains.kotlin.test.services.SourceFilePreprocessor
import org.jetbrains.kotlin.test.services.TestServices
import org.jetbrains.kotlin.test.services.sourceProviders.SpecHelpersSourceFilesProvider
import org.jetbrains.kotlin.utils.bind
import java.util.regex.Pattern

abstract class AbstractFirBlackBoxCodegenTestSpecBase(parser: FirParser) : AbstractFirBlackBoxCodegenTestBase(parser) {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        with(builder) {
            baseFirSpecBlackBoxCodegenTestConfiguration()
        }
    }
}

open class AbstractFirBlackBoxCodegenTestSpec : AbstractFirBlackBoxCodegenTestSpecBase(FirParser.LightTree) {
    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)
        builder.useAdditionalService { LightTreeSyntaxDiagnosticsReporterHolder() }
    }
}
private const val HELPERS_PACKAGE_VARIABLE = "<?PACKAGE?>"
private val packagePattern: Pattern = Pattern.compile("""(?:^|\n)package (?<packageName>.*?)(?:;|\n)""")
private class PackageNamePreprocessor(testServices: TestServices) : SourceFilePreprocessor(testServices) {
    var packageName = ""

    override fun process(file: TestFile, content: String): String {
        val packageName = packagePattern.matcher(content).let {
            if (it.find()) it.group("packageName") else null
        }
        if (packageName != null) {
            this.packageName = packageName
        }
        return content.replace(HELPERS_PACKAGE_VARIABLE, if (this.packageName == "") "" else "package ${this.packageName}")
    }
}
private fun TestConfigurationBuilder.baseFirSpecBlackBoxCodegenTestConfiguration(baseDir: String = ".") {
    defaultDirectives {
        +SPEC_HELPERS
        +WITH_STDLIB
        +WITH_REFLECT
        +FULL_JDK
    }
    useSourcePreprocessor(::PackageNamePreprocessor)
    useAdditionalSourceProviders(::SpecHelpersSourceFilesProvider.bind("codegen/box", baseDir))

    useAfterAnalysisCheckers(
        ::FirFailingTestSuppressor,
        ::BlackBoxCodegenSuppressor,
    )
}
