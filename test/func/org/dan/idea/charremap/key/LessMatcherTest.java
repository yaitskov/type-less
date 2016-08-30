package org.dan.idea.charremap.key;

public class LessMatcherTest extends BaseMatcherTest {
    public LessMatcherTest() {
        super(',', '<');
    }

    public void testTopClassGeneric() {
        checkYes(7, "class A {\n}\n");
    }

    public void testTopClassImplementsGeneric() {
        checkYes(58, "import java.lang.Comparable;\nclass A implements Comparable {\n}\n");
    }

    public void testTopGenericClassImplementsGeneric() {
        checkYes(76, "import java.lang.Comparable;\nclass A<Integer, Integer> implements Comparable {\n}\n");
    }

    public void testFieldOfTopClass() {
        checkYes(37, "import java.util.List; class A { List l; }");
    }

    public void testArrayFieldInitOfTopClass() {
        checkNo(33, "class A { int[] a = new int[] { 1 }\n }");
    }

    public void testFieldOfTopGenericClass() {
        checkYes(40, "import java.util.List; class A<T> { List l; }");
    }

    public void testStaticMethodOfTopClass() {
        checkYes(24, "class A { public static }");
    }

    public void testStaticMethodOfPublicTopClass() {
        checkYes(24, "public class A { public static }");
    }

    public void testStaticFinalMethodOfPublicFinalTopClass() {
        checkYes(43, "public final class A {\n public static final\n}");
    }

    public void testTopClassImplementsNestedList() {
        checkYes(51, "import java.util.List; class A implements List<List> {}");
    }

    public void testTopClassImplementsMapCommaAfterFirstArg() {
        checkNo(49, "import java.util.Map; class A implements Map<List> {}");
    }

    public void testInitFieldOfTopClass() {
        checkYes(69, "import java.util.ArrayList; class A { List<Integer> l = new ArrayList}");
    }

    public void testReturn() {
        checkYes(79, "import java.util.ArrayList; class A { List<Integer> f () { return new ArrayList } }");
    }

    public void testLocalVariable() {
        checkYes(49, "import java.util.List; class A { void f () { List } }");
    }

    public void testSetLocalVariable() {
        checkYes(71, "import java.util.List; class A { void f () { List<Integer> l = new List } }");
    }

    public void testCommaArgument() {
        checkNo(23, "class A { void f (int a) {}}");
    }

    public void testSecondCommaArgument() {
        checkYes(33, "class A { void f (int a, String b) {}}");
    }

    public void testSecondGenericArgumentOfTopGenericClass() {
        checkYes(55, "import java.util.List; class A<T> { void f (int a, List) {}}");
    }

    public void testSecondGenericArgumentWithNameOfTopGenericClass() {
        checkYes(55, "import java.util.List; class A<T> { void f (int a, List b) {}}");
    }

    public void testTopClassImplementsMapOfLists() {
        checkYes(47, "import java.util.Map;\nimport java.util.List;\nclass A implements Map<Integer, List> {}");
    }
}
