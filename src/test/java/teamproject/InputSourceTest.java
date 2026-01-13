package teamproject;

public class InputSourceTest {

    public static void main(String[] args) {
        System.out.println("===== InputSourceTest =====");

        testRandomInputSource();
        testFileInputSource();
        testManualInputSource();

        System.out.println("===== InputSourceTest finished =====");
    }

    private static void testRandomInputSource() {
        System.out.println("\n[TEST] InputSourceRandom");

        InputSource source = new InputSourceRandom();
        int size = 5;

        CustomList<User> users = source.provide(size);

        basicChecks(users, size);

        System.out.println("InputSourceRandom PASSED");
    }

    private static void testFileInputSource() {
        System.out.println("\n[TEST] InputSourceFile");

        // путь к тестовому файлу
        InputSource source = new InputSourceFile("users_test.txt");
        int size = 3;

        CustomList<User> users = source.provide(size);

        basicChecks(users, size);

        System.out.println("InputSourceFile PASSED");
    }

    private static void testManualInputSource() {
        System.out.println("\n[TEST] InputSourceManual");
        System.out.println("Этот тест требует ручного ввода данных");

        InputSource source = new InputSourceManual();
        int size = 1;

        CustomList<User> users = source.provide(size);

        basicChecks(users, size);

        System.out.println("InputSourceManual PASSED");
    }

    private static void basicChecks(CustomList<User> users, int expectedSize) {
        if (users == null) {
            throw new RuntimeException("FAIL: returned list is null");
        }

        if (users.size() != expectedSize) {
            throw new RuntimeException(
                    "FAIL: expected size = " + expectedSize + ", actual = " + users.size()
            );
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user == null) {
                throw new RuntimeException("FAIL: user at index " + i + " is null");
            }

            UserValidator.validate(user);
        }
    }
}
