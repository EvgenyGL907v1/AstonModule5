package test;

import car.Car;
import fill.FileFill;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestFileFill extends MyTest {

    public static void main(String[] args) {
        InputStream originalIn = System.in;

        testZeroLengthReturnsEmpty();
        testNegativeLengthReturnsEmpty();
        testCorrectParsing();
        testLimitByLength();

        System.setIn(originalIn);
        result();
    }

    private static void testZeroLengthReturnsEmpty() {
        List<Car> result = new FileFill().fill(0);
        check(result != null && result.isEmpty(), "testZeroLengthReturnsEmpty");
    }

    private static void testNegativeLengthReturnsEmpty() {
        List<Car> result = new FileFill().fill(-5);
        check(result != null && result.isEmpty(), "testNegativeLengthReturnsEmpty");
    }

    private static void testCorrectParsing() {
        File file = createTempFile(
                "Toyota,150,2020",
                "BMW,200,2021");
        setInput(file.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(2);
        check(result.size() == 2
                        && result.get(0).getModel().equals("Toyota")
                        && result.get(0).getPower() == 150
                        && result.get(0).getYear() == 2020
                        && result.get(1).getModel().equals("BMW"),
                "testCorrectParsing");
    }

    private static void testLimitByLength() {
        File file = createTempFile(
                "Toyota,150,2020",
                "BMW,200,2021",
                "Audi,120,2019",
                "Ford,90,2010");
        setInput(file.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(2);
        check(result.size() == 2, "testLimitByLength");
    }

    private static File createTempFile(String... lines) {
        try {
            File file = File.createTempFile("cars_fill_", ".txt");
            file.deleteOnExit();
            try (FileWriter writer = new FileWriter(file)) {
                for (String line : lines) {
                    writer.write(line + System.lineSeparator());
                }
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать временный файл", e);
        }
    }

    private static void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}
