package fr.hardback.spigot.tools.images;

import fr.hardback.spigot.tools.images.helpers.ImageHelper;
import fr.hardback.spigot.tools.images.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRenderImage extends BukkitRunnable {

    private Player player;
    private String path;

    public TaskRenderImage(Player player, String path) {
        this.player = player;
        this.path = path;
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
            final List<Short> mapIds = new ArrayList<>();
            final BufferedImage image = ImageHelper.getImage(path);
            final int row = image.getHeight() / 128;
            final int cols = image.getWidth() / 128;

            MapView map;

            for(int i = 0; i < row; i++){
                for(int j = 0; j < cols; j++){
                    map = Bukkit.createMap(player.getWorld());
                    map = RenderHelper.resetRenderers(map);
                    map.setScale(MapView.Scale.FARTHEST);
                    map.getRenderers().clear();
                    map.addRenderer(new ImageMapRenderer(image.getSubimage(j * 128, i * 128, 128, 128)));

                    mapIds.add(map.getId());
                }
            }

            for(short id : mapIds){
                player.getInventory().addItem(new ItemStack(Material.MAP, 1, id));
            }
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Cannot load image " + path + ".");
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());

        }
    }
}
