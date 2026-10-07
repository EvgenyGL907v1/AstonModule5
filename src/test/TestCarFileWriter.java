package test;

import car.Car;
import collection.CustomArrayList;
import file.CarFileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TestCarFileWriter extends MyTest {

    public static void main(String[] args) {
        testFileFormat();
        testAppendMode();
        testEmptyList();
        testOverwriteIsAppendNotTruncate();

        result();
    }

    private static void testFileFormat() {
        File file = tempFile();
        List<Car> cars = new ArrayList<>();
        cars.add(car("Toyota", 150, 2020));

        CarFileWriter.appendCarsToFile(file.getAbsolutePath(), cars);

        List<String> lines = readLines(file);
        check(lines.size() == 1 && lines.get(0).equals("Toyota,150,2020"),
                "testFileFormat");
    }

    private static void testAppendMode() {
        File file = tempFile();
        List<Car> first = new ArrayList<>();
        first.add(car("Toyota", 150, 2020));
        List<Car> second = new ArrayList<>();
        second.add(car("BMW", 200, 2021));

        CarFileWriter.appendCarsToFile(file.getAbsolutePath(), first);
        CarFileWriter.appendCarsToFile(file.getAbsolutePath(), second);

        List<String> lines = readLines(file);
        check(lines.size() == 2
                        && lines.get(0).equals("Toyota,150,2020")
                        && lines.get(1).equals("BMW,200,2021"),
                "testAppendMode");
    }

    private static void testEmptyList() {
        File file = tempFile();
        CarFileWriter.appendCarsToFile(file.getAbsolutePath(), new CustomArrayList<>());
        List<String> lines = readLines(file);
        check(lines.isEmpty(), "testEmptyList");
    }

    private static void testOverwriteIsAppendNotTruncate() {
        File file = tempFile();
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 1, 1886));
        cars.add(car("B", 2, 1887));

        CarFileWriter.appendCarsToFile(file.getAbsolutePath(), cars);
        CarFileWriter.appendCarsToFile(file.getAbsolutePath(), cars);

        List<String> lines = readLines(file);
        check(lines.size() == 4, "testOverwriteIsAppendNotTruncate");
    }

    private static Car car(String model, int power, int year) {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }

    private static File tempFile() {
        try {
            File file = File.createTempFile("cars_writer_", ".txt");
            file.deleteOnExit();
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать временный файл", e);
        }
    }

    private static List<String> readLines(File file) {
        try {
            List<String> lines = new ArrayList<>();
            for (String line : Files.readAllLines(file.toPath())) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл", e);
        }
    }
}
