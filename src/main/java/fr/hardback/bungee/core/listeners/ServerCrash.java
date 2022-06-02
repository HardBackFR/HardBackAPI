package fr.hardback.bungee.core.listeners;

import fr.hardback.bungee.HardBack;
import fr.hardback.spigot.tools.discord.DiscordManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class ServerCrash implements Listener {

    protected final HardBack instance;

    public ServerCrash(HardBack instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onServerCrash(ServerKickEvent event){
        final ProxiedPlayer player = event.getPlayer();

        if(player.getServer() != null){
            if (!player.getServer().getInfo().getName().equalsIgnoreCase("hub1")) {
                event.setCancelled(true);
                this.instance.getProxy().getScheduler().schedule(this.instance, () -> player.connect(this.instance.getProxy().getServerInfo("hub1")), 1L, TimeUnit.MICROSECONDS);
            }
            player.disconnect(new TextComponent(HardBack.PREFIX + ChatColor.RED + "\nIl semblerait que le " + player.getServer().getInfo().getName() + "  ait crash !\n Merci de contacter un Administrateur !"));
            DiscordManager.send("Il semblerait que " + player.getServer().getInfo().getName() + " ait crash ! (Le serveur se relance)");
        }
    }
}
