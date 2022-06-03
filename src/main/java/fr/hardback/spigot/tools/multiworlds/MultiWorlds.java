package fr.hardback.spigot.tools.multiworlds;

import fr.hardback.spigot.api.HardBackAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class MultiWorlds {

    /**
     * Please when you create a world respect the agreement :  PLAYER-NAME_WORLD-TYPE_RANDOM-INT (player.getName() + "_FLAT_" + new Random().nextInt(9999)
     * @param player Ask  a player
     * @param type Ask a world type
     * @param structures Ask to generate structures or not
     * @param setupBorder Ask to set up border or not
     * @param borderSize Ask the size of the border
     * @param tpPlayer Ask for tp the player or not
     */
    public void create(Player player, WorldType type, boolean structures, boolean tpPlayer, boolean setupBorder, double borderSize, Plugin plugin){
        player.sendMessage(HardBackAPI.PREFIX + ChatColor.RED + "Création du monde en cours...");

        String worldName = player.getName() + "_" + type.getName() + "_" + new Random().nextInt(9999);
        Bukkit.createWorld(new WorldCreator(worldName).type(type).generateStructures(structures));

        if(tpPlayer){
            player.sendMessage(HardBackAPI.PREFIX + ChatColor.GREEN + "Téléportation vers le monde " + ChatColor.BOLD + worldName + ChatColor.RESET + ChatColor.GREEN + " !");
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.teleport(new Location(Bukkit.getWorld(worldName), 0, 100, 0));

                if(setupBorder){
                    player.sendMessage(HardBackAPI.PREFIX + ChatColor.GREEN + "Création de la bordure de " + ChatColor.BOLD + borderSize + ChatColor.RESET + ChatColor.GREEN +  " de taille !");
                    WorldBorder worldBorder = player.getWorld().getWorldBorder();
                    worldBorder.setCenter(new Location(player.getWorld(), 0, 0, 0));
                    worldBorder.setSize(borderSize);
                }
            }, 100);
        }
    }
}
