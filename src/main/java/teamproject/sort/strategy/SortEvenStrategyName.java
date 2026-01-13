package teamproject.sort.strategy;

import teamproject.entities.User;
import teamproject.sort.SortEven;
import teamproject.sort.SortEvenStrategy;
import teamproject.utils.CustomList;

public class SortEvenStrategyName implements SortEvenStrategy {

	@Override
	public void sort(CustomList<User> list) {
		SortEven.sort(list, u -> u.getName());
	}

}
