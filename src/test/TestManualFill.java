package test;

import car.Car;
import fill.ManualFill;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class TestManualFill {
    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        InputStream originalIn = System.in;

        testCorrectInput();
        testEmptyModel();
        testNonNumericPower();
        testNegativePower();
        testYearOutOfRange();
        testZeroLength();

        System.setIn(originalIn);
        System.out.println("Пройдено: " + passed);
        System.out.println("Упало: " + failed);
    }

    private static void testCorrectInput() {
        setInput("Toyota\n150\n2020\nBMW\n200\n2021\n");

        List<Car> result = new ManualFill().fill(2);

        if (result.size() == 2
                && result.get(0).getModel().equals("Toyota")
                && result.get(0).getPower() == 150
                && result.get(0).getYear() == 2020
                && result.get(1).getModel().equals("BMW")
                && result.get(1).getPower() == 200
                && result.get(1).getYear() == 2021) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testCorrectInput: получено " + result);
        }
    }

    private static void testEmptyModel() {
        setInput("\n\nToyota\n150\n2020\n");

        List<Car> result = new ManualFill().fill(1);

        if (result.size() == 1 && result.get(0).getModel().equals("Toyota")) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testEmptyModel: получено " + result);
        }
    }

    private static void testNonNumericPower() {
        setInput("Toyota\nabc\n150\n2020\n");

        List<Car> result = new ManualFill().fill(1);

        if (result.size() == 1 && result.get(0).getPower() == 150) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testNonNumericPower: получено " + result);
        }
    }

    private static void testNegativePower() {
        setInput("Toyota\n-5\n150\n2020\n");

        List<Car> result = new ManualFill().fill(1);

        if (result.size() == 1 && result.get(0).getPower() == 150) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testNegativePower: получено " + result);
        }
    }

    private static void testYearOutOfRange() {
        setInput("Toyota\n150\n1800\n2020\n");

        List<Car> result = new ManualFill().fill(1);

        if (result.size() == 1 && result.get(0).getYear() == 2020) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testYearOutOfRange: получено " + result);
        }
    }

    private static void testZeroLength() {
        setInput("");

        List<Car> result = new ManualFill().fill(0);

        if (result.isEmpty()) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL testZeroLength: ожидался пустой список, получено " + result.size());
        }
    }

    private static void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}