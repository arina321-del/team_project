package teamproject;

import java.util.Objects;

public final class User implements Comparable<User> {

	private final String name;
	private final String password;
	private final String email;

	private User(String name, String password, String email) {
		this.name = Objects.requireNonNull(name, "Имя не может быть null");
		this.password = Objects.requireNonNull(password, "Пароль не может быть null");
		this.email = Objects.requireNonNull(email, "Email не может быть null");

		UserValidator.validate(this);
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(name, user.name) && Objects.equals(password, user.password)
				&& Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password, email);
	}

	@Override
	public String toString() {
		return String.format("%-20s %-25s %s%n", name, email, maskPassword());
	}
	
	private String maskPassword() {
		return password.length() <= 2 ? password : "*".repeat(password.length() - 2) + password.substring(password.length() - 2);
	}
	
	@Override
	public int compareTo(User other) {
		int cmp = this.name.compareToIgnoreCase(other.name);
		if (cmp != 0)
			return cmp;

		cmp = this.email.compareToIgnoreCase(other.email);
		if (cmp != 0)
			return cmp;

		return this.password.compareTo(other.password); // пароль — чувствителен к регистру (без ignoreCase)
	}

	public static class Builder {
		private String name;
		private String password;
		private String email;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public User build() {
			return new User(name, password, email);
		}
	}
}
