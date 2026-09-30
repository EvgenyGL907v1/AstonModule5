package fill;

import car.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Car> cars = null;

        while (true) {
            System.out.println("Меню: 1 - создать массив, 2 - посмотреть массив, 3 - выйти");
            int choice = readChoice(scanner);

            if (choice == 1) {
                cars = FillMain.fillMain(scanner);
                System.out.println("Массив создан. Элементов: " + cars.size());
            } else if (choice == 2) {
                if (cars == null) {
                    System.out.println("Массив ещё не создан.");
                } else {
                    printSorted(cars);
                }
            } else {
                System.out.println("Выход.");
                return;
            }
        }
    }

    private static void printSorted(List<Car> cars) {
        List<Car> sorted = cars.stream()
                .sorted(Comparator.comparingInt(Car::getPower))
                .toList();

        for (Car car : sorted) {
            System.out.println(car);
        }
    }

    private static int readChoice(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
                System.out.println("Введите число от 1 до 3");
            } else {
                scanner.next();
                System.out.println("Введите число от 1 до 3");
            }
        }
    }
}