package fr.hardback.spigot.api;

import fr.hardback.spigot.tools.npc.NPCManager;
import fr.hardback.spigot.tools.pets.PetManager;
import in.ashwanthkumar.slack.webhook.Slack;
import in.ashwanthkumar.slack.webhook.SlackAttachment;
import in.ashwanthkumar.slack.webhook.SlackMessage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Author: @KIZAFOX
 * For the HardBack project !
 */
public abstract class HardBackAPI {

    protected static HardBackAPI instance;
    protected JavaPlugin plugin;

    public final static String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HardBack" + ChatColor.RESET + ChatColor.GRAY + "] ";

    /**
     * Constructor
     *
     * @param plugin Root plugin
     */
    public HardBackAPI(JavaPlugin plugin) {
        instance = this;
        this.plugin = plugin;
    }

    /**
     * Send log message
     * @param level - Level of log
     * @param text - Message of log
     */
    public void log(Level level, String text){
        plugin.getLogger().log(level, text);
    }

    public abstract String getServerName();

    public abstract Slack getSlackLogsPublisher();

    public void slackLog(Level level, SlackMessage message){
        String color;

        if(level == Level.FINE){
            color = "#2FA44F";
        } else if (level == Level.WARNING) {
            color = "#DE9E31";
        }else if (level == Level.SEVERE){
            color = "#D50200";
        }else{
            color = "#28D7E5";
        }

        try {
            this.getSlackLogsPublisher().push(new SlackAttachment("").color(color).text(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Get the root plugin of the API
     *
     * @return Root plugin instance
     */
    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Get the instance of the API
     *
     * @return Instance
     */
    public static HardBackAPI get() {
        return instance;
    }
}

