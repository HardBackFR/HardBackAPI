package fr.hardback.spigot.tools.npc;

import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractNPC {

    protected final JavaPlugin plugin;
    protected final Player player;
    protected final SortedSet<String> ids;
    protected final NPCLib npc;

    public AbstractNPC(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        this.ids = new TreeSet<>();
        this.npc = new NPCLib(plugin);
    }

    public abstract void execute(NPCList npc);

    public abstract void onNPCInteractEvent(NPCInteractEvent event);
}
