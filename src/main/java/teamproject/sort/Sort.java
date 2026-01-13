package teamproject.sort;

import java.util.Comparator;

import teamproject.utils.CustomList;

public final class Sort {

	private Sort() {
	}

	public static <T> void sort(CustomList<T> list, Comparator<T> comparator) {
		if (list == null || comparator == null) {
			throw new IllegalArgumentException("list и comparator не могут быть null");
		}

		int n = list.size();
		for (int i = 1; i < n; i++) {
			T key = list.get(i);
			int j = i - 1;

			while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
				list.set(j + 1, list.get(j));
				j--;
			}
			list.set(j + 1, key);
		}
	}
}
