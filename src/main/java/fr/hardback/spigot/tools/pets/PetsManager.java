package fr.hardback.spigot.tools.pets;

import fr.hardback.spigot.tools.DirectionUtils;
import fr.hardback.spigot.tools.head.CustomHead;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PetsManager {

    public final Map<UUID, Pets> pet;
    private final UUID uuid;
    private ArmorStand armorStand;

    public PetsManager(Player player){
        this.pet = new HashMap<>();
        this.uuid = player.getUniqueId();
    }

    public void execute(Pets pets){
        Location location = this.getPlayer().getLocation();

        this.armorStand = (ArmorStand) this.getPlayer().getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.setLocation(location.add(new Vector(-0.5, +0.7, 0)));
        this.armorStand.setCustomName(pets.getName());
        this.armorStand.setCustomNameVisible(true);
        this.armorStand.setSmall(true);
        this.armorStand.setHelmet(CustomHead.create(pets.getHeadURL()));
        this.armorStand.setGravity(false);
        this.armorStand.setVisible(false);
    }

    public void destroy(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked().getCustomName() == null) return;
        if(!this.pet.containsKey(event.getPlayer().getUniqueId()))return;

        for(Pets pets : Pets.values()){
            if(event.getRightClicked().getCustomName().contains(pets.getName())) {
                this.pet.remove(event.getRightClicked().getUniqueId(), pets);
                event.getRightClicked().remove();
                new ParticleBuilder(ParticleEffect.EXPLOSION_HUGE, event.getRightClicked().getLocation()).setOffsetY(1f).setSpeed(0.1f).display();
            }
        }
    }

    public void tp(PlayerMoveEvent event){
        if(event.getPlayer().getUniqueId() != uuid) return;
        if(DirectionUtils.getPlayerDirection(event.getPlayer()) == null)return;
        if(!this.pet.containsKey(event.getPlayer().getUniqueId()))return;

        Player player = event.getPlayer();
        Location location = player.getLocation();

        switch (Objects.requireNonNull(DirectionUtils.getPlayerDirection(player))){
            case NORTH:
                this.setLocation(location.add(new Vector(+ 0.5, + 0.7, + 0.5)));
                break;
            case EAST:
                this.setLocation(location.add(new Vector(- 0.5, + 0.7, + 0.5)));
                break;
            case WEST:
                this.setLocation(location.add(new Vector(+ 0.5, + 0.7, - 0.5)));
                break;
            case SOUTH:
                this.setLocation(location.add(new Vector(- 0.5, + 0.7, - 0.5)));
                break;
            default: break;
        }
    }

    public boolean petIsSpawn(){
        return this.pet.containsKey(this.getPlayer().getUniqueId());
    }

    private void setLocation(Location location){
        this.armorStand.getLocation().setX(location.getX());
        this.armorStand.getLocation().setY(location.getY());
        this.armorStand.getLocation().setZ(location.getZ());
        this.armorStand.getLocation().setYaw(location.getYaw());
        this.armorStand.getLocation().setPitch(location.getPitch());
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(this.uuid);
    }
}
