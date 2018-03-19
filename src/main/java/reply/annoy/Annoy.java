package reply.annoy;

import java.util.List;

public interface Annoy {
	List<String> annoy(String input);
	
	default List<String> get(String input) {
		return annoy(input);
	}
}