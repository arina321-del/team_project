package teamproject.source;

import teamproject.entities.User;
import teamproject.utils.CustomList;

public interface InputSource {
	CustomList<User> provide(int size);
}
