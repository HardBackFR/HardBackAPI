package fr.hardback.bungee.core;

import fr.hardback.bungee.HardBack;
import fr.hardback.bungee.core.commands.*;
import fr.hardback.bungee.core.commands.sanction.CommandBan;
import fr.hardback.bungee.core.commands.sanction.CommandKick;
import fr.hardback.bungee.core.commands.sanction.CommandMute;
import fr.hardback.bungee.core.listeners.PlayerListeners;
import fr.hardback.bungee.core.listeners.ProxyPing;
import fr.hardback.bungee.core.listeners.ServerCrash;
import net.md_5.bungee.api.plugin.PluginManager;

public class Managers {

    protected final HardBack instance;

    public Managers(HardBack instance) {
        this.instance = instance;

        PluginManager pluginManager = this.instance.getProxy().getPluginManager();

        /*
          Listeners
         */
        pluginManager.registerListener(this.instance, new PlayerListeners(this.instance));
        pluginManager.registerListener(this.instance, new ProxyPing(this.instance));
        pluginManager.registerListener(this.instance, new ServerCrash(this.instance));

        /*
          Commands
         */
        pluginManager.registerCommand(this.instance, new CommandHub(this.instance));
        pluginManager.registerCommand(this.instance, new CommandJoin());
        pluginManager.registerCommand(this.instance, new CommandMessage());
        pluginManager.registerCommand(this.instance, new CommandRank());

        pluginManager.registerCommand(this.instance, new CommandBan());
        pluginManager.registerCommand(this.instance, new CommandMute());
        pluginManager.registerCommand(this.instance, new CommandKick());
    }
}
