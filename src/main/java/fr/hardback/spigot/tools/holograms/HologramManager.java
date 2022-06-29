package fr.hardback.spigot.tools.holograms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

import java.util.List;

public class HologramManager {

    public static void send(Location location, List<String> lines){
        DHAPI.createHologram(lines.get(0), location, true, lines);
    }

    public static void delete(){
        for(Hologram holograms : Hologram.getCachedHolograms()){
            holograms.delete();
        }
    }
}
