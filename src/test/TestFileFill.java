package test;

import car.Car;
import fill.FileFill;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestFileFill {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        InputStream originalIn = System.in;

        testExactLength();
        testMoreLinesThanLength();
        testLessLinesThanLength();
        testInvalidLinesSkipped();
        testNonExistentPath();
        testMissingColon();

        System.setIn(originalIn);
        System.out.println("Пройдено: " + passed);
        System.out.println("Упало: " + failed);
    }

    private static void testExactLength() {
        File file = createTempFile(
                "Модель: Toyota, Мощность: 150, Год: 2020",
                "Модель: BMW, Мощность: 200, Год: 2021",
                "Модель: Lada, Мощность: 90, Год: 2015"
        );
        setInput(file.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(3);

        if (result.size() == 3) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testExactLength: ожидалось 3, получено " + result.size());
        }
    }

    private static void testMoreLinesThanLength() {
        File file = createTempFile(
                "Модель: Toyota, Мощность: 150, Год: 2020",
                "Модель: BMW, Мощность: 200, Год: 2021",
                "Модель: Lada, Мощность: 90, Год: 2015",
                "Модель: Mazda, Мощность: 120, Год: 2018",
                "Модель: Suzuki, Мощность: 110, Год: 2019"
        );
        setInput(file.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(3);

        if (result.size() == 3) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testMoreLinesThanLength: ожидалось 3, получено " + result.size());
        }
    }

    private static void testLessLinesThanLength() {
        File fileShort = createTempFile(
                "Модель: Toyota, Мощность: 150, Год: 2020"
        );
        File fileEnough = createTempFile(
                "Модель: Toyota, Мощность: 150, Год: 2020",
                "Модель: BMW, Мощность: 200, Год: 2021",
                "Модель: Lada, Мощность: 90, Год: 2015"
        );
        setInput(fileShort.getAbsolutePath() + "\n" + fileEnough.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(3);

        if (result.size() == 3) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testLessLinesThanLength: ожидалось 3, получено " + result.size());
        }
    }

    private static void testInvalidLinesSkipped() {
        File file = createTempFile(
                "Модель: Toyota, Мощность: 150, Год: 2020",
                "битая строка",
                "Модель: BMW, Мощность: 200, Год: 2021",
                "Модель: Lada, Мощность: abc, Год: 2015",
                "Модель: Mazda, Мощность: 120, Год: 2018"
        );
        setInput(file.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(3);

        if (result.size() == 3) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testInvalidLinesSkipped: ожидалось 3, получено " + result.size());
        }
    }

    private static void testNonExistentPath() {
        File fileOk = createTempFile(
                "Модель: Toyota, Мощность: 150, Год: 2020",
                "Модель: BMW, Мощность: 200, Год: 2021"
        );
        String badPath = "нет_такого_файла_" + System.nanoTime() + ".txt";
        setInput(badPath + "\n" + fileOk.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(2);

        if (result.size() == 2) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testNonExistentPath: ожидалось 2, получено " + result.size());
        }
    }

    private static void testMissingColon() {
        File file = createTempFile(
                "Модель Toyota, Мощность 150, Год 2020",
                "Модель: BMW, Мощность: 200, Год: 2021",
                "Модель: Lada, Мощность: 90, Год: 2015"
        );
        setInput(file.getAbsolutePath() + "\n");

        List<Car> result = new FileFill().fill(2);

        if (result.size() == 2) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testMissingColon: ожидалось 2, получено " + result.size());
        }
    }

    private static File createTempFile(String... lines) {
        try {
            File file = File.createTempFile("cars_test", ".txt");
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