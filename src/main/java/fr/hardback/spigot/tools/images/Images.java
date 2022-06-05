package fr.hardback.spigot.tools.images;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Images {

    private final Plugin plugin;

    public final File IMAGES_DIR;
    public final File IMAGES_MAP_DIR;

    public Images(Plugin plugin){
        this.plugin = plugin;

        this.IMAGES_DIR = new File(plugin.getDataFolder(), "images");
        this.IMAGES_MAP_DIR = new File(plugin.getDataFolder(), "maps");
    }

    public void render(Player player, String path){
        new TaskRenderImage(player, path).runTaskAsynchronously(plugin);
    }

}
