package teamproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SortEven {

	public static void sort(CustomList<User> list, Function<User, String> sortCriteria) {
		Comparator<User> comparator = Comparator.comparing(sortCriteria, Comparator.comparingInt(String::length));
		CustomList<User> evenElements = new CustomList<>();
		List<Integer> evenElementsIndex = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i);
			int length = sortCriteria.apply(user).length();
			if (length % 2 == 0) {
				evenElements.add(user);
				evenElementsIndex.add(i);
			}
		}

		Sort.sort(evenElements, comparator);

		for (int i = 0; i < evenElementsIndex.size(); i++) {
			list.set(evenElementsIndex.get(i), evenElements.get(i));
		}
	}

}
