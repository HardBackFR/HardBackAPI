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
import org.bukkit.util.Vector;

import java.util.UUID;

public class NPCManager {

    public static void execute(NPC npc) {
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npc.getName());
        String[] skin = NPCSkin.getFromPlayer(Bukkit.getPlayer(npc.getSkinName()));

        gameProfile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));

        EntityPlayer entityPlayer = new EntityPlayer(minecraftServer, minecraftServer.getWorldServer(0), gameProfile, new PlayerInteractManager(minecraftServer.getWorldServer(0)));
        Location location = npc.getLocation();

        entityPlayer.setPosition(location.getX(), location.getY(), location.getZ());

        location = npc.getLocation().add(new Vector(0, 0, +0.15D));
        Vector direction = location.getDirection();
        Location front = location.add(direction);

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(front, EntityType.ARMOR_STAND);
        armorStand.setCustomName(npc.getName());
        armorStand.setCustomNameVisible(false);
        armorStand.setVisible(false);
        armorStand.setGravity(false);

        Bukkit.getOnlinePlayers().forEach(players -> {
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));

            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
        });

    }
}
