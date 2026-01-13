package teamproject.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import static teamproject.utils.Constants.SPLIT_LIMIT;
import static teamproject.utils.Constants.SPLIT_SEPARATOR;

import teamproject.entities.User;
import teamproject.utils.CustomList;
import teamproject.validations.ValidationException;

public class InputSourceFile implements InputSource {
	private final String filePath;

	public InputSourceFile(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public CustomList<User> provide(int size) {
		CustomList<User> list = new CustomList<>();

		try (Stream<String> lines = Files.lines(Path.of(filePath))) {
			lines.limit(size).map(this::parseLine).forEach(list::add);
		} catch (IOException e) {
			throw new RuntimeException("Не удалось прочитать файл: " + filePath, e);
		}

		return list;
	}

	private User parseLine(String line) {
		String[] parts = line.split(SPLIT_SEPARATOR, SPLIT_LIMIT);
		if (parts.length != SPLIT_LIMIT) {
			throw new ValidationException("Неверный формат строки: '" + line + "' (ожидается: имя;email;пароль)");
		}

		String name = parts[0].trim();
		String email = parts[1].trim();
		String password = parts[2].trim();

		return new User.Builder().name(name).email(email).password(password).build();
	}
}
