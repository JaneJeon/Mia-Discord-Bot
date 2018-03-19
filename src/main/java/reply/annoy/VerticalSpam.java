package reply.annoy;

import util.Pick;

import java.util.ArrayList;
import java.util.List;

// TODO: add more variety
public class VerticalSpam implements Annoy {
	private static final List<String> messages = List.of("AYY LMAO", "JUST MONIKA", "LOL");
	private static final String EMPTY_SPACE = "|"; // should be _ _
	
	@Override
	public List<String> annoy(String input) {
		List<String> result = new ArrayList<>();
		String randomMessage = Pick.random(messages);
		
		// explode the annoying message into vertical characters
		for (int i = 0; i < randomMessage.length(); i++)
			result.add(randomMessage.charAt(i) == ' ' ? EMPTY_SPACE : "" + randomMessage.charAt(i));
		
		return result;
	}
}