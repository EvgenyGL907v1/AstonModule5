package fill;

import car.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FillMain {
    private static final Map<Integer, FillStrategy> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(1, new ManualFill());
        STRATEGIES.put(2, new FileFill());
        STRATEGIES.put(3, new RandomFill());
    }

    public static List<Car> fillMain(Scanner scanner) {
        System.out.println("Введите длину массива:");
        int length = setLength(scanner);
        System.out.println("Выберите способ заполнения: 1 - вручную, 2 - из файла, 3 - рандомно");
        int strategy = setStrategy(scanner);
        CarFiller filler = new CarFiller(STRATEGIES.get(strategy));
        return filler.fill(length);
    }

    private static int setLength(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int scan = scanner.nextInt();
                if (scan > 0) {
                    return scan;
                } else {
                    System.out.println("Длина должна быть больше 0");
                }
            } else {
                scanner.next();
                System.out.println("Введите число");
            }
        }
    }

    private static int setStrategy(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int strategy = scanner.nextInt();
                if (strategy >= 1 && strategy <= 3) {
                    return strategy;
                } else {
                    System.out.println("Неверное значение. Введите число от 1 до 3");
                }
            } else {
                scanner.next();
                System.out.println("Введите число от 1 до 3");
            }
        }
    }
}