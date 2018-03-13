package replies;

import java.util.List;

// provides supportive messages
public class Support implements Reply {
	private static final List<String> HUG_EMOJIS = 
		List.of("<:kateHug:377953817146294284>", "<:fullyHug:379761939749928973>", "<:cirLove:377616373448310788>");
	private static final List<String> NICE_MESSAGES = 
		List.of("You're a lovely person", "Your gender is valid", "I'm so glad I met you", "You're a pretty girl");
	private static final List<Character> TRAILING = List.of('~', '!', ' ');
	
	public static List<String> annoy() {
		return List.of(
			Reply.pickRandom(NICE_MESSAGES) + Reply.pickRandom(TRAILING) + " " + Reply.pickRandom(HUG_EMOJIS)
		);
	}
}