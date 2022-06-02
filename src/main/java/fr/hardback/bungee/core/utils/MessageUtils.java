package fr.hardback.bungee.core.utils;

import fr.hardback.bungee.HardBack;
import fr.hardback.bungee.core.utils.sanction.SanctionUnit;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageUtils {

    public static void sendMessage(ProxiedPlayer player, String message){
        player.sendMessage(new TextComponent(TextComponent.fromLegacyText(message)));
    }

    public static void sendMessage(String message){
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(TextComponent.fromLegacyText(HardBack.PREFIX + message)));
    }

    public static void sendSanctionMessage(ProxiedPlayer player, SanctionUnit sanction, String reason){
        player.disconnect(new TextComponent(HardBack.PREFIX + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + sanction.getName() + "\n\n" + ChatColor.RED + "Raison: " + ChatColor.GRAY + reason + "\n\n" + ChatColor.GREEN + "Contacter un Admin en cas d'erreur/problème !"));
    }
}
