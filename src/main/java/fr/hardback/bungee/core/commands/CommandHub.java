package fr.hardback.bungee.core.commands;

import fr.hardback.bungee.HardBack;
import fr.hardback.bungee.core.utils.MessageUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class CommandHub extends Command {

	protected final HardBack instance;

	public CommandHub(HardBack instance) {
		super("hub", null, "spawn", "lobby");
		this.instance = instance;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof ProxiedPlayer)) return;

		final ProxiedPlayer player = (ProxiedPlayer) sender;

		if(player.getServer().getInfo().getName().equals("hub1")){
			MessageUtils.sendMessage((ProxiedPlayer) sender, ChatColor.RED + "Vous êtes déjà sur le Hub !");
		}else{
			this.instance.getProxy().getScheduler().schedule(this.instance, () -> player.connect(this.instance.getProxy().getServerInfo("hub1")), 1L, TimeUnit.MICROSECONDS);
		}
	}
}
