package test;

import car.Car;
import menu.CarService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCarService extends MyTest {

    public static void main(String[] args) {
        testInitialState();
        testSetCars();
        testClear();
        testSortByPowerAscending();
        testSortByPowerDescending();
        testSortByModel();
        testSortOnEmptyNoException();

        result();
    }

    private static void testInitialState() {
        CarService service = new CarService();
        check(!service.isCreated() && service.isEmpty() && service.size() == 0,
                "testInitialState");
    }

    private static void testSetCars() {
        CarService service = new CarService();
        service.setCars(cars());
        check(service.isCreated() && service.size() == 3, "testSetCars");
    }

    private static void testClear() {
        CarService service = new CarService();
        service.setCars(cars());
        service.clear();
        check(service.isEmpty() && !service.isCreated(), "testClear");
    }

    private static void testSortByPowerAscending() {
        CarService service = new CarService();
        service.setCars(cars());
        service.sort(2, 1); // 2 = POWER, 1 = возрастание
        check(powers(service.getCars()).equals(Arrays.asList(120, 150, 300)),
                "testSortByPowerAscending");
    }

    private static void testSortByPowerDescending() {
        CarService service = new CarService();
        service.setCars(cars());
        service.sort(2, 2);
        check(powers(service.getCars()).equals(Arrays.asList(300, 150, 120)),
                "testSortByPowerDescending");
    }

    private static void testSortByModel() {
        CarService service = new CarService();
        service.setCars(cars());
        service.sort(1, 1); // 1 = MODEL
        check(models(service.getCars()).equals(Arrays.asList("Audi", "BMW", "Toyota")),
                "testSortByModel");
    }

    private static void testSortOnEmptyNoException() {
        CarService service = new CarService();
        try {
            service.sort(2, 1);
            service.sortEven(2, 1);
            passed++;
            System.out.println("PASS: testSortOnEmptyNoException");
        } catch (Exception e) {
            failed++;
            System.out.println("FAIL: testSortOnEmptyNoException (" + e + ")");
        }
    }

    private static List<Car> cars() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2015));
        cars.add(car("BMW", 300, 2020));
        cars.add(car("Audi", 120, 2010));
        return cars;
    }

    private static Car car(String model, int power, int year) {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }

    private static List<Integer> powers(List<Car> list) {
        List<Integer> r = new ArrayList<>();
        for (Car c : list) r.add(c.getPower());
        return r;
    }

    private static List<String> models(List<Car> list) {
        List<String> r = new ArrayList<>();
        for (Car c : list) r.add(c.getModel());
        return r;
    }
}
