// Copyright 2000-2018 JetBrains s.r.o.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package org.jetbrains.vuejs.cli

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.WebModuleBuilder
import com.intellij.platform.ProjectTemplate
import com.intellij.platform.ProjectTemplatesFactory

class VueTemplateFactory : ProjectTemplatesFactory() {
  override fun getGroups(): Array<String> = arrayOf(WebModuleBuilder.GROUP_NAME)

  override fun createTemplates(group: String?, context: WizardContext): Array<ProjectTemplate> {
    return arrayOf(VueCliProjectGenerator())
  }
}
