package teamproject;

public class Main {
	public static void main(String[] args) {
		CustomList<User> users = new CustomList<>();
		SortingStrategyContext strategyContext = new SortingStrategyContext();

		System.out.println("Добро пожаловать в систему сортировки пользователей!");
		ConsoleUI.pause();

		while (true) {
			ConsoleUI.showMainMenu();
			int choice = ConsoleUI.readInt();

			switch (choice) {
			case 1 -> {
				ConsoleUI.clearScreen();
				int size = ConsoleUI.readInt("Введите количество пользователей: ");
				if (size <= 0) {
					System.out.println("Количество должно быть > 0. Пропуск.");
					break;
				}

				var source = ConsoleUI.chooseInputSource();
				try {
					users = source.provide(size);
					System.out.println("Загружено " + users.size() + " пользователей.");
				} catch (Exception e) {
					System.err.println("Ошибка при загрузке: " + e.getMessage());
					users = new CustomList<>();
				}
				ConsoleUI.pause();
			}

			case 2 -> {
				ConsoleUI.clearScreen();
				if (users.isEmpty()) {
					System.out.println(" Нет данных для сортировки. Сначала загрузите пользователей.");
				} else {
					ConsoleUI.showUsers("\nДо сортировки:", users);
					ConsoleUI.sort(users);
					ConsoleUI.showUsers("\nПосле сортировки:", users);
					ConsoleUI.pause();
				}
			}

			case 0 -> {
				System.out.println("\nСпасибо за использование! До свидания.");
				return;
			}

			default -> {
				System.err.println("Неверный выбор. Попробуйте снова.");
				ConsoleUI.pause();
			}
			}
		}
	}
}
