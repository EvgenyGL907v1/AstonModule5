package test;

import collection.CustomArrayList;

import java.util.Arrays;

public class TestCustomArrayList extends MyTest {

    public static void main(String[] args) {
        testDefaultCapacityAddGet();
        testGrowthBeyondCapacity();
        testAddAtIndex();
        testRemoveMiddle();
        testRemoveLast();
        testClear();
        testConstructorFromCollection();
        testNegativeInitialCapacity();
        testContainsViaAbstractList();

        result();
    }

    private static void testDefaultCapacityAddGet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("b");
        check(list.size() == 2 && list.get(0).equals("a") && list.get(1).equals("b"),
                "testDefaultCapacityAddGet");
    }

    private static void testGrowthBeyondCapacity() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        boolean ok = list.size() == 100;
        for (int i = 0; i < 100; i++) {
            if (list.get(i) != i) {
                ok = false;
                break;
            }
        }
        check(ok, "testGrowthBeyondCapacity");
    }

    private static void testAddAtIndex() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("c");
        list.add(1, "b");
        check(list.get(0).equals("a") && list.get(1).equals("b") && list.get(2).equals("c"),
                "testAddAtIndex");
    }

    private static void testRemoveMiddle() {
        CustomArrayList<String> list = new CustomArrayList<>(
                Arrays.asList("a", "b", "c"));
        String removed = list.remove(1);
        check(removed.equals("b")
                        && list.size() == 2
                        && list.get(0).equals("a")
                        && list.get(1).equals("c"),
                "testRemoveMiddle");
    }

    private static void testRemoveLast() {
        CustomArrayList<String> list = new CustomArrayList<>(
                Arrays.asList("a", "b", "c"));
        String removed = list.remove(2);
        check(removed.equals("c") && list.size() == 2, "testRemoveLast");
    }

    private static void testClear() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("a");
        list.add("b");
        list.clear();
        check(list.isEmpty() && list.size() == 0, "testClear");
    }

    private static void testConstructorFromCollection() {
        CustomArrayList<String> list = new CustomArrayList<>(
                Arrays.asList("x", "y", "z"));
        check(list.size() == 3
                        && list.get(0).equals("x")
                        && list.get(2).equals("z"),
                "testConstructorFromCollection");
    }

    private static void testNegativeInitialCapacity() {
        try {
            new CustomArrayList<String>(-1);
            failed++;
            System.out.println("FAIL: testNegativeInitialCapacity (нет исключения)");
        } catch (IllegalArgumentException e) {
            passed++;
            System.out.println("PASS: testNegativeInitialCapacity");
        }
    }

    private static void testContainsViaAbstractList() {
        CustomArrayList<String> list = new CustomArrayList<>(
                Arrays.asList("a", "b", "c"));
        check(list.contains("b") && !list.contains("z"), "testContainsViaAbstractList");
    }

}
