package fr.hardback.spigot.tools.title;

import fr.hardback.spigot.api.HardBackAPI;
import fr.hardback.spigot.tools.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Objects;

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
        try {
            Object enumTitle = Objects.requireNonNull(Reflection.getNMSClass("PacketPlayOutTitle")).getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleChat = Objects.requireNonNull(Reflection.getNMSClass("IChatBaseComponent")).getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, "{\"text\":\"" + title + "\"}");

            Object enumSubtitle = Objects.requireNonNull(Reflection.getNMSClass("PacketPlayOutTitle")).getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object subtitleChat = Objects.requireNonNull(Reflection.getNMSClass("IChatBaseComponent")).getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, "{\"text\":\"" + subTitle + "\"}");

            Constructor<?> titleConstructor = Objects.requireNonNull(Reflection.getNMSClass("PacketPlayOutTitle")).getConstructor(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            Object titlePacket = titleConstructor.newInstance(enumTitle, titleChat, 20, 40, 20);
            Object subtitlePacket = titleConstructor.newInstance(enumSubtitle, subtitleChat, 20, 40, 20);

            Reflection.sendPacket(player, titlePacket);
            Reflection.sendPacket(player, subtitlePacket);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(HardBackAPI.PREFIX + ChatColor.RED + e.getMessage());
        }
    }
}
