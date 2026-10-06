package menu;

import car.Car;
import carcounter.CarCounter;
import file.CarFileWriter;
import fill.FillMain;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final CarService service;
    private final Scanner scanner;

    public Menu(CarService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Menu(new CarService(), scanner).run();
        }
    }

    public void run() {
        while (true) {
            System.out.println();
            printMainMenu();

            System.out.print("Выберите пункт: ");
            int choice = readChoice(0, 6);

            switch (choice) {
                case 1 -> showCars();
                case 2 -> createCars();
                case 3 -> sortCarsMenu();
                case 4 -> findCarMenu();
                case 5 -> clearCars();
                case 6 -> saveToFileMenu();
                case 0 -> {
                    System.out.println("Выход.");
                    return;
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("""
                ============ Меню ============
                1 - показать список автомобилей
                2 - создать список автомобилей
                3 - отсортировать список
                4 - найти все вхождения автомобиля
                5 - очистить список
                6 - сохранить коллекцию в файл
                0 - выход""");
    }

    private void createCars() {
        List<Car> created = FillMain.fillMain(scanner);
        if (created == null) {
            System.out.println("Создание отменено.");
            return;
        }
        service.setCars(created);
        System.out.println("Список создан. Элементов: " + service.size());
    }

    private void showCars() {
        System.out.println("===== Список автомобилей =====");
        if (ensureNotEmpty()) {
            service.getCars().forEach(System.out::println);
        }
    }

    private void sortCarsMenu() {
        if (!ensureNotEmpty()) {
            return;
        }

        System.out.print("""
                Выберите тип сортировки:
                1 - обычная (по всем элементам)
                2 - только чётные значения
                0 - вернуться в меню
                Выберите пункт: """);
        int sortType = readChoice(0, 2);
        if (sortType == 0) {
            return;
        }

        if (sortType == 1) {
            regularSortMenu();
        } else {
            evenSortMenu();
        }
    }

    private void regularSortMenu() {
        System.out.print("Сортировать по: 1 - модель, 2 - мощность, 3 - год, 0 - вернуться в меню: ");
        int choice = readChoice(0, 3);
        if (choice == 0) {
            return;
        }

        System.out.print("Выберите направление: 1 - по возрастанию, 2 - по убыванию, 0 - вернуться в меню: ");
        int direction = readChoice(0, 2);
        if (direction == 0) {
            return;
        }

        service.sort(choice, direction);
        System.out.println("Список отсортирован.");
    }

    private void evenSortMenu() {
        System.out.print("Сортировать чётные по: 2 - мощность, 3 - год, 0 - вернуться в меню: ");
        int choice = readChoice(0, 3);
        if (choice == 0) {
            return;
        }
        if (choice == 1) {
            System.out.println("Модель — не числовое поле, сортировка чётных невозможна.");
            return;
        }

        service.sortEven(choice);
        String fieldName = (choice == 2) ? "мощности" : "годы";
        System.out.println("Список отсортирован (только чётные " + fieldName + ").");
    }

    private void findCarMenu() {
        if (!ensureNotEmpty()) {
            return;
        }

        System.out.print("Введите модель для поиска (или 0 для выхода): ");
        String model = scanner.nextLine().trim();
        if (model.equals("0")) {
            return;
        }

        System.out.print("Введите мощность для поиска (или 0 для выхода): ");
        String powerLine = scanner.nextLine().trim();
        if (powerLine.equals("0")) {
            return;
        }

        System.out.print("Введите год для поиска (или 0 для выхода): ");
        String yearLine = scanner.nextLine().trim();
        if (yearLine.equals("0")) {
            return;
        }

        Car sample;
        try {
            int power = Integer.parseInt(powerLine);
            int year = Integer.parseInt(yearLine);

            sample = new Car.Builder()
                    .setModel(model)
                    .setPower(power)
                    .setYear(year)
                    .build();
        } catch (NumberFormatException e) {
            System.out.println("Мощность и год должны быть целыми числами.");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Выполняется подсчет вхождений для " + sample);
        System.out.println("Количество вхождений: " + CarCounter.countMatches(service.getCars(), sample));
    }

    private void clearCars() {
        service.clear();
        System.out.println("Список очищен.");
    }

    private void saveToFileMenu() {

        if (!ensureNotEmpty()) {
            return;
        }

        System.out.print("Введите путь к файлу для сохранения (или 0 для выхода): ");
        String path = scanner.nextLine().trim();
        if (path.equals("0")) {
            return;
        }

        if (path.isEmpty()) {
            System.out.println("Путь не может быть пустым.");
            return;
        }

        CarFileWriter.appendCarsToFile(path, service.getCars());
    }

    private boolean ensureNotEmpty() {
        if (!service.isCreated()) {
            System.out.println("Список ещё не создан.");
            return false;
        }
        if (service.isEmpty()) {
            System.out.println("Список пуст.");
            return false;
        }
        return true;
    }

    private int readChoice(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.print("Введите число от " + min + " до " + max + ": ");
            } else if (scanner.hasNext()) {
                scanner.next();
                System.out.print("Введите число от " + min + " до " + max + ": ");
            } else {
                throw new IllegalStateException("Входной поток завершён");
            }
        }
    }
}