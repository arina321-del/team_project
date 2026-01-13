package teamproject.ui;

import java.util.Scanner;

import teamproject.entities.User;
import teamproject.sort.SortingStrategy;
import teamproject.sort.strategy.SortEvenStrategyEmail;
import teamproject.sort.strategy.SortEvenStrategyName;
import teamproject.sort.strategy.SortEvenStrategyPassword;
import teamproject.sort.strategy.SortingStrategyContext;
import teamproject.sort.strategy.SortingStrategyEmail;
import teamproject.sort.strategy.SortingStrategyName;
import teamproject.sort.strategy.SortingStrategyPassword;
import teamproject.source.InputSource;
import teamproject.source.InputSourceFile;
import teamproject.source.InputSourceManual;
import teamproject.source.InputSourceRandom;
import teamproject.utils.CustomList;
import teamproject.utils.FileHandler;
import teamproject.utils.ThreadCounter;

import static teamproject.utils.Constants.*;

public final class ConsoleUI {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String FILE_NAME = "src/resources/data.txt";

	private ConsoleUI() {
	}

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
		System.out.println("\n" + REAPETER.repeat(REAPET_NUMBER));
		System.out.println("  СИСТЕМА СОРТИРОВКИ ПОЛЬЗОВАТЕЛЕЙ");
		System.out.println(REAPETER.repeat(REAPET_NUMBER));
		System.out.println("1. Загрузить данные");
		System.out.println("2. Отсортировать и вывести");
		System.out.println("3. Искать кол-во повторений пользователя в списке");
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

	private static void showChooseCriterion() {
		System.out.println("\n Выберите стратегию сортировки:");
		System.out.println("1. По имени");
		System.out.println("2. По email");
		System.out.println("3. По паролю");
		System.out.print("Ваш выбор: ");
	}

	public static void writeInFile(CustomList<User> users) {
		System.out.println("Загрузить данные в файл?");
		System.out.println("1. Да");
		System.out.println("Или введите любую цифру, чтобы выйти ...");
		System.out.print("Ваш выбор: ");

		if (readInt() == USER_CHOISE_ONE) {
			FileHandler.appendResults(users);
		} else {
			return;
		}

	}

	private static SortingStrategy getCriterion(SortingStrategy nameCriterion, SortingStrategy emailCriterion,
			SortingStrategy passwordCriterion) {

		showChooseCriterion();

		SortingStrategy strategy = switch (readInt()) {
		case 1 -> nameCriterion;
		case 2 -> emailCriterion;
		case 3 -> passwordCriterion;
		default -> {
			System.out.println("Выбрана базовая стратегия.");
			yield nameCriterion;
		}
		};
		return strategy;
	}

	public static SortingStrategy chooseSortStrategy(CustomList<User> list) {

		System.out.println("\n Выберите стратегию сортировки:");
		System.out.println("1. Сортировка четных значений длин");
		System.out.println("2. Сортировка стандартная");
		System.out.print("Ваш выбор: ");

		SortingStrategy strategy = switch (readInt()) {
		case 1 -> getCriterion(new SortEvenStrategyName(), new SortEvenStrategyEmail(), new SortEvenStrategyPassword());
		case 2 -> getBasicCriterion();
		default -> {
			System.out.println(" Выбрана базовая стратегия.");
			yield getBasicCriterion();
		}
		};

		return strategy;
	}

	private static SortingStrategy getBasicCriterion() {
		return getCriterion(new SortingStrategyName(), new SortingStrategyEmail(), new SortingStrategyPassword());
	}

	public static void sort(CustomList<User> users) {
		SortingStrategyContext strategyContext = new SortingStrategyContext(chooseSortStrategy(users));
		try {
			strategyContext.sort(users);
		} catch (Exception e) {
			System.err.println("Ошибка сортировки: " + e.getMessage());
		}

	}

	public static void showUsers(String msg, CustomList<User> users) {
		System.out.println(msg);
		printUsers(users);
	}

	private static InputSource chooseInputSource(int choise) {
		return switch (choise) {
		case 1 -> new InputSourceManual();
		case 2 -> new InputSourceRandom();
		case 3 -> new InputSourceFile(FILE_NAME);
		default -> {
			System.out.println("Выбран ввод из файла.");
			yield new InputSourceFile(FILE_NAME);
		}
		};
	}

	public static User inputUser() {
		CustomList<User> user = fillUsers(new CustomList<User>(), 1, USER_CHOISE_ONE);
		return user.get(0);
	}

	public static int getCountUsers(CustomList<User> users, User user) {
		return new ThreadCounter().getCountElement(users, user);
	}

	public static CustomList<User> fillUsers(CustomList<User> users, int size) {
		return fillUsers(users, size, readInt());
	}

	public static CustomList<User> fillUsers(CustomList<User> users, int size, int choise) {
		try {
			users = ConsoleUI.chooseInputSource(choise).provide(size);
			System.out.println("Загружено " + users.size() + " пользователей.");
		} catch (Exception e) {
			System.err.println(" Ошибка при загрузке: " + e.getMessage());
			users = new CustomList<>();
		}
		return users;
	}

	public static void printUsers(CustomList<User> list) {
		if (list.isEmpty()) {
			System.out.println("Список пользователей пуст.");
			return;
		}

		System.out.println("\n" + MINUS_REAPETER.repeat(REAPET_NUMBER));
		System.out.printf(OUTPUT_USERS_FORMAT, "ИМЯ", "EMAIL", "ПАРОЛЬ");
		System.out.println(MINUS_REAPETER.repeat(REAPET_NUMBER));

		list.stream().forEach(System.out::println);

		System.out.println(MINUS_REAPETER.repeat(REAPET_NUMBER));
		System.out.println("Всего: " + list.size() + " пользователей.");
	}

	public static void pause() {
		System.out.print("\nНажмите Enter для продолжения...");
		SCANNER.nextLine();
	}

	public static void clearScreen() {
		System.out.print(CLEAR_DISPLAY);
		System.out.flush();
	}
}
