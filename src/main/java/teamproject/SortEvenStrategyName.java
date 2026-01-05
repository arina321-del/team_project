package teamproject;

public class SortEvenStrategyName implements SortEvenStrategy {

	@Override
	public void sort(CustomList<User> list) {
		SortEven.sort(list, u -> u.getName());
	}

}
