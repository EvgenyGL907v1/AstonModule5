package test;

import car.Car;
import carsorter.PowerComparator;
import carsorter.YearComparator;
import carsorter.even.EvenCarSorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEvenCarSorter extends MyTest {

    public static void main(String[] args) {
        testEvenPowerAscending();
        testEvenPowerDescending();
        testEvenYear();
        testAllOddUnchanged();
        testAllEvenFullySorted();
        testNullList();
        testSingleElement();
        testNullComparator();

        result();
    }

    private static void testEvenPowerAscending() {
        List<Car> list = powers(1, 4, 3, 2, 5);
        new EvenCarSorter(Car::getPower).sort(list, new PowerComparator());

        check(Arrays.equals(powersArr(list), new int[]{1, 2, 3, 4, 5}),
                "testEvenPowerAscending");
    }

    private static void testEvenPowerDescending() {
        // чётные 4,2; по убыванию -> 4,2 (порядок не меняется)
        List<Car> list = powers(1, 4, 3, 2, 5);
        new EvenCarSorter(Car::getPower).sort(list, new PowerComparator().reversed());

        check(Arrays.equals(powersArr(list), new int[]{1, 4, 3, 2, 5}),
                "testEvenPowerDescending");
    }

    private static void testEvenYear() {
        List<Car> list = years(2021, 2020, 2019, 2018, 2017);
        new EvenCarSorter(Car::getYear).sort(list, new YearComparator());

        check(Arrays.equals(yearsArr(list), new int[]{2021, 2018, 2019, 2020, 2017}),
                "testEvenYear");
    }

    private static void testAllOddUnchanged() {
        List<Car> list = powers(1, 3, 5, 7);
        new EvenCarSorter(Car::getPower).sort(list, new PowerComparator());
        check(Arrays.equals(powersArr(list), new int[]{1, 3, 5, 7}),
                "testAllOddUnchanged");
    }

    private static void testAllEvenFullySorted() {
        List<Car> list = powers(8, 2, 6, 4);
        new EvenCarSorter(Car::getPower).sort(list, new PowerComparator());
        check(Arrays.equals(powersArr(list), new int[]{2, 4, 6, 8}),
                "testAllEvenFullySorted");
    }

    private static void testNullList() {
        try {
            new EvenCarSorter(Car::getPower).sort(null, new PowerComparator());
            passed++;
            System.out.println("PASS: testNullList");
        } catch (Exception e) {
            failed++;
            System.out.println("FAIL: testNullList (" + e + ")");
        }
    }

    private static void testSingleElement() {
        List<Car> list = powers(4);
        new EvenCarSorter(Car::getPower).sort(list, new PowerComparator());
        check(list.size() == 1 && list.get(0).getPower() == 4, "testSingleElement");
    }

    private static void testNullComparator() {
        List<Car> list = powers(4, 2);
        new EvenCarSorter(Car::getPower).sort(list, null);
        check(Arrays.equals(powersArr(list), new int[]{4, 2}), "testNullComparator");
    }

    private static List<Car> powers(int... values) {
        List<Car> list = new ArrayList<>();
        for (int p : values) {
            list.add(new Car.Builder().setModel("M" + p).setPower(p).setYear(2020).build());
        }
        return list;
    }

    private static List<Car> years(int... values) {
        List<Car> list = new ArrayList<>();
        for (int y : values) {
            list.add(new Car.Builder().setModel("M" + y).setPower(100).setYear(y).build());
        }
        return list;
    }

    private static int[] powersArr(List<Car> list) {
        int[] r = new int[list.size()];
        for (int i = 0; i < r.length; i++) r[i] = list.get(i).getPower();
        return r;
    }

    private static int[] yearsArr(List<Car> list) {
        int[] r = new int[list.size()];
        for (int i = 0; i < r.length; i++) r[i] = list.get(i).getYear();
        return r;
    }
}
