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
            final ProxiedPlayer player = (ProxiedPlayer) sender;
            final AccountData account = DatabaseManager.REDIS.getAccountData(player.getUniqueId());
            if(account.getRank().getPower() < RankUnit.Developpeur.getPower()){
                MessageUtils.sendMessage(player, HardBack.PREFIX + ChatColor.RED + "Vous n'avez pas la permission d'éxecuter cette commande !");
                return;
            }
        }

        if(args.length != 2){
            sender.sendMessage(new TextComponent(HardBack.PREFIX + ChatColor.RED + "/rank <joueur> <grade>"));
            return;
        }

        final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        final AccountData accountTarget = DatabaseManager.REDIS.getAccountData(target.getUniqueId());

        if(!target.isConnected()){
            sender.sendMessage(new TextComponent(HardBack.PREFIX + ChatColor.RED + "Ce joueur n'est pas connecté !"));
            return;
        }

        final RankUnit rank = RankUnit.getByName(args[1]);

        accountTarget.setRank(rank);
        DatabaseManager.REDIS.setAccountData(target.getUniqueId(), accountTarget);
        MessageUtils.sendMessage(target, HardBack.PREFIX + ChatColor.RED+ "Vous n'avez pas la permission d'éxecuter cette commande !");
        DiscordManager.send(sender.getName() + " a changé le grade de " + target.getName() + " par " + rank.getName());
    }
}
