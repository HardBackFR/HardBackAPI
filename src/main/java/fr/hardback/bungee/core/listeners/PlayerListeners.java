package fr.hardback.bungee.core.listeners;

import fr.hardback.bungee.HardBack;
import fr.hardback.bungee.core.rank.RankUnit;
import fr.hardback.bungee.core.utils.MessageUtils;
import fr.hardback.commons.DatabaseManager;
import fr.hardback.spigot.tools.discord.DiscordManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerListeners implements Listener {

    protected final HardBack instance;

    public PlayerListeners(HardBack instance) {
        this.instance = instance;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onLogin(PostLoginEvent event){
        this.instance.getProxy().getScheduler().runAsync(this.instance, () -> DatabaseManager.accountProvider.createAccount(event.getPlayer().getUniqueId(), event.getPlayer().getName()));

        if(this.instance.getFileUtils().getConfig("config").getString("global.maintenance").equals("on")){
            if(!DatabaseManager.REDIS.getAccountData(event.getPlayer().getUniqueId()).getRank().isStaff()){
                event.getPlayer().disconnect(new TextComponent(HardBack.PREFIX + ChatColor.RED + "Une maintenance est en cours.. Merci de contacter un Admin en cas de problème !"));
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onLogout(PlayerDisconnectEvent event){
        DatabaseManager.accountProvider.saveAccount(event.getPlayer().getUniqueId());
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onChat(ChatEvent event){
        final ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        final RankUnit rank = DatabaseManager.REDIS.getAccountData(player.getUniqueId()).getRank();
        if(event.getMessage().startsWith("!") && rank.getPower() >= RankUnit.Builder.getPower()){
            event.setCancelled(true);

            for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()){
                final RankUnit ranks = DatabaseManager.REDIS.getAccountData(players.getUniqueId()).getRank();
                if(ranks.getPower() >= RankUnit.Builder.getPower()){
                    MessageUtils.sendMessage(players, ChatColor.GRAY+"["+ChatColor.GOLD+"StaffChat | "+player.getServer().getInfo().getName()+ChatColor.GRAY+"] " + rank.getPrefix() + player.getName() + ChatColor.GRAY + " » " + ChatColor.WHITE + event.getMessage().substring(1));
                }
            }
        }

        final String[] strings = event.getMessage().split(" ");
        final String cmd = strings[0].toLowerCase().replace("/", "");

        if(cmd.equals("pl") || cmd.equals("plugins") ||cmd.equals("bukkit:pl") || cmd.equals("bukkit:plugins") || cmd.equals("version") || cmd.equals("about") || cmd.equals("ver") || cmd.equals("bukkit:ver") || cmd.equals("bukkit:version") || cmd.equals("bukkit:about") || cmd.equals("reload") || cmd.equals("reload-confirm")) event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onServerSwitch(ServerSwitchEvent event){
        ProxyServer.getInstance().getPlayers().forEach(players -> players.setTabHeader(new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "HardBack " + ChatColor.GRAY + "(" + ChatColor.YELLOW + ProxyServer.getInstance().getPlayers().size() + ChatColor.GRAY + "/" + ChatColor.YELLOW + this.instance.getProxy().getConfigurationAdapter().getListeners().iterator().next().getMaxPlayers() + ChatColor.GRAY + ")\n\n" + ChatColor.GRAY + "Twitter: " + ChatColor.YELLOW + "@HardBack\n" + ChatColor.GRAY + "IP: " + ChatColor.YELLOW + "play.hardback.fr" + "\n"), new TextComponent(ChatColor.GRAY+"Une question ? Rendez-vous sur notre discord ! " + ChatColor.YELLOW + "discord.gg/hardback")));
    }
}
