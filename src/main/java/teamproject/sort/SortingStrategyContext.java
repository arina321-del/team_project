package teamproject;

public class SortingStrategyContext {
	private SortingStrategy strategy;

	public SortingStrategyContext(SortingStrategy strategy) {
		this.strategy = strategy;

	}

	public SortingStrategyContext() {
	}

	public SortingStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SortingStrategy strategy) {
		this.strategy = strategy;
	}

	public void sort(CustomList<User> users) {
		strategy.sort(users);
	}
}
