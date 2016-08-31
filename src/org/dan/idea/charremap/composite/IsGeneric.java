package org.dan.idea.charremap.composite;

import static com.intellij.psi.impl.source.tree.JavaElementType.TYPE;

import java.util.Map;

import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.PsiTypeParameter;
import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class IsGeneric implements Matcher {
    public static IsGeneric isGeneric() {
        return new IsGeneric();
    }

    @Override
    public boolean test(MatcherState s) {
        if (s.type() != TYPE) {
            return false;
        }
        PsiElement psi = s.getNode().getPsi();
        PsiClassType classType = (PsiClassType) ((PsiTypeElement) psi).getType();
        Map<PsiTypeParameter, PsiType> substitutionMap = classType.resolveGenerics()
                .getSubstitutor().getSubstitutionMap();
        return !substitutionMap.isEmpty();
    }
}
