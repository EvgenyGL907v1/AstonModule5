package test;

import car.Car;
import carsorter.ModelComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestModelComparator extends MyTest {

    public static void main(String[] args) {
        ModelComparator cmp = new ModelComparator();

        testEqual(cmp);
        testLess(cmp);
        testGreater(cmp);
        testPrefixByLength(cmp);
        testCaseSensitivity(cmp);

        result();
    }

    private static void testEqual(ModelComparator cmp) {
        check(cmp.compare(car("Audi"), car("Audi")) == 0, "testEqual");
    }

    private static void testLess(ModelComparator cmp) {
        check(cmp.compare(car("Audi"), car("BMW")) < 0, "testLess");
    }

    private static void testGreater(ModelComparator cmp) {
        check(cmp.compare(car("BMW"), car("Audi")) > 0, "testGreater");
    }

    private static void testPrefixByLength(ModelComparator cmp) {
        check(cmp.compare(car("Toyota"), car("ToyotaX")) < 0, "testPrefixByLength");
        check(cmp.compare(car("ToyotaX"), car("Toyota")) > 0, "testPrefixByLengthReverse");
    }

    private static void testCaseSensitivity(ModelComparator cmp) {
        // 'A'(65) < 'a'(97)
        check(cmp.compare(car("Audi"), car("audi")) < 0, "testCaseSensitivity");
    }

    private static Car car(String model) {
        return new Car.Builder().setModel(model).setPower(100).setYear(2020).build();
    }
}
