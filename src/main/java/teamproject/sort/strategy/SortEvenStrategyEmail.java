package teamproject;

public class SortEvenStrategyEmail implements SortEvenStrategy {

	@Override
	public void sort(CustomList<User> list) {
		SortEven.sort(list, u -> u.getEmail());
	}

}
