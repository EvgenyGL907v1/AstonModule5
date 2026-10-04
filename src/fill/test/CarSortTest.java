package fill.test;

import car.Car;
import carSorter.CarSorter;

import java.util.ArrayList;
import java.util.List;

public class CarSortTest {

    public static void main(String[] args) {
        CarSorter sorter = new CarSorter();

        // ===== Тест 1: сортировка по модели (возрастание) =====
        List<Car> cars1 = createCars();
        sorter.sort(1, 1, cars1);

        System.out.println("Тест 1: сортировка по модели (возрастание)");
        for (Car c : cars1) {
            System.out.println(c.getModel());
        }
        if (cars1.get(0).getModel().equals("Audi")
                && cars1.get(1).getModel().equals("BMW")
                && cars1.get(2).getModel().equals("Ford")
                && cars1.get(3).getModel().equals("Honda")
                && cars1.get(4).getModel().equals("Toyota")) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 2: сортировка по модели (убывание) =====
        List<Car> cars2 = createCars();
        sorter.sort(1, 2, cars2);

        System.out.println("Тест 2: сортировка по модели (убывание)");
        for (Car c : cars2) {
            System.out.println(c.getModel());
        }
        if (cars2.get(0).getModel().equals("Toyota")
                && cars2.get(1).getModel().equals("Honda")
                && cars2.get(2).getModel().equals("Ford")
                && cars2.get(3).getModel().equals("BMW")
                && cars2.get(4).getModel().equals("Audi")) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 3: сортировка по мощности (возрастание) =====
        List<Car> cars3 = createCars();
        sorter.sort(2, 1, cars3);

        System.out.println("Тест 3: сортировка по мощности (возрастание)");
        for (Car c : cars3) {
            System.out.println(c.getPower());
        }
        if (cars3.get(0).getPower() == 120
                && cars3.get(1).getPower() == 150
                && cars3.get(2).getPower() == 180
                && cars3.get(3).getPower() == 250
                && cars3.get(4).getPower() == 300) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 4: сортировка по мощности (убывание) =====
        List<Car> cars4 = createCars();
        sorter.sort(2, 2, cars4);

        System.out.println("Тест 4: сортировка по мощности (убывание)");
        for (Car c : cars4) {
            System.out.println(c.getPower());
        }
        if (cars4.get(0).getPower() == 300
                && cars4.get(1).getPower() == 250
                && cars4.get(2).getPower() == 180
                && cars4.get(3).getPower() == 150
                && cars4.get(4).getPower() == 120) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 5: сортировка по году (возрастание) =====
        List<Car> cars5 = createCars();
        sorter.sort(3, 1, cars5);

        System.out.println("Тест 5: сортировка по году (возрастание)");
        for (Car c : cars5) {
            System.out.println(c.getYear());
        }
        if (cars5.get(0).getYear() == 2010
                && cars5.get(1).getYear() == 2015
                && cars5.get(2).getYear() == 2017
                && cars5.get(3).getYear() == 2018
                && cars5.get(4).getYear() == 2020) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 6: сортировка по году (убывание) =====
        List<Car> cars6 = createCars();
        sorter.sort(3, 2, cars6);

        System.out.println("Тест 6: сортировка по году (убывание)");
        for (Car c : cars6) {
            System.out.println(c.getYear());
        }
        if (cars6
                .get(0).getYear() == 2020
                && cars6.get(1).getYear() == 2018
                && cars6.get(2).getYear() == 2017
                && cars6.get(3).getYear() == 2015
                && cars6.get(4).getYear() == 2010) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 7: пустой список =====
        List<Car> emptyList = new ArrayList<>();
        sorter.sort(1, 1, emptyList);
        System.out.println("Тест 7: пустой список");
        if (emptyList.size() == 0) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 8: список из одного элемента =====
        List<Car> oneCar = new ArrayList<>();
        oneCar.add(new Car.Builder()
                .setModel("Toyota")
                .setPower(150)
                .setYear(2015)
                .build());
        sorter.sort(1, 1, oneCar);
        System.out.println("Тест 8: список из одного элемента");
        if (oneCar.size() == 1 && oneCar.get(0).getModel().equals("Toyota")) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 9: null список =====
        System.out.println("Тест 9: null список");
        try {
            sorter.sort(1, 1, null);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("FAIL: " + e);
        }
        System.out.println();

        // ===== Тест 10: неверный choice =====
        List<Car> cars10 = new ArrayList<>();
        cars10.add(new Car.Builder().setModel("Toyota").setPower(150).setYear(2015).build());
        cars10.add(new Car.Builder().setModel("BMW").setPower(300).setYear(2020).build());
        sorter.sort(99, 1, cars10);
        System.out.println("Тест 10: неверный choice");
        if (cars10.get(0).getModel().equals("Toyota")
                && cars10.get(1).getModel().equals("BMW")) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 11: список с одинаковыми значениями =====
        List<Car> sameCars = new ArrayList<>();
        sameCars.add(new Car.Builder().setModel("Toyota").setPower(150).setYear(2015).build());
        sameCars.add(new Car.Builder().setModel("Toyota").setPower(150).setYear(2015).build());
        sameCars.add(new Car.Builder().setModel("Toyota").setPower(150).setYear(2015).build());
        sorter.sort(1, 1, sameCars);
        System.out.println("Тест 11: одинаковые элементы");
        if (sameCars.size() == 3
                && sameCars.get(0).getModel().equals("Toyota")
                && sameCars.get(1).getModel().equals("Toyota")
                && sameCars.get(2).getModel().equals("Toyota")) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 12: уже отсортированный список =====
        List<Car> sortedList = new ArrayList<>();
        sortedList.add(new Car.Builder().setModel("Audi").setPower(100).setYear(2010).build());
        sortedList.add(new Car.Builder().setModel("BMW").setPower(200).setYear(2015).build());
        sortedList.add(new Car.Builder().setModel("Toyota").setPower(300).setYear(2020).build());
        sorter.sort(2, 1, sortedList);
        System.out.println("Тест 12: уже отсортированный список");
        if (sortedList.get(0).getPower() == 100
                && sortedList.get(1).getPower() == 200
                && sortedList.get(2).getPower() == 300) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
        System.out.println();

        // ===== Тест 13: обратно отсортированный список =====
        List<Car> reverseList = new ArrayList<>();


        reverseList.add(new Car.Builder().setModel("Toyota").setPower(300).setYear(2020).build());
        reverseList.add(new Car.Builder().setModel("BMW").setPower(200).setYear(2015).build());
        reverseList.add(new Car.Builder().setModel("Audi").setPower(100).setYear(2010).build());
        sorter.sort(2, 1, reverseList);
        System.out.println("Тест 13: обратно отсортированный список");
        if (reverseList.get(0).getPower() == 100
                && reverseList.get(1).getPower() == 200
                && reverseList.get(2).getPower() == 300) {
            System.out.println("OK");
        } else {
            System.out.println("FAIL");
        }
    }

    // ===== Хелпер: создаёт список из 5 машин =====
    private static List<Car> createCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().setModel("Toyota").setPower(150).setYear(2015).build());
        cars.add(new Car.Builder().setModel("BMW").setPower(300).setYear(2020).build());
        cars.add(new Car.Builder().setModel("Audi").setPower(250).setYear(2018).build());
        cars.add(new Car.Builder().setModel("Ford").setPower(120).setYear(2010).build());
        cars.add(new Car.Builder().setModel("Honda").setPower(180).setYear(2017).build());
        return cars;
    }
}