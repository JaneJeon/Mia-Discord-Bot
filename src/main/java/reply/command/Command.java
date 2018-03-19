package reply.command;

import java.util.List;

public interface Command {
	List<String> command(String input);
	
	default List<String> get(String input) {
		return command(input);
	}
}