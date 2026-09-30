package DZ5;


import java.util.Comparator;
import java.util.List;


public class CarSorter implements SortStrategy {


    public void sortByPower(List<Car> list) {
        if (list == null || list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, new PowerComparator());
    }

    public void sortByModel(List<Car> list) {
        if (list == null || list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, new PowerComparator());
    }

    public void sortByYear(List<Car> list) {
        if (list == null || list.size() < 2) return;
        quickSort(list, 0, list.size() - 1, new PowerComparator());
    }

    private static class PowerComparator implements Comparator<Car> {
        @Override
        public int  compare(Car c1, Car c2) {
            if (c1.getPower() < c2.getPower()) return -1;
            if (c1.getPower() > c2.getPower()) return 1;
            return 0;
        }
    }

    private static class ModelComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        String s1 = c1.getModel();
        String s2 = c2.getModel();

        int len1 = s1.length();
        int len2 = s2.length();
        int lim = Math.min(len1, len2);

        for (int k = 0; k < lim; k++) {
            char ch1 = s1.charAt(k);
            char ch2 = s2.charAt(k);
            if (ch1 != ch2) {
                return ch1 - ch2;
            }
        }
        return len1 - len2;
    }
    }

    private static class YearComparator implements Comparator<Car> {
        @Override
    public int compare(Car c1, Car c2) {
            if (c1.getYear() < c2.getYear()) return -1;
            if (c1.getYear() > c2.getYear()) return 1;
            return 0;
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