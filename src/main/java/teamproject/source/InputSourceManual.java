package teamproject.source;

import java.util.Scanner;

import teamproject.entities.User;
import teamproject.utils.CustomList;
import teamproject.validations.UserValidator;
import teamproject.validations.ValidationException;

public class InputSourceManual implements InputSource {
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public CustomList<User> provide(int size) {
		CustomList<User> list = new CustomList<>();
		System.out.println("Ручной ввод " + size + " пользователя(ей):");

		for (int i = 0; i < size; i++) {
			System.out.println("\n--- Пользователь №" + (i + 1) + " ---");
			String name = readValidField("Имя: ", UserValidator::validateName);
			String email = readValidField("Email: ", UserValidator::validateEmail);
			String password = readValidField("Пароль (от 8 симв.): ", UserValidator::validatePassword);

			User user = new User.Builder().name(name).email(email).password(password).build();

			list.add(user);
		}

		return list;
	}

	private String readValidField(String prompt, java.util.function.Consumer<String> validator) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				validator.accept(input);
				return input;
			} catch (ValidationException e) {
				System.err.println("Ошибка: " + e.getMessage());
			}
		}
	}

}
