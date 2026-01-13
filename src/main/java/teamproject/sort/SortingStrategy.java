package teamproject.sort;

import teamproject.entities.User;
import teamproject.utils.CustomList;

public interface SortingStrategy {
    void sort(CustomList<User> list);
}
