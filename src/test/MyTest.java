package test;

public class MyTest {

    protected static int passed = 0;
    protected static int failed = 0;
    protected static int localPassed = 0;
    protected static int localFailed = 0;

    protected static void check(boolean cond, String name) {
        if (cond) {
            passed++;
            localPassed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            localFailed++;
            System.out.println("FAIL: " + name);
        }
    }

    protected static void result() {
        System.out.println("Пройдено: " + localPassed);
        System.out.println("Упало: " + localFailed);
        localPassed = 0;
        localFailed = 0;
    }

    public static void main(String[] args) {
        System.out.println("TestCar");
        TestCar.main(new String[0]);
        System.out.println();

        System.out.println("TestCarCounter");
        TestCarCounter.main(new String[0]);
        System.out.println();

        System.out.println("TestCarFileWriter");
        TestCarFileWriter.main(new String[0]);
        System.out.println();

        System.out.println("TestCarFiller");
        TestCarFiller.main(new String[0]);
        System.out.println();

        System.out.println("TestCarService");
        TestCarService.main(new String[0]);
        System.out.println();

        System.out.println("TestCarSorter");
        TestCarSorter.main(new String[0]);
        System.out.println();

        System.out.println("TestCustomArrayList");
        TestCustomArrayList.main(new String[0]);
        System.out.println();

        System.out.println("TestCustomCollectors");
        TestCustomCollectors.main(new String[0]);
        System.out.println();

        System.out.println("TestEvenCarSorter");
        TestEvenCarSorter.main(new String[0]);
        System.out.println();

        System.out.println("TestFileFill");
        TestFileFill.main(new String[0]);
        System.out.println();

        System.out.println("TestModelComparator");
        TestModelComparator.main(new String[0]);
        System.out.println();

        System.out.println("TestPowerComparator");
        TestPowerComparator.main(new String[0]);
        System.out.println();

        System.out.println("TestYearComparator");
        TestYearComparator.main(new String[0]);
        System.out.println();

        System.out.println("Итоговый результат");
        System.out.println("Пройдено: " + passed);
        System.out.println("Упало: " + failed);
    }
}
