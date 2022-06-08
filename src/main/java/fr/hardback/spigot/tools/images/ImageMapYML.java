package fr.hardback.spigot.tools.images;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class ImageMapYML {

    private final UUID imageMapUUID;
    private final File configFile;
    private final YamlConfiguration yamlConfiguration;

    public ImageMapYML(UUID imageMapUUID) {
        this.imageMapUUID = imageMapUUID;
        this.configFile = new File(Images.IMAGE_MAP_MANAGER.toString() + ".yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }

    public void write(ImageMap imageMap){
        final ConfigurationSection config = this.yamlConfiguration.createSection("image");

        config.set("uuid", imageMap.getUuid().toString());
        config.set("path", imageMap.getPath());
        config.set("ids", imageMap.getMapIds());

        save();
    }

    public ImageMap read(){
        final ConfigurationSection config = this.yamlConfiguration.getConfigurationSection("image");
        final String uuidStr = config.getString("uuid");
        final String path = config.getString("path");
        final ArrayList<Short> ids = (ArrayList<Short>) config.getShortList("ids");

        return new ImageMap(UUID.fromString(uuidStr), path, ids);
    }

    private void save(){
        try {
            this.yamlConfiguration.save(this.configFile);
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Cannot save image map config file : " + imageMapUUID.toString() + ".yml");
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
        }
    }
}
