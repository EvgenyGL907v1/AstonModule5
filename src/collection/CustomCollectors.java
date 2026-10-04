package collection;

import java.util.stream.Collector;

public final class CustomCollectors {

    private CustomCollectors() {

    }

    public static <T> Collector<T, CustomArrayList<T>, CustomArrayList<T>> toCustomList() {
        return Collector.of(
                CustomArrayList::new,
                CustomArrayList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                }
        );
    }
}