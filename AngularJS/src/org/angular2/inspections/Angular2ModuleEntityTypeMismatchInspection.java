// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.angular2.inspections;

public class Angular2ModuleEntityTypeMismatchInspection extends Angular2ModuleConfigurationInspection {
  public Angular2ModuleEntityTypeMismatchInspection() {
    super(ProblemType.ENTITY_WITH_MISMATCHED_TYPE);
  }
}
