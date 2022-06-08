package fr.hardback.spigot.tools.images;

import fr.hardback.spigot.tools.images.tasks.TaskRenderImage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;


public class Images {

    public static File IMAGES_DIR;
    public static File IMAGES_MAP_DIR;

    public static ImageMapManager IMAGE_MAP_MANAGER;

    public Images(Plugin plugin){
        IMAGES_DIR = new File(plugin.getDataFolder(), "images");
        IMAGES_MAP_DIR = new File(plugin.getDataFolder(), "maps");

        IMAGE_MAP_MANAGER = new ImageMapManager();
    }

    public void render(Player player, String path, Plugin plugin){
        new TaskRenderImage(player, path).runTaskAsynchronously(plugin);
    }
}
