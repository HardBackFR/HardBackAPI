package fr.hardback.spigot.tools.npc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public enum NPC {

    NAVIGATEUR(ChatColor.GOLD + "Navigateur", "KIZAFOX", new Location(Bukkit.getWorld("world"), -0.562, 100.0, 0.169, -178.8f, 3.3f));

    private final String name, skinName;
    private final Location location;

    NPC(String name, String skinName, Location location) {
        this.name = name;
        this.skinName = skinName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getSkinName() {
        return skinName;
    }

    public Location getLocation() {
        return location;
    }
}
