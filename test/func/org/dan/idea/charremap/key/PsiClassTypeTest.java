package org.dan.idea.charremap.key;

import com.intellij.psi.PsiClassType;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.testFramework.PlatformTestCase;
import org.junit.Ignore;

public class PsiClassTypeTest extends PlatformTestCase implements Mix {
    @Ignore
    public void testResolveGenerics() {
        assertFalse(PsiClassType.getTypeByName("java.util.List", getProject(),
                GlobalSearchScope.allScope(getProject())).resolveGenerics()
                .getSubstitutor().getSubstitutionMap().isEmpty());
    }
}
