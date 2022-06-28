package fr.hardback.bungee.core.commands;

import fr.hardback.bungee.HardBack;
import fr.hardback.bungee.core.rank.RankUnit;
import fr.hardback.bungee.core.utils.MessageUtils;
import fr.hardback.commons.DatabaseManager;
import fr.hardback.commons.data.AccountData;
import fr.hardback.spigot.tools.discord.DiscordManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandRank extends Command {

    public CommandRank() {
        super("rank", null, "grade", "rankup");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            if(DatabaseManager.REDIS.getAccountData(((ProxiedPlayer) sender).getUniqueId()).getRank().getPower() < RankUnit.Developpeur.getPower()) return;
        }

        final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

        if(!target.isConnected()){
            sender.sendMessage(new TextComponent(HardBack.PREFIX + ChatColor.RED + "Ce joueur n'est pas connecté !"));
            return;
        }

        final AccountData accountTarget = DatabaseManager.REDIS.getAccountData(target.getUniqueId());
        final RankUnit rank = RankUnit.getByName(args[1]);

        accountTarget.setRank(rank);
        DatabaseManager.REDIS.setAccountData(target.getUniqueId(), accountTarget);

        sender.sendMessage(new TextComponent(HardBack.PREFIX + ChatColor.GOLD + "Vous avez modifié le grade de " + ChatColor.BOLD + target.getName() + ChatColor.RESET + ChatColor.GOLD + " par " + rank.getPrefix()));
        target.sendMessage(new TextComponent(HardBack.PREFIX + ChatColor.GREEN + "Votre grade a été mit à jour !"));

        DiscordManager.send(sender.getName() + " a changé le grade de " + target.getName() + " par " + rank.getName());
    }
}
