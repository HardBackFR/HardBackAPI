package fr.hardback.spigot.tools.npc;

import dev.sergiferry.playernpc.api.NPC;
import dev.sergiferry.playernpc.api.NPCLib;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.UUID;

public class NPCManager {

    public static void setup(Player player, Plugin plugin, NPCList npc, boolean look){
        NPC.Personal personalNPC = NPCLib.getInstance().generatePersonalNPC(player, plugin, npc.getName(), npc.getLocation());
        personalNPC.setSkin(new NPC.Skin(npc.getTexture(), npc.getSignature()));
        personalNPC.setItem(NPC.Slot.MAINHAND, npc.getItemStack());
        personalNPC.setText(Arrays.asList(npc.getName(), NPCList.SUB_TITLE));
        personalNPC.setCollidable(true);
        personalNPC.setFollowLookType(NPC.FollowLookType.PLAYER);
        personalNPC.setCustomTabListName(null, false);
        personalNPC.setShowOnTabList(false);
        personalNPC.setTextOpacity(NPC.Hologram.Opacity.HARDER);
        personalNPC.setPose(NPC.Pose.STANDING);
        personalNPC.teleport(npc.getLocation());

        personalNPC.create();
        personalNPC.show();

        if(look){
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                personalNPC.lookAt(player.getLocation());
                personalNPC.update();
            }, 0L, 20L);
        }
    }
}
