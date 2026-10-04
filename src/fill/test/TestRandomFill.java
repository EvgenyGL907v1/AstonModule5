package Java5.src.fill.test;

import Java5.src.Car.Car;
import Java5.src.fill.RandomFill;

import java.util.List;

public class TestRandomFill {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testSizeOfFill();
        testModelPositive();
        testPowerPositive();
        testYearPositive();

        System.out.println("Пройдено: " + passed);
        System.out.println("Упало: " + failed);
    }

    private static void testSizeOfFill() {
        List<Car> result = new RandomFill().fill(5);
        if (result.size() == 5) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL: ожидалось 5, получено " + result.size());
        }
    }

    private static void testModelPositive() {
        List<Car> cars = new RandomFill().fill(50);
        boolean allPositive = true;
        for (Car car : cars) {
            if (car.getModel() == null || car.getModel().trim().isEmpty()) {
                allPositive = false;
                break;
            }
        }
        if (allPositive) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL: найдена пустая модель");
        }
    }

    private static void testPowerPositive() {
        List<Car> cars = new RandomFill().fill(50);
        boolean allPositive = true;
        for (Car car : cars) {
            if (car.getPower() <= 0) {
                allPositive = false;
                break;
            }
        }
        if (allPositive) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL: найдена мощность <= 0");
        }
    }

    private static void testYearPositive() {
        List<Car> cars = new RandomFill().fill(50);
        boolean allPositive = true;
        for (Car car : cars) {
            if (car.getYear() < 1886 || car.getYear() > 2036) {
                allPositive = false;
                break;
            }
        }
        if (allPositive) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL: найден некорректный год");
        }
    }

}
