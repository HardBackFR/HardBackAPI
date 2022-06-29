package fr.hardback.spigot.tools.holograms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class HologramManager {

    public static void send(Location location, List<String> lines){
        DHAPI.createHologram(UUID.randomUUID().toString(), location, true, lines);
    }

    public static void delete(){
        for(Hologram holograms : Hologram.getCachedHolograms()){
            holograms.delete();
        }
    }
}
