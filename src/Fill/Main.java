package Fill;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Integer, FillStrategy> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(1, new ManualFill());
        STRATEGIES.put(2, new FileFill());
        STRATEGIES.put(3, new RandomFill());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            /* Шаблон если нам будет нужен выход из заполнения массива
            int action = showMainMenu(scanner);
            if (action == 2) {
                System.out.println("Выход из программы.");
                break;
            }
            */
            System.out.println("Введите длину массива:");
            int length = setLength(scanner);
            System.out.println("Выберите способ заполнения: 1 - вручную, 2 - из файла, 3 - рандомно");
            int strategy = setStrategy(scanner);
            CarFiller filler = new CarFiller(STRATEGIES.get(strategy));
            filler.fill(length);
        }
    }

    private static int showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("Выберите дальнейший шаг: 1 - заполнить массив, 2 - выйти");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 2) {
                    return choice;
                }
                System.out.println("Введите число от 1 до 2");
            } else {
                scanner.next();
                System.out.println("Введите число от 1 до 2");
            }
        }
    }

    private static int setLength(Scanner scanner) {
        while(true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Введите число");
            }
        }
    }

    private static int setStrategy(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int strategy = scanner.nextInt();
                if (strategy >= 1 && strategy <= 3) {
                    return strategy;
                } else {
                    System.out.println("Неверное значение. Введите число от 1 до 3");
                }
            } else {
                scanner.next();
                System.out.println("Введите число от 1 до 3");
            }
        }
    }
}
