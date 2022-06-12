package fr.hardback.spigot.tools.npc;

import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class NPCManager{

    protected final JavaPlugin plugin;

    protected NPCLib npcLib;
    protected NPC npc;

    public NPCManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.npcLib = new NPCLib(this.plugin);
    }

    public void load(NPCList npcList) {
        npc = npcLib.createNPC(Arrays.asList(npcList.getName(), NPCList.SUB_TITLE));
        npc.setLocation(npcList.getLocation());
        npc.setSkin(new Skin(npcList.getTexture(), npcList.getSignature()));
        npc.create();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public NPCLib getNpcLib() {
        return npcLib;
    }

    public NPC getNpc() {
        return npc;
    }
}
