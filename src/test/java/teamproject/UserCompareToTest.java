package teamproject;

public class UserCompareToTest {

    public static void main(String[] args) {

        User u1 = new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build();

        User u2 = new User.Builder()
                .name("Boris2")
                .email("boris2@gmail.com")
                .password("Rt5$vN2x")
                .build();

        int result = u1.compareTo(u2);

        if (result < 0) {
            System.out.println("UserCompareToTest PASSED");
        } else {
            System.out.println("UserCompareToTest FAILED");
        }
    }
}