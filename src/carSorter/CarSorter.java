package Java5.src.carSorter;

import Java5.src.Car.Car;
import java.util.Comparator;
import java.util.List;


public class CarSorter implements SortStrategy {


    public void sortByPower(List<Car> list) {
        if (list == null || list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, new PowerComparator());
    }

    public void sortByModel(List<Car> list) {
        if (list == null || list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, new ModelComparator());
    }

    public void sortByYear(List<Car> list) {
        if (list == null || list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, new YearComparator());
    }

    @Override
    public void sort(List list, Comparator comparator) {

    }

    private void quickSort(List<Car> list, int low, int high, Comparator<Car> comparator) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, comparator);
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, high, comparator);
        }
    }
    private int partition(List<Car> list, int low, int high, Comparator<Car> comparator) {
        Car pivot = list.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <=0) {
                i++;
                swapped(list,i,j);
            }
        }
        swapped(list, i + 1, high);

        return i + 1;
    }

    private void swapped(List<Car> list, int i, int j) {
        Car temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}