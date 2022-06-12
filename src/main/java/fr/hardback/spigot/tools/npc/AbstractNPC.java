package fr.hardback.spigot.tools.npc;

import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractNPC {

    protected final SortedSet<String> ids;
    protected final NPCLib npcLib;

    public AbstractNPC(Player player, JavaPlugin plugin) {
        this.ids = new TreeSet<>();
        this.npcLib = new NPCLib(plugin);
    }

    public abstract void execute(NPCList npc);

    public abstract void onNPCInteractEvent(NPCInteractEvent event);

    public SortedSet<String> getIds() {
        return ids;
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }
}
