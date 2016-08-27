package org.dan.idea.charremap.key;

public class AtMatcherTest extends BaseMatcherTest {
    public AtMatcherTest() {
        super('2', '@');
    }

    public void testTopClassNoAnnotations() {
        checkYes(1, "\n\nclass A {}\n");
    }

    public void testTopClassNoAnnotationsFirstChar() {
        checkYes(0, "\n\nclass A {}\n");
    }

    public void testTopClassTouchIdAhead() {
        checkNo(0, "class A {}\n");
    }

    public void testTopClassTouchIdBack() {
        checkNo(5, "class A {}\n");
    }

    public void testTopClassPackage() {
        checkYes(10, "package x;\n\nclass A {}\n");
    }

    public void testTopClassImport() {
        checkYes(25, "import java.lang.String;\n\nclass A {}\n");
    }

    public void testTopClassPackageAndImport() {
        checkYes(36, "package x;\nimport java.lang.String;\n\nclass A {}\n");
    }

    public void testTopClassBeforeAnnotation() {
        checkYes(0, " @Deprecated\nclass A {}\n");
    }

    public void testTopClassAfterAnnotation() {
        checkYes(12, "@Deprecated \nclass A {}\n");
    }

    public void testTopClassTouchBeforeAnnotation() {
        checkYes(0, "@Deprecated \nclass A {}\n");
    }

    public void testFieldTouchBeforeAnnotation() {
        checkYes(10, "class A { @Deprecated int x; }\n");
    }

    public void testFieldNoAnnotationAroundSpaces() {
        checkYes(10, "class A {  int x; }\n");
    }

    public void testFieldNoAnnotationSpaceAfter() {
        checkYes(9, "class A { int x; }\n");
    }

    public void testFieldNoAnnotationTouchType() {
        checkYes(10, "class A { int x; }\n");
    }

    public void testMethodNoAnnotationAroundSpaces() {
        checkYes(10, "class A {  void f() {} }\n");
    }

    public void testSubClassFieldNoAnnotationTouchTypeAroundSpaces() {
        checkYes(20, "class A { class B {  int x; } }\n");
    }

    public void testFieldInAnnotation() {
        checkNo(11, "class A { @Deprecated int x; }\n");
    }

    public void testTopClassTouchAfterAnnotation() {
        checkNo(11, "@Deprecated\nclass A {}\n");
    }

    public void testTopClassInAnnotation() {
        checkNo(1, "@Deprecated\nclass A {}\n");
    }
}
