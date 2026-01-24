package bot;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.HashMap;

public class MessageListener extends ListenerAdapter {

    private final ConfigManager channels =  new ConfigManager("channels.json");
    private final ConfigManager cooldowns =  new ConfigManager("cooldowns.json");
    private final WikiChecker wikiChecker = new WikiChecker();
    private final HashMap<String, Integer> userCooldown = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Ignore bots
        if (event.getAuthor().isBot()) return;

        long guildId = Long.parseLong(event.getGuild().getId());
        String content = event.getMessage().getContentRaw();
        String username = event.getAuthor().getName();
        Member member = event.getMember();
        TextChannel channel = event.getChannel().asTextChannel();

        // Set channel command
        if (content.equalsIgnoreCase("!setchannel")) {

            // Only allow users with Manage Server permission
            if (member == null || !member.hasPermission(Permission.MESSAGE_MANAGE)) {
                channel.sendMessage("This bot is configured such that you do not have permission to use this command!").queue();
                return;
            }

            // Save the monitored channel to the channels.json file
            channels.setEntry(guildId, channel.getIdLong());
            channel.sendMessage("This channel is now being monitored.").queue();

            // Auto enable cooldown at default of 5 min
            if (!cooldowns.containsEntry(guildId)) {
                cooldowns.setEntry(guildId, 5);
            }
            return;
        }

        // Set cooldown command
        if (content.toLowerCase().contains("!setcooldown")) {

            // Only allow users with Manage Server permission
            if (member == null || !member.hasPermission(Permission.MESSAGE_MANAGE)) {
                channel.sendMessage("This bot is configured such that you do not have permission to use this command!").queue();
                return;
            }

            String[] messageParts = content.split("\\s+"); // split on whitespace
            int tempCool = Integer.parseInt(messageParts[1]);
            if (tempCool >= 0 &&  tempCool <= 1440 && tempCool != cooldowns.getEntry(guildId)) {
                cooldowns.setEntry(guildId, tempCool);
                channel.sendMessage("Cooldown is now " + tempCool + " minutes.").queue();
            } else if  (tempCool == cooldowns.getEntry(guildId)) {
                channel.sendMessage("Cooldown is already " + tempCool + "!").queue();
            } else {
                channel.sendMessage(tempCool + " is not a valid cooldown value. cooldown must be a value between 0 and 1440.").queue();
            }
            return;
        }

        // Help command
        if (content.equalsIgnoreCase("!help")) {
            event.getMessage()
                    .reply("**Here is a list of available commands!** \n\n**!help :** Lists commands " +
                    "\n**!setchannel :** Sets the channel that the bot monitors for wiki questions [Mod only]"+
                    "\n**!setcooldown [min] :** Sets the time between wiki detection messages on a single user [Mod only]" +
                    "\n**!about :** Shows info about the bot and how it works [Global]" +
                    "\n**!wiki :** Sends the link to the FI:S wiki [Global]" +
                    "\n**!test :** pings the bot [Local to monitored channel]")
                    .queue();
            return;
        }

        // Wiki link command
        if (content.equalsIgnoreCase("!wiki")) {
            channel.sendMessage("Did someone say wiki???? Go get 'em soldier! \n\n https://www.fungalinfectionspore.wiki/home ").queue();
            return;
        }

        // About command
        if (content.equalsIgnoreCase("!about")) {
            event.getMessage()
                    .reply("# About this bot!" +
                                "\n-# v0.3.4.4" +
                                "\n*Developed by TheMicrowave in collaboration with slasherwolf_ et al.*" +
                                "\n\n## How do I work?" +
                                "\nI monitor messages in the channel designated by !setchannel for their relevance to to the Fungal Infection: Spore Wiki." +
                                " Once I have determined that a message is sufficiently likely to be answered on the wiki I will send the user the link to the wiki." +
                                " If that user sends another message which may be answered on the wiki within the cooldown time (min) designated by !setcooldown I will non-intrusively react with 📖" +
                                "\n\n## Features list" +
                                "\n**Current :**" +
                                "\n-Global commands like !wiki, !help, and !about" +
                                "\n-Semi content aware wiki question detection" +
                                "\n-Adjustable single channel monitoring via !setchannel" +
                                "\n-Adjustable messaging cooldown via !setcooldown" +
                                "\n**Planned :**" +
                                "\n-Content aware responses for direct links to helpful wiki pages" +
                                "\n-Multi-channel monitoring" +
                                "\n-Spore addon question help")
                    .queue();
            return;
        }

        // Ignore Other channels
        if (event.getChannel().getIdLong() != channels.getEntry(guildId)) return;

        System.out.println(username + ": " + content);

        // Ping command
        if (content.equalsIgnoreCase("!test")) {
            channel.sendMessage("received").queue();
            return;
        }

        // Wiki algo interface ----------

        if (member == null || member.hasPermission(Permission.MESSAGE_MANAGE)) return;

        RichCustomEmoji customEmoji = event.getGuild().getEmojiById(1457159268955455519L);

        if (wikiChecker.isWikiQuestion(content) == 1) {
            // Check the current time
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));
            int time = now.getHour() * 100 + now.getMinute();

            if (userCooldown.containsKey(username) && (Math.abs(time - userCooldown.get(username)) >= cooldowns.getEntry(guildId) )) {
                userCooldown.put(username, time);
                event.getMessage()
                        .reply("**I have detected that this is a question answered in the wiki!**" +
                                "\nIf this was incorrect, please help me improve by pinging TheMicrowave." +
                                "\n\nI am currently unable to direct you to the exact page where your question is answered at the moment, that feature will be available in the future." +
                                "\nhttps://www.fungalinfectionspore.wiki/home")
                        .queue();
            } else if (!userCooldown.containsKey(username)) {
                userCooldown.put(username, time);
                event.getMessage()
                        .reply("**I have detected that this is a question answered in the wiki!**" +
                                "\nIf this was incorrect, please help me improve by pinging TheMicrowave." +
                                "\n\nI am currently unable to direct you to the exact page where your question is answered at the moment, that feature will be available in the future." +
                                "\nhttps://www.fungalinfectionspore.wiki/home")
                        .queue();
            } else {
                if (customEmoji != null) {
                    event.getMessage().addReaction(customEmoji).queue();
                } else {
                    event.getMessage().addReaction(Emoji.fromUnicode("📖")).queue();
                }
            }
        }
    }
}
