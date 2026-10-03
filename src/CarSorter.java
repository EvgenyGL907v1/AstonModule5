package carSorter;

import car.Car;

import java.util.Comparator;
import java.util.List;


public class CarSorter implements SortStrategy<Car> {

public void sort(int choice, int direction, List<Car> list) {
    if (list == null || list.size() < 2) return;

    switch (choice) {
        case 1:
            sortByModel(list, direction);
            break;
        case 2:
            sortByPower(list, direction);
            break;
        case 3:
            sortByYear(list, direction);
            break;
        default:
            break;
    }
}

    public void sortByPower(List<Car> list, int direction) {
        if (list == null || list.size() < 2) return;
        Comparator<Car> comparator = new PowerComparator();
        if (direction == 2) {
            comparator = comparator.reversed();
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    public void sortByModel(List<Car> list, int direction) {
        if (list == null || list.size() < 2) return;
        Comparator<Car> comparator = new ModelComparator();
        if (direction == 2) {
            comparator = comparator.reversed();
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    public void sortByYear(List<Car> list, int direction) {
        if (list == null || list.size() < 2) return;
        Comparator<Car> comparator = new YearComparator();
        if (direction == 2) {
            comparator = comparator.reversed();
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
    if (list !=null && list.size() >=2 && comparator != null) {
        quickSort(list, 0, list.size() - 1, comparator);
        }
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