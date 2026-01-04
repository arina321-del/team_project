package teamproject;

import java.util.Scanner;

public final class ConsoleUI {
    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleUI() {}

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Некорректное число. Попробуйте снова.");
            }
        }
    }

    public static int readInt() {
        return readInt("");
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    public static void showMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  СИСТЕМА СОРТИРОВКИ ПОЛЬЗОВАТЕЛЕЙ");
        System.out.println("=".repeat(50));
        System.out.println("1. Загрузить данные");
        System.out.println("2. Отсортировать и вывести");
        System.out.println("0. Выйти");
        System.out.print("Ваш выбор: ");
    }

    public static void showDataSourceMenu() {
        System.out.println("\n Источник данных:");
        System.out.println("1. Ручной ввод");
        System.out.println("2. Случайные данные");
        System.out.println("3. Из файла (data.txt)");
        System.out.print("Ваш выбор: ");
    }

    public static void sortAndPrint(CustomList<User> list) {
        if (list.isEmpty()) {
            System.out.println(" Нет данных для сортировки. Сначала загрузите пользователей.");
            return;
        }

        System.out.println("\n Выберите стратегию сортировки:");
        System.out.println("1. По имени");
        System.out.println("2. По email");
        System.out.println("3. По паролю");
        System.out.print("Ваш выбор: ");

        SortingStrategy strategy = switch (readInt()) {
            case 1 -> new SortingStrategyName();
            case 2 -> new SortingStrategyEmail();
            case 3 -> new SortingStrategyPassword();
            default -> {
                System.out.println(" Выбрана базовая стратегия.");
                yield new SortingStrategyName();
            }
        };

        System.out.println("\nДо сортировки:");
        printUsers(list);

        try {
            strategy.sort(list);
            System.out.println("\nПосле сортировки:");
            printUsers(list);
        } catch (Exception e) {
            System.err.println("Ошибка сортировки: " + e.getMessage());
        }
    }

    public static InputSource chooseInputSource() {
        showDataSourceMenu();
        return switch (readInt()) {
            case 1 -> new InputSourceManual();
            case 2 -> new InputSourceRandom();
            case 3 -> new InputSourceFile("data.txt");
            default -> {
                System.out.println(" Выбран ввод из файла.");
                yield new InputSourceFile("data.txt");
            }
        };
    }

    public static void printUsers(CustomList<User> list) {
        if (list.isEmpty()) {
            System.out.println("Список пользователей пуст.");
            return;
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.printf("%-20s %-25s %s%n", "ИМЯ", "EMAIL", "ПАРОЛЬ");
        System.out.println("-".repeat(60));

        list.stream()
                .forEach(u -> System.out.printf(
                        "%-20s %-25s %s%n",
                        u.getName(),
                        u.getEmail(),
                        maskPassword(u.getPassword())
                ));

        System.out.println("-".repeat(60));
        System.out.println("Всего: " + list.size() + " пользователей.");
    }

    private static String maskPassword(String pwd) {
        return pwd.length() <= 2 ? pwd : "*".repeat(pwd.length() - 2) + pwd.substring(pwd.length() - 2);
    }

    public static void pause() {
        System.out.print("\nНажмите Enter для продолжения...");
        SCANNER.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
