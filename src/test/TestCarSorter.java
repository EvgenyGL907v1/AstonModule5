package test;

import car.Car;
import carsorter.CarSorter;
import carsorter.PowerComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCarSorter extends MyTest {

    public static void main(String[] args) {
        CarSorter sorter = new CarSorter();

        testSortByPowerAscending(sorter);
        testSortByPowerDescending(sorter);
        testSortByModelDescending(sorter);
        testSortByYearAscending(sorter);
        testSortByYearDescending(sorter);
        testSortWithComparator(sorter);
        testSortWithNullComparator(sorter);

        result();
    }

    private static void testSortByPowerAscending(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sortByPower(list, 1);
        check(powers(list).equals(Arrays.asList(120, 150, 180, 250, 300)),
                "testSortByPowerAscending");
    }

    private static void testSortByPowerDescending(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sortByPower(list, 2);
        check(powers(list).equals(Arrays.asList(300, 250, 180, 150, 120)),
                "testSortByPowerDescending");
    }

    private static void testSortByModelDescending(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sortByModel(list, 2);
        check(models(list).equals(Arrays.asList("Toyota", "Honda", "Ford", "BMW", "Audi")),
                "testSortByModelDescending");
    }

    private static void testSortByYearAscending(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sortByYear(list, 1);
        check(years(list).equals(Arrays.asList(2010, 2015, 2017, 2018, 2020)),
                "testSortByYearAscending");
    }

    private static void testSortByYearDescending(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sortByYear(list, 2);
        check(years(list).equals(Arrays.asList(2020, 2018, 2017, 2015, 2010)),
                "testSortByYearDescending");
    }

    private static void testSortWithComparator(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sort(list, new PowerComparator());
        check(powers(list).equals(Arrays.asList(120, 150, 180, 250, 300)),
                "testSortWithComparator");
    }

    private static void testSortWithNullComparator(CarSorter sorter) {
        List<Car> list = cars();
        sorter.sort(list, null);
        // компаратор null -> сортировка не выполняется, порядок прежний
        check(powers(list).equals(Arrays.asList(150, 300, 250, 120, 180)),
                "testSortWithNullComparator");
    }


    private static List<Car> cars() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2015));
        cars.add(car("BMW", 300, 2020));
        cars.add(car("Audi", 250, 2018));
        cars.add(car("Ford", 120, 2010));
        cars.add(car("Honda", 180, 2017));
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

    private static List<Integer> years(List<Car> list) {
        List<Integer> r = new ArrayList<>();
        for (Car c : list) r.add(c.getYear());
        return r;
    }

    private static List<String> models(List<Car> list) {
        List<String> r = new ArrayList<>();
        for (Car c : list) r.add(c.getModel());
        return r;
    }
}
