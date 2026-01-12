package teamproject;

public class SortEvenStrategyTest {

    public static void main(String[] args) {

        testStrategy(new SortEvenStrategyName(), "SortEvenStrategyName");
        testStrategy(new SortEvenStrategyEmail(), "SortEvenStrategyEmail");
        testStrategy(new SortEvenStrategyPassword(), "SortEvenStrategyPassword");

        System.out.println("SortEvenStrategyTest FINISHED");
    }

    private static void testStrategy(SortEvenStrategy strategy, String name) {

        CustomList<User> list = new CustomList<>();

        User u1 = new User.Builder()
                .name("Svetlana_")
                .email("svetlana_@360.yandex.ru")
                .password("Tj6!wP5x")
                .build();

        User u2 = new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build();

        User u3 = new User.Builder()
                .name("Dmitry_89")
                .email("dmitry_89@mail.nic.ru")
                .password("Az9@hJ3m")
                .build();

        list.add(u1);
        list.add(u2);
        list.add(u3);

        strategy.sort(list);

        boolean passed =
                list.get(0) == u1 &&
                        list.get(1) == u2 &&
                        list.get(2) == u3;

        if (passed) {
            System.out.println(name + " PASSED");
        } else {
            System.out.println(name + " FAILED");
        }
    }
}
