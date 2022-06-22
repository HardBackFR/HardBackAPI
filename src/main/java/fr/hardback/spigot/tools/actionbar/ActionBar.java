package fr.hardback.spigot.tools.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBar {

    /**
     * Send an action bar to a player
     * @param player - Player object
     * @param message - Message send to player
     */
    public static void send(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }
}