package Fill;

import Car.Car;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileFill implements FillStrategy {

    @Override
    public List<Car> fill(int length) {
        System.out.println("Данные в файле должны храниться в формате: Модель: ..., Мощность: ..., Год: ...\n"
        + "Пример: \"Модель: Subaru, Мощность: 120, Год: 2026\n"
        + "\nНеобходимо помнить, что из файла будут вычитаны только первые " + length + " строк\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            List<Car> cars = filePath(scanner);
            if (cars.size() >= length) {
                System.out.println("Вычитаны первые " + length + " строк");
                return cars.stream().limit(length).toList();
            }
            else System.out.println("Отредактируйте файл или укажите другой путь");
        }
    }

    private List<Car> filePath(Scanner scanner) {
        while (true) {
            System.out.println("Введите путь до файла: ");
            String fileRead = scanner.nextLine();
            List<Car> cars = new ArrayList<>();
            try (BufferedReader filePath = new BufferedReader(new FileReader(fileRead))) {
                String line;
                while ((line = filePath.readLine()) != null) {
                    String[] car = line.strip().split(",");
                    String model = car[0].substring(car[0].indexOf(":") + 1).strip();
                    int power = Integer.parseInt(car[1].substring(car[1].indexOf(":") + 1).strip());
                    int year = Integer.parseInt(car[2].substring(car[2].indexOf(":") + 1).strip());
                    cars.add(new Car.Builder().setModel(model).setPower(power).setYear(year).build());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден по указанному пути");
            } catch (IOException e) {
                System.out.println("Проблема при вычитке данных из файла");
            }
            return cars;
        }
    }
}
