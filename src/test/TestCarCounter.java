package test;

import car.Car;
import carcounter.CarCounter;

import java.util.ArrayList;
import java.util.List;

public class TestCarCounter extends MyTest {

    public static void main(String[] args) {
        testNullList();
        testEmptyList();
        testSingleMatch();
        testMultipleMatches();
        testNoMatch();
        testPartialMatchNotCounted();
        testLargeListConcurrency();

        result();
    }

    private static void testNullList() {
        check(CarCounter.countMatches(null, car("A", 100, 2020)) == 0, "testNullList");
    }

    private static void testEmptyList() {
        check(CarCounter.countMatches(new ArrayList<>(), car("A", 100, 2020)) == 0,
                "testEmptyList");
    }

    private static void testSingleMatch() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2020));
        check(CarCounter.countMatches(cars, car("Toyota", 150, 2020)) == 1,
                "testSingleMatch");
    }

    private static void testMultipleMatches() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2020));
        cars.add(car("BMW", 200, 2021));
        cars.add(car("Toyota", 150, 2020));
        cars.add(car("Toyota", 150, 2020));
        check(CarCounter.countMatches(cars, car("Toyota", 150, 2020)) == 3,
                "testMultipleMatches");
    }

    private static void testNoMatch() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2020));
        cars.add(car("BMW", 200, 2021));
        check(CarCounter.countMatches(cars, car("Audi", 120, 2019)) == 0,
                "testNoMatch");
    }

    private static void testPartialMatchNotCounted() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2020));
        check(CarCounter.countMatches(cars, car("Toyota", 999, 2020)) == 0,
                "testPartialMatchNotCounted");
    }

    private static void testLargeListConcurrency() {
        List<Car> cars = new ArrayList<>();
        int expected = 0;
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                cars.add(car("Match", 100, 2020));
                expected++;
            } else {
                cars.add(car("Other", 100, 2020));
            }
        }
        check(CarCounter.countMatches(cars, car("Match", 100, 2020)) == expected,
                "testLargeListConcurrency (ожидалось " + expected + ")");
    }

    private static Car car(String model, int power, int year) {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }
}
