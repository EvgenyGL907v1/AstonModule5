package Java5.src.fill;

import Java5.src.collection.CustomArrayList;
import Java5.src.collection.CustomCollectors;
import Java5.src.Car.Car;

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
            System.out.println("Введите путь к файлу:");

            String filePath = scanner.nextLine().trim();

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(filePath))) {

                return reader.lines()
                        .map(this::parseLine)
                        .flatMap(Optional::stream)
                        .limit(length)
                        .collect(CustomCollectors.toCustomList());

            } catch (IOException e) {
                System.out.println(
                        "Не удалось прочитать файл. Введите путь повторно."
                );
            }
        }
    }
    private Optional<Car> parseLine(String line) {
        try {
            String[] fields = line.strip().split(",");

            if (fields.length != 3) {
                throw new IllegalArgumentException(
                        "Ожидалось три поля"
                );
            }

            String model = extractValue(fields[0]);

            int power = Integer.parseInt(
                    extractValue(fields[1])
            );

            int year = Integer.parseInt(
                    extractValue(fields[2])
            );

            return Optional.of(
                    new Java5.src.Car.Builder()
                            .setModel(model)
                            .setPower(power)
                            .setYear(year)
                            .build()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Строка: \"" + line + "\" не валидна, будет пропущена"
            );

            return Optional.empty();
        }
    }

    private String extractValue(String field) {
        String[] parts = field.split(":", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Некорректный формат поля: " + field
            );
        }

        String value = parts[1].trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException(
                    "Значение поля не может быть пустым"
            );
        }

        return value;
    }
}