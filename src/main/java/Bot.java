import replies.Reply;
import replies.ReplyFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.MessageBuilder;
import util.Load;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// provides bot behaviour
// TODO: imitate Mia by recording all of her messages and doing some machine learning magic
public class Bot implements IListener<MessageReceivedEvent> {
	private IDiscordClient client;
	private IUser victim;
	private LocalDateTime lastTagged = null;
	private LocalDateTime lastReplied = null;
	private static final String VICTIM = Load.string("victim.txt");
	private static final int HOURS_UNTIL_TAGGING = 8;
	private static final int FUZZ_HOURS = 2;
	private static final int SLEEP_HOURS = 1;
	private static final int WAIT_SECS = 20;
	private static final int TAG_LIMIT = 5;
	private static final List<String> CHANNELS = List.of("treehouse", "another-place-to-talk", "nerd-speak");
	
	private Bot() throws InterruptedException {
		client = Discord.getConn();
		while (!client.isReady())
			Thread.sleep(100);
		
		// register this bot as an event listener
		client.getDispatcher().registerListener(this);
		
		// get Mia
		victim = client.getUsersByName(VICTIM, false).get(0);
		
		// get the tagger on a separate thread
		new Thread(this::randomAnnoy).start();
	}
	
	private int getRandomHours() {
		return (int) (2 * Math.random() * FUZZ_HOURS) + HOURS_UNTIL_TAGGING - FUZZ_HOURS;
	}
	
	// One way of annoying Mia is by randomly tagging her in a message every once in a while.
	private void randomAnnoy() {
		while (true) {
			if (lastTagged == null || 
				// check if sufficient time (random) has passed since the last tag
				Duration.between(lastTagged, LocalDateTime.now())
					.compareTo(Duration.ofHours(getRandomHours())) > 0) {
				annoyVictim();
				lastTagged = LocalDateTime.now();
			}
			
			// convert from hours to milliseconds
			try {
				Thread.sleep(SLEEP_HOURS * 3600 * 1000);
			} catch (Exception ignored) {}
		}
	}
	
	private void annoyVictim() {
		sendMessages(pickRandomChannel(), ReplyFactory.get());
	}
	
	private void annoyVictim(IChannel channel) {
		if (Math.random() > 0.5) {
			// annoy Mia by repeatedly tagging her
			List<String> tags = new ArrayList<>();
			for (int i = 0; i <= Math.random() * TAG_LIMIT; i++)
				tags.add(victim.mention(Math.random() > 0.5));
			sendMessages(channel, tags);
		} else
			sendMessages(channel, ReplyFactory.get());
	}
	
	private IChannel pickRandomChannel() {
		String randomChannel = Reply.pickRandom(CHANNELS);
		return client.getChannels(true).stream()
			.filter(ch -> ch.getName().equals(randomChannel))
			.findFirst()
			.get();
	}
	
	private void sendMessages(IChannel channel, List<String> messages) {
		sendMessage(channel, victim.mention(true));
		messages.forEach(msg -> sendMessage(channel, msg));
	}
	
	// TODO: figure out why some strings are not being sent (it even crashes), including "_ _", "~", and "!"
	private void sendMessage(IChannel channel, String message) {
		System.out.println("Sending message: "+message+", channel: "+channel.getName()+", @"+LocalDateTime.now());
		new MessageBuilder(client)
			.withChannel(channel)
			.withContent(message)
			.build();
	}
	
	// The other way of annoying Mia is by replying almost immediately to her and tagging her
	@Override
	public void handle(MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		IChannel channel = message.getChannel();
		if (CHANNELS.contains(channel.getName()) 
			&& message.getAuthor() == victim
			&& (lastReplied == null || 
				Duration.between(lastReplied, LocalDateTime.now())
					.compareTo(Duration.ofHours(getRandomHours())) > 0)) {
			try {
				Thread.sleep(WAIT_SECS * 1000);
			} catch (InterruptedException ignored) {}
			
			annoyVictim(channel);
			lastReplied = LocalDateTime.now();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Bot();
	}
}