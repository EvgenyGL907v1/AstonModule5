package Fill;

import Car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualFill implements FillStrategy {
    @Override
    public List<Car> fill(int length) {
        Scanner scanner = new Scanner(System.in);
        List<Car> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            System.out.println("Заполните параметры для " + (i + 1) + " элемента массива");
            System.out.println("Введите модель автомобиля:");
            String model = readModel(scanner);
            System.out.println("Введите мощность автомобиля, в числовом виде:");
            int power = readPower(scanner);
            System.out.println("Введите год производства, в числовом виде. Диапазоном от 1886 до 2036 года:");
            int year = readYear(scanner);
            result.add(new Car.Builder().setModel(model).setPower(power).setYear(year).build());
        }
        return result;
    }

    private String readModel(Scanner scanner) {
        while (true) {
            String model = scanner.nextLine();
            if (model != null && !model.trim().isEmpty()) {
                return model;
            }
            System.out.println("Модель не может быть пустой. Введите модель повторно");
        }
    }

    private int readPower(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int power = scanner.nextInt();
                if (power > 0) {
                    return power;
                }
                System.out.println("Мощность должна быть больше 0. Введите мощность повторно");
            } else {
                scanner.next();
                System.out.println("Необходимо ввести числовое значение. Введите мощность повторно");
            }
        }
    }

    private int readYear(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int year = scanner.nextInt();
                if (year >= 1886 && year <= 2036) {
                    return year;
                }
                System.out.println("Год производства должен быть в диапазон от 1886 до 2036");
            } else {
                scanner.next();
                System.out.println("Необходимо ввести числовое значение. Введите мощность повторно");
            }
        }
    }
}