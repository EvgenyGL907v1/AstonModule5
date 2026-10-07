package test;

import car.Car;
import carsorter.YearComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestYearComparator extends MyTest {

    public static void main(String[] args) {
        YearComparator cmp = new YearComparator();

        check(cmp.compare(car(2010), car(2020)) == -1, "testLess");
        check(cmp.compare(car(2020), car(2010)) == 1, "testGreater");
        check(cmp.compare(car(2015), car(2015)) == 0, "testEqual");

        result();
    }

    private static Car car(int year) {
        return new Car.Builder().setModel("M").setPower(100).setYear(year).build();
    }
}
