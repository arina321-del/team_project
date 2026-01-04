package teamproject;

import java.util.Comparator;

public class SortingStrategyEmail implements SortingStrategy{

    private static final Comparator<User> COMPARATOR =
            Comparator.comparing(User::getEmail, String.CASE_INSENSITIVE_ORDER);

    @Override
    public void sort(CustomList<User> list) {
        Sort.sort(list, COMPARATOR);
    }
}
