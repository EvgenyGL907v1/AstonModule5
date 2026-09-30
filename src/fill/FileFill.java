package fill;

import car.Car;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileFill implements FillStrategy {


    @Override
    public List<Car> fill(int length) {
        System.out.println("Данные в файле должны храниться в формате: Модель: ..., Мощность: ..., Год: ...\n"
                + "Пример: \"Модель: Subaru, Мощность: 120, Год: 2026\"\n"
                + "Необходимо помнить, что из файла будут вычитаны только первые " + length + " строк\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            final ReadResult read = filePath(scanner);
            List<Car> cars = read.cars();
            int totalLines = read.totalLines();
            int skippedLines = read.skippedLines();
            if (cars.size() >= length) {
                System.out.println("Прочитано " + totalLines + " строк. Пропущено " + skippedLines + " строк\n"
                        + "В массив внесено " + length + " строк");
                return cars.stream().limit(length).toList();
            } else
                System.out.println("Количество валидных строк меньше указанной длины. Укажите другой файл или измените текущий");
        }
    }

    private ReadResult filePath(Scanner scanner) {
        while (true) {
            System.out.println("Введите путь до файла: ");
            String fileRead = scanner.nextLine();
            List<Car> cars = new ArrayList<>();
            int totalLines = 0;
            int skippedLines = 0;
            try (BufferedReader filePath = new BufferedReader(new FileReader(fileRead))) {
                String line;
                while ((line = filePath.readLine()) != null) {
                    totalLines++;
                    try {
                        String[] car = line.strip().split(",");
                        String model = extractValue(car[0]);
                        int power = Integer.parseInt(extractValue(car[1]));
                        int year = Integer.parseInt(extractValue(car[2]));
                        cars.add(new Car.Builder().setModel(model).setPower(power).setYear(year).build());
                    } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                        System.out.println("Строка: \"" + line + "\" не валидна, будет пропущена");
                        skippedLines++;
                    }
                }
                return new ReadResult(cars, totalLines, skippedLines);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден по указанному пути");
            } catch (IOException e) {
                System.out.println("Проблема при вычитке данных из файла");
            } catch (InvalidPathException e) {
                System.out.println("Некорректный путь к файлу");
            }
        }
    }

    private static String extractValue(String field) {
        int idx = field.indexOf(":");
        if (idx == -1) {
            throw new IllegalArgumentException("Отсутствует разделитель ':'");
        }
        return field.substring(idx + 1).strip();
    }

    private record ReadResult(List<Car> cars, int totalLines, int skippedLines) {

        @Override
        public List<Car> cars() {
                return List.copyOf(cars);
            }
        }
}