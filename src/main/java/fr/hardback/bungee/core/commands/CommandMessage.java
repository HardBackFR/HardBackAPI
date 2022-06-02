package fr.hardback.bungee.core.commands;

import fr.hardback.bungee.core.utils.MessageUtils;
import fr.hardback.commons.DatabaseManager;
import fr.hardback.commons.data.AccountData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandMessage extends Command {

    public CommandMessage() {
        super("msg", null, "message", "r", "reply");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer && args.length > 0){
            if(ProxyServer.getInstance().getPlayer(args[0]) != null){
                final ProxiedPlayer messager = (ProxiedPlayer) sender;
                final ProxiedPlayer receiver = ProxyServer.getInstance().getPlayer(args[0]);
                final AccountData accountMessager = DatabaseManager.REDIS.getAccountData(messager.getUniqueId());
                final AccountData accountReceiver = DatabaseManager.REDIS.getAccountData(receiver.getUniqueId());

                args[0] = "";
                final StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    message.append(" ").append(arg);
                }
                MessageUtils.sendMessage(messager, ChatColor.GOLD+"« " + accountReceiver.getRank().getPrefix() + receiver.getName() + ChatColor.WHITE + " :" + message);
                MessageUtils.sendMessage(receiver, ChatColor.GOLD+"» " + accountMessager.getRank().getPrefix() + messager.getName() + ChatColor.WHITE + " :" + message);
            } else {
                MessageUtils.sendMessage((ProxiedPlayer) sender,ChatColor.RED+"Ce joueur n'est pas connecté !");
            }
        }
    }
}