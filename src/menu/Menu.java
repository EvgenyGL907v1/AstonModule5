package menu;

import car.Car;
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
            showCars();
            printMainMenu();

            System.out.print("Выберите пункт: ");
            int choice = readChoice(0, 5);

            switch (choice) {
                case 1 -> createCars();
                case 2 -> sortCarsMenu();
                case 3 -> findCarMenu();
                case 4 -> clearCars();
                case 5 -> saveToFileMenu();
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
                1 - создать список автомобилей
                2 - отсортировать список
                3 - найти автомобиль
                4 - очистить список
                5 - сохранить коллекцию в файл
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

    private void findCarMenu() {
        if (!ensureNotEmpty()) {
            return;
        }
        System.out.print("Введите модель для поиска: ");
        String model = scanner.nextLine();

        List<Car> found = service.find(model);
        if (found.isEmpty()) {
            System.out.println("Автомобиль не найден.");
        } else {
            found.forEach(System.out::println);
        }
    }

    private void clearCars() {
        service.clear();
        System.out.println("Список очищен.");
    }

    private void saveToFileMenu() {

        if (!ensureNotEmpty()) {
            return;
        }

        System.out.print("Введите путь к файлу для сохранения: ");
        String path = scanner.nextLine().trim();

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