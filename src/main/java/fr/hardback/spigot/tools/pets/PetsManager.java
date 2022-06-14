package fr.hardback.spigot.tools.pets;

import fr.hardback.spigot.tools.DirectionUtils;
import fr.hardback.spigot.tools.head.CustomHead;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PetsManager {

    public final Map<UUID, ArmorStand> pet;
    private final UUID uuid;
    private final ArmorStand armorStand;

    public PetsManager(Player player, Pets pets){
        this.pet = new HashMap<>();
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
        this.pet.put(player.getUniqueId(), this.armorStand);
    }

    public void destroy(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked().getCustomName() == null) return;

        for(Pets pets : Pets.values()){
            if(event.getRightClicked().getCustomName().contains(pets.getName())) {
                event.getRightClicked().remove();
                new ParticleBuilder(ParticleEffect.EXPLOSION_HUGE, event.getRightClicked().getLocation()).setOffsetY(1f).setSpeed(0.1f).display();
                this.pet.remove(event.getRightClicked().getUniqueId());
            }
        }
    }

    public void tp(PlayerMoveEvent event){
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
            default: break;
        }
    }
}
