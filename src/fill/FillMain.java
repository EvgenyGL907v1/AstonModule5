package fill;

import car.Car;
import java.util.List;
import java.util.Scanner;

public class FillMain {

    private enum FillType {
        BACK(0, "назад"),
        MANUAL(1, "вручную"),
        FILE(2, "из файла"),
        RANDOM(3, "рандомно");

        private final int code;
        private final String description;

        FillType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static FillType fromCode(int code) {
            for (FillType type : values()) {
                if (type.code == code) return type;
            }
            throw new IllegalArgumentException("Неверный код: " + code);
        }

        public FillStrategy createStrategy() {
            return switch (this) {
                case MANUAL -> new ManualFill();
                case FILE -> new FileFill();
                case RANDOM -> new RandomFill();
                case BACK -> null;
            };
        }
    }

    public static List<Car> fillMain(Scanner scanner) {
        System.out.println("Введите длину массива: (ввод 0 - вернутся в меню)");
        int length = readIntInRange(scanner, 0, Integer.MAX_VALUE, "Введите положительное число");
        if (length == 0) {
            return null;
        }

        System.out.println("Выберите способ заполнения: 1 - вручную, 2 - из файла, 3 - рандомно, 0 - вернуться в меню");
        int strategyCode = readIntInRange(scanner, 0, 3, "Неверное значение. Введите число от 0 до 3");

        FillType fillType = FillType.fromCode(strategyCode);
        if (fillType == FillType.BACK) {
            return null;
        }

        CarFiller filler = new CarFiller(fillType.createStrategy());
        return filler.fill(length);
    }

    private static int readIntInRange(Scanner scanner, int min, int max, String errorMessage) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(errorMessage);
            } else if (scanner.hasNext()) {
                scanner.next();
                System.out.println("Введите число");
            } else {
                throw new IllegalStateException("Входной поток завершён");
            }
        }
    }
}