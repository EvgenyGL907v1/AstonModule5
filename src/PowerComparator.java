package DZ5;

import java.util.Comparator;
import DZ5.CarSorter;

public class PowerComparator implements Comparator<Car> {
    @Override
    public int  compare(Car c1, Car c2) {
        if (c1.getPower() < c2.getPower()) return -1;
        if (c1.getPower() > c2.getPower()) return 1;
        return 0;
    }
}

