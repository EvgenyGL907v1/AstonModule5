package file;

import car.Car;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CarFileWriter {
    public static void appendCarsToFile(String filePath, List<Car> cars) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            for (Car car : cars) {
                String carLine = car.getModel() + "," + car.getPower() + "," + car.getYear();
                writer.write(carLine);
                writer.newLine();
            }

            System.out.println("Записано " + cars.size() + " элементов в файл: " + filePath);

        } catch (IOException e) {

            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
