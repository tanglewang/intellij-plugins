// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.angular2.inspections;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTagChild;
import com.intellij.psi.xml.XmlText;
import com.intellij.util.containers.ContainerUtil;
import org.angular2.lang.Angular2Bundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import static org.angular2.codeInsight.tags.Angular2TagDescriptorsProvider.NG_CONTENT;

public class Angular2NonEmptyNgContentInspection extends Angular2HtmlLikeTemplateLocalInspectionTool {

  @Override
  protected void visitXmlTag(@NotNull ProblemsHolder holder, @NotNull XmlTag tag) {
    if (NG_CONTENT.equals(tag.getName())) {
      XmlTagChild[] content = tag.getValue().getChildren();
      if (ContainerUtil.find(content, el -> !(el instanceof XmlText)
                                            || !el.getText().trim().isEmpty()) != null) {
        holder.registerProblem(tag, new TextRange(content[0].getTextRangeInParent().getStartOffset(),
                                                  content[content.length - 1].getTextRangeInParent().getEndOffset()),
                               Angular2Bundle.message("angular.inspection.template.ng-content-with-content"),
                               new RemoveContentQuickFix()
        );
      }
    }
  }

  private static class RemoveContentQuickFix implements LocalQuickFix {
    @Nls
    @NotNull
    @Override
    public String getName() {
      return Angular2Bundle.message("angular.quickfix.template.remove-tag-content.name");
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
      return Angular2Bundle.message("angular.quickfix.family");
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
      final XmlTag tag = (XmlTag)descriptor.getPsiElement();
      PsiElement[] content = tag.getValue().getChildren();
      if (content.length > 0) {
        tag.deleteChildRange(content[0], content[content.length - 1]);
      }
    }
  }
}
