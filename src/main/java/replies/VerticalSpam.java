package replies;

import java.util.ArrayList;
import java.util.List;

public class VerticalSpam implements Reply {
	private static final List<String> messages = List.of("AYY LMAO", "JUST MONIKA", "OMAE WA MO SHINDEIRU");
	private static final String EMPTY_SPACE = "|"; // should be _ _
	
	public static List<String> annoy() {
		List<String> result = new ArrayList<>();
		String randomMessage = Reply.pickRandom(messages);
		
		// explode the annoying message into vertical characters
		for (int i = 0; i < randomMessage.length(); i++)
			result.add(randomMessage.charAt(i) == ' ' ? EMPTY_SPACE : "" + randomMessage.charAt(i));
		
		return result;
	}
}