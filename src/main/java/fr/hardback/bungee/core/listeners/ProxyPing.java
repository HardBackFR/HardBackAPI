package fr.hardback.bungee.core.listeners;

import fr.hardback.bungee.HardBack;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ProxyPing implements Listener {

    protected final HardBack instance;

    public ProxyPing(HardBack instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        final ServerPing serverPing = event.getResponse();

        serverPing.getPlayers().setSample(new ServerPing.PlayerInfo[]{ new ServerPing.PlayerInfo(ChatColor.RED + "Serveur en développement !", UUID.randomUUID()) });
        serverPing.setDescription(ChatColor.GOLD + "" + ChatColor.BOLD + "HardBack" + ChatColor.GRAY + " - " + ChatColor.GREEN + "(1.8.X - 1.18.X)\n" + ChatColor.RED + "Serveur en maintenance...");
        serverPing.setPlayers(new ServerPing.Players(375, ProxyServer.getInstance().getOnlineCount(), serverPing.getPlayers().getSample()));
        serverPing.setVersion(new ServerPing.Protocol(ChatColor.RED + "1.8.X, 1.9.X, 1.10.X, 1.11.X, 1.12.X, 1.13.Xn 1.14.X, 1.15.X, 1.16.X, 1.17.X, 1.18.X", serverPing.getVersion().getProtocol()));

        try {
            serverPing.setFavicon(Favicon.create(ImageIO.read(new File("favicon.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

