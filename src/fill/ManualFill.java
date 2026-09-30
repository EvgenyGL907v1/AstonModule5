package fill;

import car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class ManualFill implements FillStrategy {

    @Override
    public List<Car> fill(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be >= 0, was " + length);
        }
        Scanner scanner = new Scanner(System.in);
        List<Car> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            System.out.println("Заполните параметры для " + (i + 1) + " элемента массива");
            System.out.println("Введите модель автомобиля:");
            String model = readModel(scanner);
            System.out.println("Введите мощность автомобиля, в числовом виде:");
            // Сделал через лямбду. Должно работать без проблем. Будут замечания, пишите
            int power = readInt(scanner, p -> p > 0,
                    "Мощность должна быть больше 0. Введите мощность повторно");
            System.out.println("Введите год производства, в числовом виде. Диапазоном от "
                    + Car.MIN_YEAR + " до " + Car.MAX_YEAR + " года:");
            int year = readInt(scanner, y -> y >= Car.MIN_YEAR && y <= Car.MAX_YEAR,
                    "Год производства должен быть в диапазоне от "
                            + Car.MIN_YEAR + " до " + Car.MAX_YEAR);
            result.add(new Car.Builder().setModel(model).setPower(power).setYear(year).build());
        }
        return result;
    }

    private String readModel(Scanner scanner) {
        while (true) {
            String model = scanner.nextLine().trim();
            if (!model.isEmpty()) {
                return model;
            }
            System.out.println("Модель не может быть пустой. Введите модель повторно");
        }
    }

    private int readInt(Scanner scanner, IntPredicate valid, String errorMessage) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (valid.test(value)) {
                    return value;
                }
                System.out.println(errorMessage);
            } else if (scanner.hasNext()) {
                scanner.next();
                System.out.println("Необходимо ввести числовое значение.");
            } else {
                throw new IllegalStateException("Входной поток завершён");
            }
        }
    }
}