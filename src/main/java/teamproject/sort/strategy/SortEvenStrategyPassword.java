package teamproject;

public class SortEvenStrategyPassword implements SortEvenStrategy {

	@Override
	public void sort(CustomList<User> list) {
		SortEven.sort(list, u -> u.getPassword());
	}

}
