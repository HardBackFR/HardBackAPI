package fr.hardback.bungee;

import fr.hardback.bungee.core.utils.FileUtils;
import fr.hardback.commons.DatabaseManager;
import fr.hardback.bungee.core.Managers;
import fr.hardback.spigot.tools.discord.DiscordManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public class HardBack extends Plugin {

    protected static HardBack instance;
    private FileUtils fileUtils;

    public final static String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "HardBack" + ChatColor.RESET + ChatColor.GRAY + "] ";

    @Override
    public void onEnable() {
        instance = this;

        DatabaseManager.initAllConnection();
        DatabaseManager.accountProvider.loadAccounts();

        this.fileUtils = new FileUtils(this);
        this.fileUtils.createFile("config");

        new Managers(this);
        DiscordManager.send("HardBackProxy has been enabled ! (Version: " + this.getDescription().getVersion() + ")");
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(PREFIX + ChatColor.GREEN + "HardBack has been enabled ! (Version: " + this.getDescription().getVersion() + ")"));
    }

    @Override
    public void onDisable() {
        DatabaseManager.closeAllConnection();
    }

    public static HardBack get() {
        return instance;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }
}
