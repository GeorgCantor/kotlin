/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.fe10.test.cases.generated.cases.components.expressionInfoProvider;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.analysis.api.fe10.test.configurator.AnalysisApiFe10TestConfiguratorFactory;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfiguratorFactoryData;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfigurator;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.TestModuleKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.FrontendKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisSessionMode;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiMode;
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.components.expressionInfoProvider.AbstractIsUsedAsExpressionTest;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link GenerateNewCompilerTests.kt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression")
@TestDataPath("$PROJECT_ROOT")
public class Fe10IdeNormalAnalysisSourceModuleIsUsedAsExpressionTestGenerated extends AbstractIsUsedAsExpressionTest {
    @NotNull
    @Override
    public AnalysisApiTestConfigurator getConfigurator() {
        return AnalysisApiFe10TestConfiguratorFactory.INSTANCE.createConfigurator(
            new AnalysisApiTestConfiguratorFactoryData(
                FrontendKind.Fe10,
                TestModuleKind.Source,
                AnalysisSessionMode.Normal,
                AnalysisApiMode.Ide
            )
        );
    }

    @Test
    public void testAllFilesPresentInIsUsedAsExpression() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("argument.kt")
    public void testArgument() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/argument.kt");
    }

    @Test
    @TestMetadata("boolean_else.kt")
    public void testBoolean_else() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/boolean_else.kt");
    }

    @Test
    @TestMetadata("default_parameter.kt")
    public void testDefault_parameter() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/default_parameter.kt");
    }

    @Test
    @TestMetadata("if_subject.kt")
    public void testIf_subject() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/if_subject.kt");
    }

    @Test
    @TestMetadata("initializer_when_branch.kt")
    public void testInitializer_when_branch() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/initializer_when_branch.kt");
    }

    @Test
    @TestMetadata("initializer_when_branch_block.kt")
    public void testInitializer_when_branch_block() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/initializer_when_branch_block.kt");
    }

    @Test
    @TestMetadata("initializer_when_branch_block_stmt.kt")
    public void testInitializer_when_branch_block_stmt() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/initializer_when_branch_block_stmt.kt");
    }

    @Test
    @TestMetadata("nonunit_lambda.kt")
    public void testNonunit_lambda() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/nonunit_lambda.kt");
    }

    @Test
    @TestMetadata("nonunit_lambda_multiple_statements.kt")
    public void testNonunit_lambda_multiple_statements() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/nonunit_lambda_multiple_statements.kt");
    }

    @Test
    @TestMetadata("return_explicit_unit.kt")
    public void testReturn_explicit_unit() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/return_explicit_unit.kt");
    }

    @Test
    @TestMetadata("return_implicit_unit.kt")
    public void testReturn_implicit_unit() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/return_implicit_unit.kt");
    }

    @Test
    @TestMetadata("return_value.kt")
    public void testReturn_value() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/return_value.kt");
    }

    @Test
    @TestMetadata("run_block.kt")
    public void testRun_block() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/run_block.kt");
    }

    @Test
    @TestMetadata("throw.kt")
    public void testThrow() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/throw.kt");
    }

    @Test
    @TestMetadata("throw_if_branch.kt")
    public void testThrow_if_branch() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/throw_if_branch.kt");
    }

    @Test
    @TestMetadata("try_catch_binop.kt")
    public void testTry_catch_binop() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/try_catch_binop.kt");
    }

    @Test
    @TestMetadata("unit_lambda.kt")
    public void testUnit_lambda() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/unit_lambda.kt");
    }

    @Test
    @TestMetadata("unit_lambda_nonunit_function.kt")
    public void testUnit_lambda_nonunit_function() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/unit_lambda_nonunit_function.kt");
    }

    @Test
    @TestMetadata("value_initializer.kt")
    public void testValue_initializer() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/value_initializer.kt");
    }

    @Test
    @TestMetadata("var_reassignment_if.kt")
    public void testVar_reassignment_if() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/var_reassignment_if.kt");
    }

    @Test
    @TestMetadata("when.kt")
    public void testWhen() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/when.kt");
    }

    @Test
    @TestMetadata("when_branch.kt")
    public void testWhen_branch() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/when_branch.kt");
    }

    @Test
    @TestMetadata("when_subject.kt")
    public void testWhen_subject() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/when_subject.kt");
    }

    @Test
    @TestMetadata("when_subject_with_branches.kt")
    public void testWhen_subject_with_branches() throws Exception {
        runTest("analysis/analysis-api/testData/components/expressionInfoProvider/isUsedAsExpression/when_subject_with_branches.kt");
    }
}
