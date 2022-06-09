package fr.hardback.spigot.tools.npc;

import org.bukkit.ChatColor;

public enum NPC {

    NAVIGATEUR(ChatColor.YELLOW + "" + ChatColor.BOLD + "Le Navigateur", "KIZAFOX", -0.562, 100.0, 0.169);

    private final String name, skinName;
    private final double x, y, z;

    NPC(String name, String skinName, double x, double y, double z) {
        this.name = name;
        this.skinName = skinName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public String getSkinName() {
        return skinName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
