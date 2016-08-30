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

    public void testTopFinalClassNoAnnotationTouch() {
        checkYes(0, "final class A { int x; }\n");
    }

    public void testTopPublicClassNoAnnotationTouch() {
        checkYes(0, "public class A { int x; }\n");
    }

    public void testFieldNoAnnotationTouchIntType() {
        checkYes(10, "class A { int x; }\n");
    }

    public void testBeforeFieldAfterAnnotationWithNewLine() {
        checkYes(23, "class A {\n @Deprecated \nprivate int x; }\n");
    }

    public void testStaticFieldNoAnnotationTouch() {
        checkYes(10, "class A { static int x; }\n");
    }

    public void testVolatileFieldNoAnnotationTouch() {
        checkYes(10, "class A { volatile int x; }\n");
    }

    public void testMethodNoAnnotationTouchIntType() {
        checkYes(10, "class A { int x() { return 0; } }\n");
    }

    public void testMethodAfterAnnotationBeforeNewLine() {
        checkYes(30, "public class A {\n @Deprecated \npublic int x() {\n return 0; } }\n");
    }

    public void testMethodNoAnnotationTouchStringType() {
        checkYes(10, "class A { String x() { return null; } }\n");
    }

    public void testMethodNoAnnotationTouchPublic() {
        checkYes(10, "class A { public String x() { return null; } }\n");
    }

    public void testBeforeId() {
        checkYes(10, "class A { asdf }\n");
    }

    public void testInId() {
        checkNo(11, "class A { asdf }\n");
    }

    public void testMethodNoAnnotationTouchStatic() {
        checkYes(10, "class A { static String x() { return null; } }\n");
    }

    public void testAnonymousClassMethodNoAnnotationTouchPublic() {
        checkYes(103, "import java.util.function.Supplier; class A { Supplier<Integer> f() { return new Supplier<Integer>() { public Integer get() { return 3; } }; } }\n");
    }

    public void testNoArgMethodOfAnonymousClassNoAnnotation() {
        checkYes(122, "import java.util.function.Supplier; class A { Supplier<Integer> f() { return new Supplier<Integer>() { public Integer get() { return 3; } }; } }\n");
    }

    public void testIntegerArgMethodOfAnonymousClassNoAnnotation() {
        checkYes(121, "import java.util.function.Consumer; class A { Consumer<Integer> f() { return new Consumer<Integer>() { final void accept(Integer x) {} }; } }\n");
    }

    public void testAfterStringArgMethodOfAnonymousClassNoAnnotation() {
        checkYes(150, "import java.util.function.BiConsumer; class A { BiConsumer<String, Integer> f() { return new Consumer<String, Integer>() { final void accept(String x,) {} }; } }\n");
    }

    public void testSecondArgMethodOfAnonymousClassBeforeAnnotationTouch() {
        checkYes(151, "import java.util.function.BiConsumer; class A { BiConsumer<String, Integer> f() { return new Consumer<String, Integer>() { final void accept(String x, @Deprecated Integer x) {} }; } }\n");
    }

    public void testSecondArgMethodOfAnonymousClassAfterAnnotationTouchType() {
        checkYes(163, "import java.util.function.BiConsumer; class A { BiConsumer<String, Integer> f() { return new Consumer<String, Integer>() { final void accept(String x, @Deprecated Integer x) {} }; } }\n");
    }

    public void testAnonymousClassSubClassNoAnnotationTouchPublic() {
        checkYes(103, "import java.util.function.Supplier; class A { Supplier<Integer> f() { return new Supplier<Integer>() { class B {} public Integer get() { return 3; } }; } }\n");
    }

    public void testLambdaSubClassNoAnnotationTouchPublic() {
        checkYes(85, "import java.util.function.Supplier; class A { Supplier<Integer> f() { return () -> { class B {} return 3; }; } }\n");
    }

    public void testAnonymousClassMethodNoAnnotationSpaceAfter() {
        checkYes(102, "import java.util.function.Supplier; class A { Supplier<Integer> f() { return new Supplier<Integer>() { public Integer get() { return 3; } }; } }\n");
    }

    public void testMethodNoAnnotationTouchFinal() {
        checkYes(10, "class A { final String x() { return null; } }\n");
    }

    public void testSubClassMethodNoAnnotationTouchStringType() {
        checkYes(20, "class A { class B { String x() { return null; } } }\n");
    }

    public void testSubClassMethodNoAnnotationTouchPublicAroundSpace() {
        checkYes(20, "class A { class B { \npublic String x() { return null; } } }\n");
    }

    public void testSubClassMethodNoAnnotationTouchPublic() {
        checkYes(20, "class A { class B { public String x() { return null; } } }\n");
    }

    public void testStaticSubClassMethodNoAnnotationTouchPublic() {
        checkYes(27, "class A { static class B { public String x() { return null; } } }\n");
    }

    public void testFinalSubClassMethodNoAnnotationTouchPublic() {
        checkYes(26, "class A { final class B { public String x() { return null; } } }\n");
    }

    public void testPublicSubClassMethodNoAnnotationTouchPublic() {
        checkYes(27, "class A { public class B { public String x() { return null; } } }\n");
    }

    public void testSubClassMethodNoAnnotationTouchFinal() {
        checkYes(20, "class A { class B { final String x() { return null; } } }\n");
    }

    public void testFieldNoAnnotationTouchStringType() {
        checkYes(10, "class A { String x; }\n");
    }

    public void testFieldNoAnnotationTouchPrivate() {
        checkYes(10, "class A { private int x; }\n");
    }

    public void testSubClassFieldNoAnnotationTouchPrivate() {
        checkYes(20, "class A { class B { private int x; } }\n");
    }

    public void testFieldNoAnnotationTouchFinal() {
        checkYes(10, "class A { final int x = 1; }\n");
    }

    public void testSubClassFieldNoAnnotationTouchFinal() {
        checkYes(20, "class A { class B { final int x = 1; } }\n");
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

    public void testNoArgMethod() {
        checkYes(17, "class A { void f() {} }\n");
    }

    public void testNoArgMethodSpaceAfter() {
        checkYes(17, "class A { void f( ) {} }\n");
    }

    public void testOneArgMethod() {
        checkYes(17, "class A { void f(int x) {} }\n");
    }

    public void testOneArrayArgMethod() {
        checkYes(17, "class A { void f(int[] x) {} }\n");
    }

    public void testOneArgMethodSpaceAfter() {
        checkYes(17, "class A { void f( int x) {} }\n");
    }

    public void testOneArgMethodAroundSpaces() {
        checkYes(18, "class A { void f(  int x) {} }\n");
    }

    public void testSecondArgMethod() {
        checkYes(24, "class A { void f(int x, double y) {} }\n");
    }

    public void testAfterFirstArgAndSpaceMethod() {
        checkYes(24, "class A { void f(int x, ) {} }\n");
    }

    public void testAfterFirstArgMethod() {
        checkYes(23, "class A { void f(int x,) {} }\n");
    }

    public void testOneFinalArgMethod() {
        checkYes(17, "class A { void f(final int x) {} }\n");
    }

    public void testOneStringArgMethod() {
        checkYes(17, "class A { void f(String x) {} }\n");
    }

    public void testOneAnnotatedArgMethod() {
        checkYes(17, "class A { void f(@Deprecated String x) {} }\n");
    }

    public void testStaticFinalIntField2() {
        checkNo(30, "class A { static final int f =\n}");
    }

    public void testPublicStaticFinalDoubleField2() {
        checkNo(40, "class A { public static final double f =\n}");
    }

    public void testFloatField2() {
        checkNo(19, "class A { float f =\n}");
    }

    public void testMethodBeforeField() {
        checkYes(22, "class A { float f = 1; void m() {} }");
    }

    public void testFieldBeforeMethod() {
        checkNo(19, "class A { float f =\n void m() {} }");
    }

    public void testFloatFieldSum1And2() {
        checkNo(26, "class A { float f = 1.0f + \n}");
    }

    public void testFloatFieldMethodArg() {
        checkNo(58, "class A { static int g(int i) { return i; } float f = A.g()\n}");
    }

    public void testAddDigitToFloatField2() {
        checkNo(21, "class A { float f = 1;\n}");
    }

    public void testPass2AsArg() {
        checkNo(40, "class A { void f(int a) {} void g() { f() } }");
    }

    public void testSet2ToLocalVar() {
        checkNo(29, "class A { void f() { int x = }}");
    }

    public void testSet2ToLocalVarInStatic() {
        checkNo(27, "class A { static { int x = \n}}");
    }

    public void testSetSumOf2ToLocalVar() {
        checkNo(33, "class A { void f() { int x = 1 + }}");
    }

    public void testAddDigitToFloatField2NoSemicolon() {
        checkNo(21, "class A { float f = 1}");
    }

    public void testReturnFloat2() {
        checkNo(29, "class A { float f() { return \n}\n}");
    }

    public void testStringLiteralField() {
        checkNo(22, "class A { String f = \"\"\n}");
    }

    public void testAllocateArraySize() {
        checkNo(28, "class A { int[] a = new int[]\n}");
    }

    public void testNonFirstLetterOfVariable() {
        checkNo(15, "class A { int a\n}");
    }

    public void testMidLetterOfVariable() {
        checkNo(15, "class A { int ab;\n}");
    }

    public void testMidLetterOfMethodDeclaration() {
        checkNo(16, "class A { void ab() {}\n}");
    }

    public void testMidLetterSubClassDeclaration() {
        checkNo(17, "class A { class Bb \n}");
    }

    public void testLastLetterSubClassDeclaration() {
        checkNo(18, "class A { class Bb \n}");
    }

    public void testLastLetterClassDeclaration() {
        checkNo(7, "class A {}");
    }
}
