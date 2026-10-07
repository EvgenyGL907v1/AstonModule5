package test;

import car.Car;

public class TestCar extends MyTest {

    public static void main(String[] args) {
        testValidBuild();
        testBoundaryValues();
        testToString();

        result();
    }

    private static void testValidBuild() {
        Car car = car("Toyota", 150, 2020);
        check(car.getModel().equals("Toyota")
                        && car.getPower() == 150
                        && car.getYear() == 2020,
                "testValidBuild");
    }

    private static void testBoundaryValues() {
        Car min = car("A", Car.MIN_POWER, Car.MIN_YEAR);
        Car max = car("B", 1000, Car.MAX_YEAR);
        check(min.getPower() == 1 && min.getYear() == Car.MIN_YEAR
                        && max.getYear() == Car.MAX_YEAR,
                "testBoundaryValues");
    }

    private static void testToString() {
        String s = car("BMW", 200, 2021).toString();
        check(s.contains("BMW") && s.contains("200") && s.contains("2021"),
                "testToString");
    }

    private static Car car(String model, int power, int year) {
        return new Car.Builder()
                .setModel(model)
                .setPower(power)
                .setYear(year)
                .build();
    }
}
