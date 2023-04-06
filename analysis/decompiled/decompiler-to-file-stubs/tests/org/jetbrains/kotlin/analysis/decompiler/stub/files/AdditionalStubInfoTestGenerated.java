/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.decompiler.stub.files;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/decompiled/decompiler-to-file-stubs/testData/additionalClsStubInfo")
@TestDataPath("$PROJECT_ROOT")
public class AdditionalStubInfoTestGenerated extends AbstractAdditionalStubInfoTest {
    @Test
    public void testAllFilesPresentInAdditionalClsStubInfo() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/decompiled/decompiler-to-file-stubs/testData/additionalClsStubInfo"), Pattern.compile("^([^\\.]+)$"), null, false);
    }

    @Test
    @TestMetadata("AnnotatedFlexibleTypes")
    public void testAnnotatedFlexibleTypes() throws Exception {
        runTest("analysis/decompiled/decompiler-to-file-stubs/testData/additionalClsStubInfo/AnnotatedFlexibleTypes/");
    }
}
