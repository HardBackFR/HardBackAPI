package fr.hardback.spigot.tools.discord;

import io.github.cdimascio.dotenv.Dotenv;

import java.awt.*;
import java.io.IOException;

public class DiscordManager {

    private static final String ICON_URL = "https://camo.githubusercontent.com/8400b2b3a3dd8a6a400470ec66783783d295119289f2f2dc05cfa7fa403ae5af/68747470733a2f2f692e6962622e636f2f705051683051372f66617669636f6e2e706e67";

    public static void send(String message) {
        try {
            DiscordWebhook discordWebhook = new DiscordWebhook(Dotenv.configure().load().get("DISCORD"));
            discordWebhook.setAvatarUrl(ICON_URL);
            discordWebhook.setUsername("HardBackAPI | Minecraft");
            discordWebhook.setTts(false);
            discordWebhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setAuthor("HardBackAPI", "https://hardback.fr", ICON_URL)
                    .setDescription(message)
                    .setColor(Color.ORANGE)
                    .setFooter("API faite par KIZAFOX pour le serveur HardBack", ICON_URL)
                    .setUrl("https://hardback.fr"));
            discordWebhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
