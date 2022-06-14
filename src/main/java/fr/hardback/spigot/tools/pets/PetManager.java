package fr.hardback.spigot.tools.pets;

import fr.hardback.spigot.api.HardBackAPI;
import fr.hardback.spigot.tools.head.CustomHead;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PetManager {

    private final Map<UUID, Pig> petsMap =  new HashMap<>();;

    public void execute(Player player, Pets pets){
        Bukkit.getScheduler().runTaskAsynchronously(HardBackAPI.get().getPlugin(), () -> {
            if(pets.isGround()){
                Pig pig = (Pig) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIG);

                pig.setCustomName(pets.getName());
                pig.setCustomNameVisible(true);
                pig.getEquipment().setHelmet(CustomHead.create(pets.getHeadURL()));
                pig.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));

                petsMap.put(player.getUniqueId(), pig);
            }
        });
    }

    public void remove(Player player){
        this.petsMap.remove(player.getUniqueId());
    }

    public void onPlayerMove(PlayerMoveEvent event){
        if(petsMap.containsKey(event.getPlayer().getUniqueId())){
            Pig pig = this.petsMap.get(event.getPlayer().getUniqueId());
            pig.teleport(event.getPlayer().getLocation());
        }
    }
}