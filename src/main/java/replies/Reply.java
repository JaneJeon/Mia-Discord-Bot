package replies;

import java.util.List;

public interface Reply {
	// TODO: is there a better way to do this?
	static List<String> annoy() {
		return List.of("<3");
	}
	
	static <E> E pickRandom(List<E> list) {
		return list.get((int) (Math.random() * list.size()));
	}
}