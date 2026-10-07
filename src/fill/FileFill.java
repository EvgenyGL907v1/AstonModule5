package fill;

import collection.CustomArrayList;
import collection.CustomCollectors;
import car.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FileFill implements FillStrategy {

    @Override
    public List<Car> fill(int length) {

        if (length <= 0) {
            return new CustomArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите путь к файлу (ввод 0 - вернутся в меню): ");

            String filePath = scanner.nextLine().trim();

            if ("0".equals(filePath))
                return null;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                return reader.lines()
                        .map(this::parseLine)
                        .flatMap(Optional::stream)
                        .limit(length)
                        .collect(CustomCollectors.toCustomList());

            } catch (IOException e) {
                System.out.println("Не удалось прочитать файл. Введите путь повторно.");
            }
        }
    }

    private Optional<Car> parseLine(String line) {
        try {
            String[] fields = line.strip().split(",");
            if (fields.length != 3) {
                throw new IllegalArgumentException("Ожидалось три поля");
            }

            String model = fields[0];
            int power = Integer.parseInt(fields[1]);
            int year = Integer.parseInt(fields[2]);

            return Optional.of(
                    new Car.Builder()
                            .setModel(model)
                            .setPower(power)
                            .setYear(year)
                            .build()
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Строка: \"" + line + "\" не валидна, будет пропущена");

            return Optional.empty();
        }
    }
}