package teamproject;

import java.util.regex.Pattern;

public final class UserValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[\\s\\-._'0-9а-яА-ЯёЁa-zA-Z]+$"
    );

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private UserValidator(){

    }

    public static void validate(User user) {
        if (user == null){
            throw new ValidationException("User не может быть null");
        }

        validateName(user.getName());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    public static void validateName(String name) {
        if (name == null) {
            throw new ValidationException("Имя не может быть null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new ValidationException("Имя не может быть пустым или состоять только из пробелов");
        }
        if (!NAME_PATTERN.matcher(trimmed).matches()) {
            throw new ValidationException(
                    "Имя должно начинаться с буквы и содержать только буквы, пробелы, дефисы и апострофы: '" + name + "'"
            );
        }
    }

    public static void validateEmail(String email) {
        if (email == null) {
            throw new ValidationException("Email не может быть null");
        }
        String trimmed = email.trim();
        if (trimmed.isEmpty()) {
            throw new ValidationException("Email не может быть пустым");
        }
        if (!EMAIL_PATTERN.matcher(trimmed).matches()) {
            throw new ValidationException("Некорректный формат email: '" + email + "'");
        }
    }

    public static void validatePassword(String password) {
        if (password == null) {
            throw new ValidationException("Пароль не может быть null");
        }
        if (password.length() < 8) {
            throw new ValidationException("Пароль должен содержать не менее 8 символов");
        }
        if (!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            throw new ValidationException("Пароль должен содержать хотя бы одну букву и одну цифру");
        }
    }
}
