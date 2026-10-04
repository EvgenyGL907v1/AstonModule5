package Java5.src.file;

import Java5.src.Car.Car;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CarFileWriter {
    public static void appendCarsToFile(String filePath, List<Car> cars) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            for (Car car : cars) {

                writer.write(car.toString());
                writer.newLine();
            }

            System.out.println("Записано " + cars.size() + " элементов в файл: " + filePath);

        } catch (IOException e) {

            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
