package Java5.src.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;


public class EvenOddBubbleSort<T> implements SortStrategy<T> {

    private final ToIntFunction<T> numericField;

    private final BubbleSort<T> innerSort = new BubbleSort<>();

    public EvenOddBubbleSort(ToIntFunction<T> numericField) {
        this.numericField = numericField;
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }

        // исходные четные элементы
        List<Integer> evenPositions = new ArrayList<>();

        // сортируемые четные элементы
        List<T> evenElements = new ArrayList<>();

        // проходим по списку и собираем чётные
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            if (numericField.applyAsInt(element) % 2 == 0) {
                evenPositions.add(i);
                evenElements.add(element);
            }
        }

        // сортируем только чётные элементы
        innerSort.sort(evenElements, comparator);

        // кладём отсортированные чётные обратно на их же позиции
        for (int i = 0; i < evenPositions.size(); i++) {
            list.set(evenPositions.get(i), evenElements.get(i));
        }
    }
}