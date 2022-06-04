package fr.hardback.spigot.tools.npc;

import org.bukkit.ChatColor;

public enum NPC {

    TEST(ChatColor.BLUE + "NPC Test", "KIZAFOX", 0, 10, 0);

    private final String name, skinName;
    private final int x, y, z;

    NPC(String name, String skinName, int x, int y, int z) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
