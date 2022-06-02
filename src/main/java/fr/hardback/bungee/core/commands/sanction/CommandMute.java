package fr.hardback.bungee.core.commands.sanction;

import fr.hardback.bungee.core.utils.MessageUtils;
import fr.hardback.bungee.core.utils.sanction.SanctionUnit;
import fr.hardback.commons.DatabaseManager;
import fr.hardback.commons.data.AccountData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandMute extends Command {

    public CommandMute() {
        super("mute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) return;

        final ProxiedPlayer player = (ProxiedPlayer) sender;
        final AccountData account = DatabaseManager.REDIS.getAccountData(player.getUniqueId());

        if(!account.getRank().isStaff()){
            MessageUtils.sendMessage(player, ChatColor.RED + "Vous n'avez pas la permission d'éxécuter cette commande !");
            return;
        }

        if(args.length == 0){
            MessageUtils.sendMessage(player, ChatColor.RED + "/kick <joueur> <raison>");
            return;
        }

        final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

        if(target != null && target.isConnected()){
            AccountData targetAccount = DatabaseManager.REDIS.getAccountData(target.getUniqueId());

            if(targetAccount.getRank().isStaff()){
                MessageUtils.sendMessage(player, ChatColor.RED + "Vous ne pouvez pas sanctionner un membre du staff !");
                return;
            }

            //do stuff
        }else{
            MessageUtils.sendMessage(player, ChatColor.RED + "" + ChatColor.BOLD + args[0] + ChatColor.RESET + ChatColor.RED + " n'est pas connecté ou n'existe pas !");
        }
    }
}