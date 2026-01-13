package teamproject.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import teamproject.entities.User;

public class FileHandler {
	private static final String FILE_NAME = "src/resources/output_users.txt";

	public static void appendResults(CustomList<User> users) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			writer.write("=== Новая запись результатов (" + java.time.LocalDateTime.now() + ") ===\n");
			for (int i = 0; i < users.size(); i++) {
				writer.write(users.get(i).toString());
				writer.newLine();
			}
			writer.write("====================================================\n\n");
			System.out.println("Данные успешно добавлены в файл " + FILE_NAME);
		} catch (IOException e) {
			System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
		}
	}
}
