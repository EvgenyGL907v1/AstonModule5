package carsorter.even;

import car.Car;
import carsorter.CarSorter;
import carsorter.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class EvenCarSorter implements SortStrategy<Car> {

    private final ToIntFunction<Car> numericField;
    private final CarSorter innerSort = new CarSorter();

    public EvenCarSorter(ToIntFunction<Car> numericField) {
        this.numericField = numericField;
    }

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        if (list == null || list.size() < 2 || comparator == null) {
            return;
        }

        // Запоминаем позиции и сами чётные элементы
        List<Integer> evenPositions = new ArrayList<>();
        List<Car> evenElements = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Car element = list.get(i);
            if (numericField.applyAsInt(element) % 2 == 0) {
                evenPositions.add(i);
                evenElements.add(element);
            }
        }

        // Сортируем только чётные, используя уже существующий QuickSort
        innerSort.sort(evenElements, comparator);

        // Ставим отсортированные чётные обратно на их позиции
        for (int i = 0; i < evenPositions.size(); i++) {
            list.set(evenPositions.get(i), evenElements.get(i));
        }
    }
}