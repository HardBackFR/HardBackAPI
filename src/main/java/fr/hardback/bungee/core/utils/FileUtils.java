package fr.hardback.bungee.core.utils;

import fr.hardback.bungee.HardBack;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    protected final HardBack instance;

    public FileUtils(HardBack instance) {
        this.instance = instance;
    }

    public void createFile(String fileName){
        if(!this.instance.getDataFolder().exists()){
            this.instance.getDataFolder().mkdir();
        }

        final File file = new File(this.instance.getDataFolder(), fileName + ".yml");

        if(!file.exists()){
            try {
                file.createNewFile();

                if(fileName.equals("config")){
                    Configuration config = getConfig(fileName);
                    config.set("global.maintenance", "off");

                    saveConfig(config, fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Configuration getConfig(String fileName){
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.instance.getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveConfig(Configuration config, String fileName){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(this.instance.getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}