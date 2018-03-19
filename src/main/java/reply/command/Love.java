package reply.command;

import util.Pick;

import java.util.List;

// provides supportive messages
// TODO: add more messages
public class Love implements Command {
	private static final List<String> HUG_EMOJIS = 
		List.of("<:kateHug:377953817146294284>", "<:fullyHug:379761939749928973>", "<:cirLove:377616373448310788>");
	private static final List<String> NICE_MESSAGES = 
		List.of("You're a lovely person", "Your gender is valid", "I'm so glad I met you", "You're a pretty girl");
	private static final List<Character> TRAILING = List.of('~', '!', ' ');
	
	@Override
	public List<String> command(String input) {
		return List.of(
			Pick.random(NICE_MESSAGES) + Pick.random(TRAILING) + " " + Pick.random(HUG_EMOJIS)
		);
	}
}