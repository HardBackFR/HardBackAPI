package fr.hardback.spigot.tools.multiworlds;

import fr.hardback.spigot.api.HardBackAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class MultiWorlds {

    /**
     * Please when you create a world respect the agreement :  PLAYER-NAME_WORLD-TYPE_RANDOM-INT (player.getName() + "_FLAT_" + new Random().nextInt(9999))
     * @param player Ask  a player
     * @param worldName Ask a world name
     * @param type Ask a world type
     * @param structures Ask to generate structures or not
     * @param setupBorder Ask to set up border or not
     * @param size Ask the size of the border
     * @param tpPlayer Ask for tp the player or not
     * @param timeUntilTp Ask time to tp the player
     */
    public static void create(Player player, String worldName, WorldType type, boolean structures, boolean setupBorder, double size, boolean tpPlayer, long timeUntilTp){
        Bukkit.createWorld(new WorldCreator(worldName).type(type).generateStructures(structures));
        if(setupBorder) setupBorder(worldName, size);
        if(tpPlayer) tp(worldName, player, timeUntilTp);
    }

    public static void tp(String worldName, Player player, long timeUntilTp){
        if(Bukkit.getWorld(worldName) != null) return;
        Bukkit.getScheduler().runTaskLater(HardBackAPI.get().getPlugin(), () -> player.teleport(new Location(Bukkit.getWorld(worldName), 0, 100, 0)), timeUntilTp * 20);
    }

    public static void setupBorder(String worldName, double size){
        WorldBorder worldBorder = Bukkit.getWorld(worldName).getWorldBorder();
        worldBorder.setCenter(new Location(Bukkit.getWorld(worldName), 0, 0, 0));
        worldBorder.setSize(size);
    }
}
