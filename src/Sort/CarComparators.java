package Java5.src.Sort;

import Java5.src.Car.Car;

import java.util.Comparator;

public final class CarComparators {

    private CarComparators() {
    }

    public static final Comparator<Car> BY_POWER = new Comparator<Car>() {
        @Override
        public int compare(Car c1, Car c2) {
            if (c1.getPower() < c2.getPower()) return -1;
            if (c1.getPower() > c2.getPower()) return 1;
            return 0;
        }
    };

    public static final Comparator<Car> BY_YEAR = new Comparator<Car>() {
        @Override
        public int compare(Car c1, Car c2) {
            if (c1.getYear() < c2.getYear()) return -1;
            if (c1.getYear() > c2.getYear()) return 1;
            return 0;
        }
    };

    public static final Comparator<Car> BY_MODEL = new Comparator<Car>() {
        @Override
        public int compare(Car c1, Car c2) {
            String s1 = c1.getModel();
            String s2 = c2.getModel();

            int len1 = s1.length();
            int len2 = s2.length();
            int limit = Math.min(len1, len2);

            for (int k = 0; k < limit; k++) {
                char ch1 = s1.charAt(k);
                char ch2 = s2.charAt(k);
                if (ch1 != ch2) {
                    return ch1 - ch2;
                }
            }

            return len1 - len2;
        }
    };
}
