package fr.hardback.spigot.tools.actionbar;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;


public class ActionBarAPI {

    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(cbc, (byte) 2));
    }
}