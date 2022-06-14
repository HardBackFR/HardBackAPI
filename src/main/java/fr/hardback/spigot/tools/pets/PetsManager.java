package fr.hardback.spigot.tools.pets;

import fr.hardback.spigot.tools.DirectionUtils;
import fr.hardback.spigot.tools.head.CustomHead;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;
import java.util.UUID;

public class PetsManager {

    private final ArmorStand armorStand;
    private final UUID uuid;

    public PetsManager(Player player, Pets pets){
        this.uuid = player.getUniqueId();
        this.armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

        Location location = player.getLocation();

        ((CraftArmorStand) armorStand).getHandle().setLocation(location.getX() - 0.5, location.getY() + 0.7, location.getZ(), location.getYaw(), location.getPitch());
        this.armorStand.setCustomName(pets.getName());
        this.armorStand.setCustomNameVisible(true);
        this.armorStand.setSmall(true);
        this.armorStand.setHelmet(CustomHead.create(pets.getHeadURL()));
        this.armorStand.setGravity(false);
        this.armorStand.setVisible(false);
    }

    public void onMove(PlayerMoveEvent event){
        if(event.getPlayer().getUniqueId() != uuid) return;

        Player player = event.getPlayer();
        Location location = player.getLocation();

        switch (Objects.requireNonNull(DirectionUtils.getPlayerDirection(player))){
            case NORTH:
                ((CraftArmorStand) armorStand).getHandle().setLocation(location.getX() + 0.5, location.getY() + 0.7, location.getZ() + 0.5, location.getYaw(), location.getPitch());
                break;
            case EAST:
                ((CraftArmorStand) armorStand).getHandle().setLocation(location.getX() - 0.5, location.getY() + 0.7, location.getZ() + 0.5, location.getYaw(), location.getPitch());
                break;
            case WEST:
                ((CraftArmorStand) armorStand).getHandle().setLocation(location.getX() + 0.5, location.getY() + 0.7, location.getZ() - 0.5, location.getYaw(), location.getPitch());
                break;
            case SOUTH:
                ((CraftArmorStand) armorStand).getHandle().setLocation(location.getX() - 0.5, location.getY() + 0.7, location.getZ() - 0.5, location.getYaw(), location.getPitch());
                break;
            default:break;
        }
    }
}
