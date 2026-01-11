package teamproject;

public class SortingStrategyContextTest {

    public static void main(String[] args) {

        testContextWithNameStrategy();
        testContextWithEmailStrategy();

        System.out.println("SortingStrategyContextTest FINISHED");
    }

    private static void testContextWithNameStrategy() {

        SortingStrategyContext context =
                new SortingStrategyContext(new SortingStrategyName());

        CustomList<User> list = new CustomList<>();

        list.add(new User.Builder()
                .name("Victoria")
                .email("victoria@360.yandex.ru")
                .password("Wq8@zX3m")
                .build());

        list.add(new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build());

        context.sort(list);

        boolean passed = list.get(0).getName().equals("Ann251");

        print("Context + NameStrategy", passed);
    }

    private static void testContextWithEmailStrategy() {

        SortingStrategyContext context =
                new SortingStrategyContext(new SortingStrategyEmail());

        CustomList<User> list = new CustomList<>();

        list.add(new User.Builder()
                .name("Boris2")
                .email("boris2@gmail.com")
                .password("Rt5$vN2x")
                .build());

        list.add(new User.Builder()
                .name("Ann251")
                .email("ann251@mail.ru")
                .password("K7m#pL9q")
                .build());

        context.sort(list);

        boolean passed = list.get(0).getEmail().equals("ann251@mail.ru");

        print("Context + EmailStrategy", passed);
    }

    private static void print(String name, boolean passed) {
        System.out.println(name + (passed ? " PASSED" : " FAILED"));
    }
}