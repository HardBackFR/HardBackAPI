package fr.hardback.spigot.tools.npc;

import com.mojang.authlib.GameProfile;

import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.UUID;

public class NPCManager {

    public static void execute(NPC npc) {
        Location location = npc.getLocation();
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nsmWorld = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npc.getName());
        String[] skin = NPCSkin.getFromName(npc.getSkinName());

        if (skin != null) {
            gameProfile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
        }

        EntityPlayer entityPlayer = new EntityPlayer(nmsServer, nsmWorld, gameProfile, new PlayerInteractManager(nsmWorld));
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        Bukkit.getOnlinePlayers().forEach(players -> {
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));

            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer));
        });
    }
}
