package teamproject;

public class FileHandlerTest {

    public static void main(String[] args) {
        System.out.println("===== FileHandlerTest =====");

        CustomList<User> users = new CustomList<>();

        users.add(new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build());

        users.add(new User.Builder()
                .name("Boris2")
                .email("boris2@gmail.com")
                .password("Rt5$vN2x")
                .build());

        FileHandler.appendResults(users);

        FileHandler.appendResults(users);

        System.out.println("===== FileHandlerTest finished =====");
    }
}
