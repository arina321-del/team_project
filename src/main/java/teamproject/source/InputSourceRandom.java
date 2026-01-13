package teamproject;

import java.util.Random;
import java.util.stream.IntStream;

public class InputSourceRandom implements InputSource{

    private static final String[] NAMES = {"Ann", "Boris", "Victoria", "Gleb", "Daria", "Elena", "Veniamin", "Antonio", "Vladimir"};
    private static final String[] DOMAINS = {"mail.ru", "gmail.com", "360.yandex.ru", "mail.nic.ru", "mail.rumbler.ru"};
    private final Random random = new Random();

    @Override
    public CustomList<User> provide(int size) {
        CustomList<User> list = new CustomList<>();

        IntStream.range(0, size)
                .mapToObj(i -> generateRandomUser())
                .forEach(list::add);

        return list;
    }

    private User generateRandomUser() {
        String name = NAMES[random.nextInt(NAMES.length)] + (random.nextInt(100));
        String email = name.toLowerCase() + "@" + DOMAINS[random.nextInt(DOMAINS.length)];
        String password = "pass" + (100000 + random.nextInt(999999));

        return new User.Builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
