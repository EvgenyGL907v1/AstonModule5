package test;

import car.Car;
import collection.CustomArrayList;
import file.CarFileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestCarFileWriter {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        String testFileName = "testCars.txt";

        File file = new File(testFileName);
        if (file.exists()) {
            file.delete();
        }

        Car car1 = new Car.Builder().setModel("Toyota").setPower(150).setYear(2020).build();
        Car car2 = new Car.Builder().setModel("BMW").setPower(200).setYear(2021).build();

        CustomArrayList<Car> list1 = new CustomArrayList<>();
        list1.add(car1);

        CustomArrayList<Car> list2 = new CustomArrayList<>();
        list2.add(car2);


        CarFileWriter.appendCarsToFile(testFileName, list1);
        CarFileWriter.appendCarsToFile(testFileName, list2);

        // читаем фыайл и смотрим строки
        int lineCount = 0;
        boolean hasToyota = false;
        boolean hasBMW = false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                if (line.contains("Toyota")) hasToyota = true;
                if (line.contains("BMW")) hasBMW = true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }

        // вывод
        if (lineCount == 2 && hasToyota && hasBMW) {
            passed++;
            System.out.println("PASS: testAppendMode");
        } else {
            failed++;
            System.out.println("FAIL: testAppendMode");
            System.out.println("Ожидалось 2 строки (Toyota и BMW). Получено строк: " + lineCount);
        }

        System.out.println("Пройдено: " + passed);
        System.out.println("Упало: " + failed);
    }
}
