package fr.hardback.spigot.api;

import fr.hardback.spigot.tools.npc.NPCManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Author: @KIZAFOX
 * For the HardBack project !
 */
public abstract class HardBackAPI {

    protected static HardBackAPI instance;
    protected JavaPlugin plugin;

    public final static String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HardBack" + ChatColor.RESET + ChatColor.GRAY + "] ";

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
        this.plugin.getLogger().log(level, text);
    }

    public abstract String getServerName();

    public abstract NPCManager getNPCManager();

    public static HardBackAPI get() {
        return instance;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}

