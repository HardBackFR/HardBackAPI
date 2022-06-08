package fr.hardback.spigot.tools.images.tasks;

import fr.hardback.spigot.tools.images.ImageMap;
import fr.hardback.spigot.tools.images.ImageMapRenderer;
import fr.hardback.spigot.tools.images.helpers.ImageHelper;
import fr.hardback.spigot.tools.images.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class TaskUpdateImage extends BukkitRunnable {

    private final ImageMap imageMap;

    public TaskUpdateImage(ImageMap imageMap) {
        this.imageMap = imageMap;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            final BufferedImage image = ImageHelper.getImage(this.imageMap.getPath());
            final int row = image.getHeight() / 128;
            final int cols = image.getWidth() / 128;

            MapView map;
            int index = 0;

            for(int i = 0; i < row; i++){
                for(int j = 0; j < cols; j++){
                    map = Bukkit.getMap(this.imageMap.getMapIds().get(index));
                    map = RenderHelper.resetRenderers(map);
                    map.setScale(MapView.Scale.FARTHEST);
                    map.addRenderer(new ImageMapRenderer(image.getSubimage(j * 128, i * 128, 128, 128)));

                    index++;
                }
            }
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Cannot load image " + imageMap.getPath() + ".");
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
        }
    }
}
