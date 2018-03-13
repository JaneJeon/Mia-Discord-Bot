import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import util.Load;

// provides singleton Discord connection
public class Discord {
	private static IDiscordClient instance = null;
	
	private Discord() {}
	
	public static IDiscordClient getConn() {
		if (instance == null) {
			String token = Load.string("token.txt");
			instance = new ClientBuilder().withToken(token).login();
		}
		return instance;
	}
}