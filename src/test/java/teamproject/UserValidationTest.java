package teamproject;

public class UserValidationTest {

    public static void main(String[] args) {

        testValidUser();
        testInvalidName();
        testInvalidEmail();
        testInvalidPassword();

        System.out.println("UserValidationTest FINISHED");
    }

    private static void testValidUser() {
        try {
            new User.Builder()
                    .name("MaxPower")
                    .email("maxpower@gmail.com")
                    .password("Qr2$vL7n")
                    .build();

            System.out.println("testValidUser PASSED");
        } catch (Exception e) {
            System.out.println("testValidUser FAILED");
            e.printStackTrace();
        }
    }

    private static void testInvalidName() {
        try {
            new User.Builder()
                    .name("!!!@@@")
                    .email("maxpower@gmail.com")
                    .password("Qr2$vL7n")
                    .build();

            System.out.println("testInvalidName FAILED");
        } catch (ValidationException e) {
            System.out.println("testInvalidName PASSED");
        }
    }

    private static void testInvalidEmail() {
        try {
            new User.Builder()
                    .name("MaxPower")
                    .email("maxpower@@gmail.com")
                    .password("Qr2$vL7n")
                    .build();

            System.out.println("testInvalidEmail FAILED");
        } catch (ValidationException e) {
            System.out.println("testInvalidEmail PASSED");
        }
    }

    private static void testInvalidPassword() {
        try {
            new User.Builder()
                    .name("MaxPower")
                    .email("maxpower@gmail.com")
                    .password("123")
                    .build();

            System.out.println("testInvalidPassword FAILED");
        } catch (ValidationException e) {
            System.out.println("testInvalidPassword PASSED");
        }
    }
}
