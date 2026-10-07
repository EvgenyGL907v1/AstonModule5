package test;

import collection.CustomArrayList;
import collection.CustomCollectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestCustomCollectors extends MyTest {

    public static void main(String[] args) {
        testCollectReturnsCustomArrayList();
        testCollectPreservesOrderAndSize();
        testCollectEmptyStream();
        testCollectSingleElement();

        result();
    }

    private static void testCollectReturnsCustomArrayList() {
        CustomArrayList<Integer> result =
                Stream.of(1, 2, 3).collect(CustomCollectors.toCustomList());
        check(result instanceof CustomArrayList, "testCollectReturnsCustomArrayList");
    }

    private static void testCollectPreservesOrderAndSize() {
        CustomArrayList<String> result =
                Stream.of("a", "b", "c").collect(CustomCollectors.toCustomList());
        check(result.size() == 3
                        && result.get(0).equals("a")
                        && result.get(1).equals("b")
                        && result.get(2).equals("c"),
                "testCollectPreservesOrderAndSize");
    }

    private static void testCollectEmptyStream() {
        CustomArrayList<String> result =
                Stream.<String>empty().collect(CustomCollectors.toCustomList());
        check(result.isEmpty(), "testCollectEmptyStream");
    }

    private static void testCollectSingleElement() {
        List<String> source = Arrays.asList("only");
        CustomArrayList<String> result =
                source.stream().collect(CustomCollectors.toCustomList());
        check(result.size() == 1 && result.get(0).equals("only"),
                "testCollectSingleElement");
    }
}
