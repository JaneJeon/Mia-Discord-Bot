Discord Annoy Bot
-
Instructions:
1. Create a bot (optionally, in a new account). [Check out the process here!](https://github.com/reactiflux/discord-irc/wiki/Creating-a-discord-bot-&-getting-a-token)
2. Invite your bot to a server, as outlined in the link above.
3. Create `token.txt` and `victim.txt` in `src/main/resources` with your bot's token name and your victim's screen name (without the # and numbers) in each file, respectively.
4. Run `gradle shadowJar` at the root of this project (the same level as `build.gradle`).
5. Run the jar: `java -jar build/libs/$JAR_NAME-all.jar`