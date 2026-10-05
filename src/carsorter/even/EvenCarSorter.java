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

    public EvenCarSorter(ToIntFunction<Car> numericField) { //как вызвать??
        this.numericField = numericField; //Что будет, сортировать четной сортировкой по модели (String)?
    }


    //как лучше сделать сортировку из наших ТРЕХ вариантов?)
    public void sort(int choice, int direction, List<Car> list) {
        if (list == null || list.size() < 2) return;

        switch (choice) {
            case 1:
                innerSort.sortByModel(list, direction);
                break;
            case 2:
                innerSort.sortByPower(list, direction);
                break;
            case 3:
                innerSort.sortByYear(list, direction);
                break;
            default:
                break;
        }
    }

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }

        // исходные четные элементы
        List<Integer> evenPositions = new ArrayList<>();

        // сортируемые четные элементы
        List<Car> evenElements = new ArrayList<>();

        // проходим по списку и собираем чётные
        for (int i = 0; i < list.size(); i++) {
            Car element = list.get(i);
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
