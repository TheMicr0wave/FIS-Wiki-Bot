/**
 *  Fungal Infection: Spore wiki bot
 *  Developed by TheMicrowave in collaboration with slasherwolf_ et al.
 *
 *  Version: 0.3.4.3
 *  Last updated [mm/dd/yyyy]: 01/15/2026
 *
 *  Changes:
 *  Updated thresholdValue() method to reduce the trigger threshold in messages with a shorter length
 */

package bot;

import net.dv8tion.jda.api.JDABuilder;
//import net.dv8tion.jda.api.JDA;
//import net.dv8tion.jda.api.entities.Member;
//import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import io.github.cdimascio.dotenv.Dotenv;

public class BotMain {
    public static void main(String[] args) throws Exception {

        Dotenv dotenv = Dotenv.load();

        String token = dotenv.get("DISCORD_TOKEN");

        System.out.println("Token loaded successfully!");

        JDABuilder.createDefault(token,GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_EMOJIS_AND_STICKERS)
                .addEventListeners(new MessageListener())
                .build();
    }
}