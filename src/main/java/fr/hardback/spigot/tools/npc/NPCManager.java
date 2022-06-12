package fr.hardback.spigot.tools.npc;

import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class NPCManager{

    protected final Player player;
    protected final JavaPlugin plugin;

    protected NPCLib npcLib;
    protected NPC npc;

    public NPCManager(Player player, JavaPlugin plugin) {
        this.player = player;
        this.plugin = plugin;

        this.npcLib = new NPCLib(this.plugin);
    }

    public void load(NPCList npcList) {
        npc = npcLib.createNPC(Arrays.asList(npcList.getName(), NPCList.SUB_TITLE));
        npc.setLocation(npcList.getLocation());
        npc.setSkin(new Skin(npcList.getTexture(), npcList.getSignature()));
        npc.create();
    }
}
