package teamproject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCounter {

	public int getCountElement(CustomList<User> users, User findUser) {
		int poolAmount = 2;

		ExecutorService executor = Executors.newFixedThreadPool(poolAmount);
		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();

		int size = users.size();
		int part = size / poolAmount;

		for (int i = 0; i < poolAmount; i++) {
			int start = i * part;
			int end = (i == poolAmount - 1) ? size : part + start;

			Callable<Integer> callable = new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					int localCount = 0;
					for (int j = start; j < end; j++) {
						if (users.get(j).equals(findUser)) {
							localCount++;
						}
					}
					return localCount;
				}
			};

			tasks.add(callable);
		}

		int sum = 0;

		try {
			List<Future<Integer>> values = executor.invokeAll(tasks);
			for (Future<Integer> future : values) {
				sum += future.get();
			}
			return sum;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
		return sum;
	}

}
