package fr.hardback.bungee.core.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandJoin extends Command {

	public CommandJoin() {
		super("join");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof ProxiedPlayer)) return;
		
		final ProxiedPlayer player = (ProxiedPlayer) sender;

		if(args.length == 0) return;
		
		final ServerInfo server = ProxyServer.getInstance().getServerInfo(args[0]);

		if(server == null || server.getName() == null) return;

		if(args[0].equalsIgnoreCase(server.getName())) {
			player.connect(server);
		}
	}
}
