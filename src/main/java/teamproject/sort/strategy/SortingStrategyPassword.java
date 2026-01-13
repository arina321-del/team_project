package teamproject;

import java.util.Comparator;

public class SortingStrategyPassword implements SortingStrategy{

    private static final Comparator<User> COMPARATOR =
            Comparator.comparing(User::getPassword);

    @Override
    public void sort(CustomList<User> list) {
        Sort.sort(list, COMPARATOR);
    }
}
