package test;

import car.Car;
import fill.CarFiller;
import fill.FillStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestCarFiller extends MyTest {

    public static void main(String[] args) {
        testDelegatesToStrategy();
        testPassesLengthToStrategy();
        testSetStrategyChangesBehavior();
        testReturnsStrategyResult();

        result();
    }

    private static void testDelegatesToStrategy() {
        FillStrategy stub = length -> {
            List<Car> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                list.add(new Car.Builder().setModel("M" + i).setPower(100).setYear(2020).build());
            }
            return list;
        };

        CarFiller filler = new CarFiller(stub);
        List<Car> result = filler.fill(3);
        check(result.size() == 3, "testDelegatesToStrategy");
    }

    private static void testPassesLengthToStrategy() {
        final int[] captured = {-1};
        FillStrategy stub = length -> {
            captured[0] = length;
            return new ArrayList<>();
        };

        new CarFiller(stub).fill(42);
        check(captured[0] == 42, "testPassesLengthToStrategy");
    }

    private static void testSetStrategyChangesBehavior() {
        FillStrategy first = length -> new ArrayList<>();
        FillStrategy second = length -> {
            List<Car> list = new ArrayList<>();
            list.add(new Car.Builder().setModel("X").setPower(100).setYear(2020).build());
            return list;
        };

        CarFiller filler = new CarFiller(first);
        boolean firstEmpty = filler.fill(5).isEmpty();

        filler.setStrategy(second);
        boolean secondHasOne = filler.fill(5).size() == 1;

        check(firstEmpty && secondHasOne, "testSetStrategyChangesBehavior");
    }

    private static void testReturnsStrategyResult() {
        List<Car> expected = new ArrayList<>();
        expected.add(new Car.Builder().setModel("Z").setPower(120).setYear(2019).build());
        FillStrategy stub = length -> expected;

        CarFiller filler = new CarFiller(stub);
        check(filler.fill(1) == expected, "testReturnsStrategyResult");
    }
}
