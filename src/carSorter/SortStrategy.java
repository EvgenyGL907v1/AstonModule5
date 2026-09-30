package carSorter;

import java.util.Comparator;
import java.util.List;

@FunctionalInterface
public interface SortStrategy<T> {

    void sort(List<T> list, Comparator<T> comparator);
}