package teamproject;

public class SortingStrategyTest {

    public static void main(String[] args) {

        testStrategy(new SortingStrategyName(), "SortingStrategyName");
        testStrategy(new SortingStrategyEmail(), "SortingStrategyEmail");
        testStrategy(new SortingStrategyPassword(), "SortingStrategyPassword");

        System.out.println("SortingStrategyTest FINISHED");
    }

    private static void testStrategy(SortingStrategy strategy, String name) {

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

        list.add(new User.Builder()
                .name("Boris2")
                .email("boris2@gmail.com")
                .password("Rt5$vN2x")
                .build());

        strategy.sort(list);

        boolean passed =
                list.get(0).getName().equals("Ann251") &&
                        list.get(1).getName().equals("Bob") &&
                        list.get(2).getName().equals("Victoria");

        print(name, passed);
    }

    private static void print(String name, boolean passed) {
        System.out.println(name + (passed ? " PASSED" : " FAILED"));
    }
}
