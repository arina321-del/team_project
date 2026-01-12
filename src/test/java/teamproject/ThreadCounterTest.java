package teamproject;

public class ThreadCounterTest {

    public static void main(String[] args) {
        testUserCountMoreThanOnce();
        testUserNotFound();
        testSingleElementList();
    }

    private static void testUserCountMoreThanOnce() {
        CustomList<User> users = new CustomList<>();

        User user1 = new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build();

        User user2 = new User.Builder()
                .name("Boris2")
                .email("boris2@gmail.com")
                .password("Rt5$vN2x")
                .build();

        users.add(user1);
        users.add(user2);
        users.add(user1);
        users.add(user1);

        ThreadCounter counter = new ThreadCounter();
        int result = counter.getCountElement(users, user1);

        if (result == 3) {
            System.out.println("testUserCountMoreThanOnce PASSED");
        } else {
            System.out.println("testUserCountMoreThanOnce FAILED: expected 3, got " + result);
        }
    }

    private static void testUserNotFound() {
        CustomList<User> users = new CustomList<>();

        User existingUser = new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build();

        User notExistingUser = new User.Builder()
                .name("Olga1987")
                .email("olga1987@mail.ru")
                .password("Kf4#yU9c")
                .build();

        users.add(existingUser);

        ThreadCounter counter = new ThreadCounter();
        int result = counter.getCountElement(users, notExistingUser);

        if (result == 0) {
            System.out.println("testUserNotFound PASSED");
        } else {
            System.out.println("testUserNotFound FAILED: expected 0, got " + result);
        }
    }

    private static void testSingleElementList() {
        CustomList<User> users = new CustomList<>();

        User user = new User.Builder()
                .name("Veniamin")
                .email("veniamin@gmail.com")
                .password("Hj5@rT2k")
                .build();

        users.add(user);

        ThreadCounter counter = new ThreadCounter();
        int result = counter.getCountElement(users, user);

        if (result == 1) {
            System.out.println("testSingleElementList PASSED");
        } else {
            System.out.println("testSingleElementList FAILED: expected 1, got " + result);
        }
    }
}
