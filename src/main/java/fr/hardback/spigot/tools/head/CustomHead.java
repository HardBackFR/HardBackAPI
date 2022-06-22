package fr.hardback.spigot.tools.head;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

public class CustomHead {

    public static ItemStack create(String base64) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        ItemMeta headM = head.getItemMeta();

        try {
            Field field = Objects.requireNonNull(headM).getClass().getDeclaredField("profile");
            field.setAccessible(true);

            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            PropertyMap propertyMap = profile.getProperties();

            if (propertyMap == null)
                throw new IllegalStateException("Profile doesn't contain a property map");

            byte[] encodedData = base64.getBytes();
            propertyMap.put("textures", new Property("textures", new String(encodedData)));

            field.set(headM, profile);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        head.setItemMeta(headM);
        return head;

    }
}
