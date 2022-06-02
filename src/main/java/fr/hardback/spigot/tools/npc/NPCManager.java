package fr.hardback.spigot.tools.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.hardback.spigot.api.HardBackAPI;
import net.minecraft.server.v1_8_R3.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCManager {

    public void createNPC(Location location, String name, String texture, String signature, Player player){
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nsmWorld = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);

        changeSKin(gameProfile, texture, signature);

        EntityPlayer npc = new EntityPlayer(nmsServer, nsmWorld, gameProfile, new PlayerInteractManager(nsmWorld));
        npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));

        Player npcPlayer = npc.getBukkitEntity().getPlayer();
        CraftPlayer cp = (CraftPlayer) npcPlayer;

        Bukkit.getScheduler().runTaskLater(HardBackAPI.get().getPlugin(), () -> {
            sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle()));
        }, 20 * 3);
    }

    private void changeSKin(GameProfile gameProfile, String texture, String signature){
        gameProfile.getProperties().put("texture", new Property("textures", texture, signature));
    }

    private static void sendPacket (Packet< ? > packet){
        for (Player all : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
