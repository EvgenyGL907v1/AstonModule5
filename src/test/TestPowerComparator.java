package test;

import car.Car;
import carsorter.PowerComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPowerComparator extends MyTest {

    public static void main(String[] args) {
        PowerComparator cmp = new PowerComparator();

        check(cmp.compare(car(100), car(200)) == -1, "testLess");
        check(cmp.compare(car(200), car(100)) == 1, "testGreater");
        check(cmp.compare(car(150), car(150)) == 0, "testEqual");

        result();
    }

    private static Car car(int power) {
        return new Car.Builder().setModel("M").setPower(power).setYear(2020).build();
    }
}
