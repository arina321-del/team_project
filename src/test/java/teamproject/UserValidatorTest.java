package teamproject;

public class UserValidatorTest {

    public static void main(String[] args) {

        testValidateNameNull();
        testValidateNameEmpty();
        testValidateNameInvalid();
        testValidateNameValid();

        testValidateEmailNull();
        testValidateEmailEmpty();
        testValidateEmailInvalid();
        testValidateEmailValid();

        testValidatePasswordNull();
        testValidatePasswordShort();
        testValidatePasswordNoDigit();
        testValidatePasswordValid();

        testValidateUserNull();
        testValidateUserValid();
        testValidateUserInvalidEmail();
    }

    private static void testValidateNameNull() {
        try {
            UserValidator.validateName(null);
            System.out.println("testValidateNameNull FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateNameNull PASSED");
        }
    }

    private static void testValidateNameEmpty() {
        try {
            UserValidator.validateName("   ");
            System.out.println("testValidateNameEmpty FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateNameEmpty PASSED");
        }
    }

    private static void testValidateNameInvalid() {
        try {
            UserValidator.validateName("Max@Power");
            System.out.println("testValidateNameInvalid FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateNameInvalid PASSED");
        }
    }

    private static void testValidateNameValid() {
        try {
            UserValidator.validateName("MaxPower");
            System.out.println("testValidateNameValid PASSED");
        } catch (ValidationException e) {
            System.out.println("testValidateNameValid FAILED");
        }
    }

    private static void testValidateEmailNull() {
        try {
            UserValidator.validateEmail(null);
            System.out.println("testValidateEmailNull FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateEmailNull PASSED");
        }
    }

    private static void testValidateEmailEmpty() {
        try {
            UserValidator.validateEmail("   ");
            System.out.println("testValidateEmailEmpty FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateEmailEmpty PASSED");
        }
    }

    private static void testValidateEmailInvalid() {
        try {
            UserValidator.validateEmail("maxpower@@gmail.com");
            System.out.println("testValidateEmailInvalid FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateEmailInvalid PASSED");
        }
    }

    private static void testValidateEmailValid() {
        try {
            UserValidator.validateEmail("maxpower@gmail.com");
            System.out.println("testValidateEmailValid PASSED");
        } catch (ValidationException e) {
            System.out.println("testValidateEmailValid FAILED");
        }
    }

    private static void testValidatePasswordNull() {
        try {
            UserValidator.validatePassword(null);
            System.out.println("testValidatePasswordNull FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidatePasswordNull PASSED");
        }
    }

    private static void testValidatePasswordShort() {
        try {
            UserValidator.validatePassword("123");
            System.out.println("testValidatePasswordShort FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidatePasswordShort PASSED");
        }
    }

    private static void testValidatePasswordNoDigit() {
        try {
            UserValidator.validatePassword("Password");
            System.out.println("testValidatePasswordNoDigit FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidatePasswordNoDigit PASSED");
        }
    }

    private static void testValidatePasswordValid() {
        try {
            UserValidator.validatePassword("Qr2$vL7n");
            System.out.println("testValidatePasswordValid PASSED");
        } catch (ValidationException e) {
            System.out.println("testValidatePasswordValid FAILED");
        }
    }

    private static void testValidateUserNull() {
        try {
            UserValidator.validate(null);
            System.out.println("testValidateUserNull FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateUserNull PASSED");
        }
    }

    private static void testValidateUserValid() {
        try {
            User user = new User.Builder()
                    .name("MaxPower")
                    .email("maxpower@gmail.com")
                    .password("Qr2$vL7n")
                    .build();

            UserValidator.validate(user);
            System.out.println("testValidateUserValid PASSED");
        } catch (ValidationException e) {
            System.out.println("testValidateUserValid FAILED");
        }
    }

    private static void testValidateUserInvalidEmail() {
        try {
            User user = new User.Builder()
                    .name("MaxPower")
                    .email("email")
                    .password("Qr2$vL7n")
                    .build();

            UserValidator.validate(user);
            System.out.println("testValidateUserInvalidEmail FAILED");
        } catch (ValidationException e) {
            System.out.println("testValidateUserInvalidEmail PASSED");
        }
    }
}
