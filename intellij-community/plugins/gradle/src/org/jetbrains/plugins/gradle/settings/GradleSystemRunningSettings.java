// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.plugins.gradle.settings;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated use {@link GradleProjectSettings}
 */
@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "2019.2")
public class GradleSystemRunningSettings {
  private static final Logger LOG = Logger.getInstance("#" + GradleSystemRunningSettings.class.getPackage().getName());

  @NotNull
  @Deprecated
  public PreferredTestRunner getDefaultTestRunner() {
    return PreferredTestRunner.PLATFORM_TEST_RUNNER;
  }

  /**
   * @deprecated use {@link GradleProjectSettings#isDelegatedBuildEnabled(Module)} )
   */
  @Deprecated
  public boolean isUseGradleAwareMake() {
    return false;
  }

  @NotNull
  @Deprecated
  public static GradleSystemRunningSettings getInstance() {
    LOG.error("This class is deprecated please migrate to GradleProjectSettings");
    return new GradleSystemRunningSettings();
  }

  @Deprecated
  public enum PreferredTestRunner {
    PLATFORM_TEST_RUNNER, GRADLE_TEST_RUNNER, CHOOSE_PER_TEST
  }
}