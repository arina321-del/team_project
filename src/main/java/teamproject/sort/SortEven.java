package teamproject.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import teamproject.utils.CustomList;

public class SortEven {

	public static <T> void sort(CustomList<T> list, Function<T, String> sortCriteria) {

		CustomList<T> evenElements = new CustomList<>();
		List<Integer> evenElementsIndex = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			T object = list.get(i);
			int length = sortCriteria.apply(object).length();
			if (length % 2 == 0) {
				evenElements.add(object);
				evenElementsIndex.add(i);
			}
		}

		Sort.sort(evenElements, Comparator.comparing(sortCriteria, Comparator.comparingInt(String::length)));

		for (int i = 0; i < evenElementsIndex.size(); i++) {
			list.set(evenElementsIndex.get(i), evenElements.get(i));
		}
	}

}
