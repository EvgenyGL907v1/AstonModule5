package menu;

import car.Car;
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
            printMainMenu();
            int choice = readChoice(0, 5);

            switch (choice) {
                case 1 -> createCars();
                case 2 -> showCars();
                case 3 -> sortCarsMenu();
                case 4 -> findCarMenu();
                case 5 -> clearCars();
                case 0 -> {
                    System.out.println("Выход.");
                    return;
                }
            }

            pause();
        }
    }

    private void printMainMenu() {
        System.out.println("""
                Меню:
                1 - создать список автомобилей
                2 - показать список автомобилей
                3 - отсортировать список
                4 - найти автомобиль
                5 - очистить список
                0 - выход
                
                Выберите пункт:\s""");
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
        if (ensureNotEmpty()) {
            service.getCars().forEach(System.out::println);
        }
    }

    private void sortCarsMenu() {
        if (!ensureNotEmpty()) {
            return;
        }
        System.out.println("Сортировать по: 1 - мощность, 2 - модель, 3 - год, 0 - вернуться в меню");
        int choice = readChoice(0, 3);
        if (choice == 0) {
            return;
        }
        service.sort(choice);
        System.out.println("Список отсортирован. Результат:");
        service.getCars().forEach(System.out::println);
    }

    private void findCarMenu() {
        if (!ensureNotEmpty()) {
            return;
        }
        System.out.println("Введите модель для поиска:");
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

    /** Возвращает true, если список не пуст; иначе печатает сообщение. */
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

    private void pause() {
        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
        System.out.println();
    }

    private int readChoice(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Введите число от " + min + " до " + max);
            } else if (scanner.hasNext()) {
                scanner.next();
                System.out.println("Введите число от " + min + " до " + max);
            } else {
                throw new IllegalStateException("Входной поток завершён");
            }
        }
    }
}