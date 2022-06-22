package fr.hardback.spigot.tools.hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class HologramManager {

    /**
     * Create a hologram and saves to a file.
     * @param location - Location where hologram will be placed
     * @param lines - Lines for hologram
     * @return Hologram class
     */
    public static Hologram send(Location location, List<String> lines){
        return DHAPI.createHologram(UUID.randomUUID().toString(), location, true, lines);
    }

    /**
     * Get the hologram asked.
     * @param hologram - Hologram name
     * @return Hologram asked
     */
    public static Hologram getHologram(String hologram){
        return DHAPI.getHologram(hologram);
    }
}