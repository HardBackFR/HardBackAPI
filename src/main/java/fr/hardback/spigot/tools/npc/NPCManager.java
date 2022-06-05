package fr.hardback.spigot.tools.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.UUID;

public class NPCManager {

    public void createNPC(NPC npc, Plugin plugin){
        final MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        final GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npc.getName());

        final NPCSkin npcSkin = new NPCSkin();
        final String[] skin = npcSkin.getFromPlayer(Bukkit.getPlayer(npc.getSkinName()));
        gameProfile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));

        final EntityPlayer entityPlayer = new EntityPlayer(minecraftServer, minecraftServer.getWorldServer(0), gameProfile, new PlayerInteractManager(minecraftServer.getWorldServer(0)));
        entityPlayer.setPosition(npc.getX(), npc.getY(), npc.getZ());

        final Location location = new Location(Bukkit.getWorld("world"), npc.getX(), npc.getY(), npc.getZ() - 0.15);
        final Vector direction = location.getDirection();
        final Location front = location.add(direction);
        final ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(front, EntityType.ARMOR_STAND);
        armorStand.setCustomName(npc.getName());
        armorStand.setCustomNameVisible(false);
        armorStand.setVisible(false);
        armorStand.setGravity(false);

        Bukkit.getOnlinePlayers().forEach(players -> {
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer)), 5);
        });
    }

    public static void removeNPC(NPC npc){
        final MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        final GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npc.getName());
        final EntityPlayer entityPlayer = new EntityPlayer(minecraftServer, minecraftServer.getWorldServer(0), gameProfile, new PlayerInteractManager(minecraftServer.getWorldServer(0)));

        Bukkit.getOnlinePlayers().forEach(players -> {
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entityPlayer.getId()));
        });

        Bukkit.getWorlds().forEach(worlds -> worlds.getEntities().forEach(entities -> {
            if(entities instanceof ArmorStand & entities.getCustomName().equalsIgnoreCase(npc.getName())) entities.remove();
        }));
    }
}
