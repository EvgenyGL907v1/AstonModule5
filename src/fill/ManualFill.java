package fill;

import car.Car;
import collection.CustomCollectors;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ManualFill implements FillStrategy {

    @Override
    public List<Car> fill(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(
                    "length must be >= 0, was " + length
            );
        }

        Scanner scanner = new Scanner(System.in);

//        return IntStream.range(0, length)
//                .mapToObj(i -> readCar(scanner, i + 1))
//                .collect(CustomCollectors.toCustomList());
        List<Car> result = IntStream.range(0, length)
                .mapToObj(i -> readCar(scanner, i + 1)) // Stream<Optional<Car>>
                .takeWhile(Optional::isPresent)         // стоп по первому empty
                .map(Optional::get)                     // Stream<Car>
                .collect(CustomCollectors.toCustomList());
        return result.isEmpty() ? null : result;
    }

    private Optional<Car> readCar(Scanner scanner, int number) {
        System.out.println(
                "Заполните параметры для " + number + " элемента массива"
        );

//        System.out.println("Введите модель автомобиля:");
//        String model = readModel(scanner);
        System.out.println("Введите модель автомобиля (или 0 для выхода):");
        Optional<String> model = readModel(scanner);
        if (model.isEmpty()) {
            return Optional.empty();
        }


//        System.out.println(
//                "Введите мощность автомобиля, в числовом виде:"
//        );
//        int power = readInt(
//                scanner,
//                p -> p > 0,
//                "Мощность должна быть больше 0. Введите мощность повторно"
//        );
        System.out.println(
                "Введите мощность автомобиля, в числовом виде (или 0 для выхода):"
        );
        Optional<Integer> power = readInt(
                scanner,
                p -> p > 0,
                "Мощность должна быть больше 0. Введите мощность повторно"
        );
        if (power.isEmpty()) {
            return Optional.empty();
        }

        System.out.println(
                "Введите год производства, в числовом виде. Диапазоном от "
                        + Car.MIN_YEAR
                        + " до "
                        + Car.MAX_YEAR
                        + " года (или 0 для выхода):"
        );

//        int year = readInt(
//                scanner,
//                y -> y >= Car.MIN_YEAR && y <= Car.MAX_YEAR,
//                "Год производства должен быть в диапазоне от "
//                        + Car.MIN_YEAR
//                        + " до "
//                        + Car.MAX_YEAR
//        );
//
//        return new Car.Builder()
//                .setModel(model)
//                .setPower(power)
//                .setYear(year)
//                .build();
        Optional<Integer> year = readInt(
                scanner,
                y -> y >= Car.MIN_YEAR && y <= Car.MAX_YEAR,
                "Год производства должен быть в диапазоне от "
                        + Car.MIN_YEAR
                        + " до "
                        + Car.MAX_YEAR
        );
        if (year.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new Car.Builder()
                .setModel(model.get())
                .setPower(power.get())
                .setYear(year.get())
                .build());
    }

    private Optional<String> readModel(Scanner scanner) {
        while (true) {
//            String model = scanner.nextLine().trim();
//            if (!model.isEmpty()) {
//                return model;
//            }
//            System.out.println("Модель не может быть пустой. Введите модель повторно");
            String model = scanner.nextLine().trim();
            if ("0".equals(model)) {
                return Optional.empty();
            }
            if (!model.isEmpty()) {
                return Optional.of(model);
            }
            System.out.println("Модель не может быть пустой. Введите модель повторно.");
        }
    }

    private Optional<Integer> readInt(Scanner scanner, IntPredicate valid, String errorMessage) {
        while (true) {
//            if (scanner.hasNextInt()) {
//                int value = scanner.nextInt();
//                scanner.nextLine();
//                if (valid.test(value)) {
//                    return value;
//                }
//                System.out.println(errorMessage);
//            } else if (scanner.hasNext()) {
//                scanner.next();
//                System.out.println("Необходимо ввести числовое значение.");
//            } else {
//                throw new IllegalStateException("Входной поток завершён");
//            }
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value == 0) {
                    return Optional.empty(); // сигнал остановки
                }
                if (valid.test(value)) {
                    return Optional.of(value);
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