package teamproject.sort.strategy;

import java.util.Comparator;

import teamproject.entities.User;
import teamproject.sort.Sort;
import teamproject.sort.SortingStrategy;
import teamproject.utils.CustomList;

public class SortingStrategyEmail implements SortingStrategy{

    private static final Comparator<User> COMPARATOR =
            Comparator.comparing(User::getEmail, String.CASE_INSENSITIVE_ORDER);

    @Override
    public void sort(CustomList<User> list) {
        Sort.sort(list, COMPARATOR);
    }
}
