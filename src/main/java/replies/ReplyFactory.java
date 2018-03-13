package replies;

import java.util.List;

public class ReplyFactory implements Reply {
	public static List<String> get() {
		// TODO: use service-loader pattern to replace this hacky piece of shit!!!
		return Math.random() < 0.5 ? Support.annoy() : VerticalSpam.annoy();
	}
}