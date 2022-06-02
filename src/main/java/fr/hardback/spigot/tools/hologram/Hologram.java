package fr.hardback.spigot.tools.hologram;

import fr.hardback.spigot.api.HardBackAPI;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private final List<EntityArmorStand> entitylist = new ArrayList<>();
    private final String[] text;
    private final Location location;
    private double DISTANCE = 0.25D;
    private int count;

    public Hologram(String[] text, Location location) {
        this.text = text;
        this.location = location;
        create();
    }

    public void showPlayerTemp(Player player, long time){
        showPlayer(player);
        Bukkit.getScheduler().runTaskLater(HardBackAPI.get().getPlugin(), () -> hidePlayer(player), time * 20);
    }


    public void showAllTemp(long time){
        showAll();
        Bukkit.getScheduler().runTaskLater(HardBackAPI.get().getPlugin(), this::hideAll, time);
    }

    public void showPlayer(Player player) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void hidePlayer(Player player) {
        for (EntityArmorStand armor : entitylist) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void showAll() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            for (EntityArmorStand armor : entitylist) {
                PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public void hideAll() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            for (EntityArmorStand armor : entitylist) {
                PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    private void create() {
        for (String texts : this.text) {
            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) this.location.getWorld()).getHandle(),this.location.getX(), this.location.getY(),this.location.getZ());
            entity.setCustomName(texts);
            entity.setCustomNameVisible(true);
            entity.setInvisible(true);
            entity.setGravity(false);
            entitylist.add(entity);
            this.location.subtract(0, this.DISTANCE, 0);
            count++;
        }

        for (int i = 0; i < count; i++) {
            this.location.add(0, this.DISTANCE, 0);
        }
        this.count = 0;
    }
}