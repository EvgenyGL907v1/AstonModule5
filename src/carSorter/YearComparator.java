package carSorter;

import car.Car;

import java.util.Comparator;

public class YearComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        if (c1.getYear() < c2.getYear()) return -1;
        if (c1.getYear() > c2.getYear()) return 1;
        return 0;
    }
}