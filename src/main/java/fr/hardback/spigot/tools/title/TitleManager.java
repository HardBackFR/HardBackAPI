package fr.hardback.spigot.tools.title;

import org.bukkit.entity.Player;

public class TitleManager {

    /**
     * Send a title to a player
     * @param player - Player object
     * @param title - First line of title
     * @param subTitle - Second line of title
     * @param fadeIn - Fade in second
     * @param stay - Stay in second
     * @param fadeOut - Fade out in second
     */
    public static void send(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut){
        player.sendTitle(title, subTitle, fadeIn, stay, fadeOut);
    }
}
