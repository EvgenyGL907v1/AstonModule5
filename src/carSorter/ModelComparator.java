package carSorter;

import car.Car;

import java.util.Comparator;

public class ModelComparator implements Comparator<Car> {
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