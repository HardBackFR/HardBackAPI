package fr.hardback.spigot.tools.color;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;

import net.minecraft.server.v1_8_R3.EnumColor;

public class ColorUtils {

    private static Color[] colors;
    private static DyeColor[] dyeColors;
    private static ChatColor[] chatColors;
    private static EnumColor[] enumColors;

    /*
     * Get a random color of Minecraft colors
     * @return
     */
    public static Color getRandomColor() {
        return ColorUtils.colors[new Random().nextInt(15)];
    }

    /*
     * Get a random color of Minecraft dye colors
     * @return
     */
    public static DyeColor getRandomDyeColor() {
        return ColorUtils.dyeColors[new Random().nextInt(15)];
    }

    /*
     * Get a random color of Minecraft chat colors
     * @return
     */
    public static ChatColor getRandomChatColor() {
        return ColorUtils.chatColors[new Random().nextInt(7)];
    }

    /*
     * Get a random color of Minecraft enum colors (nms)
     * @return
     */
    public static EnumColor getRandomEnumColor() {
        return ColorUtils.enumColors[new Random().nextInt(7)];
    }

    static {
        ColorUtils.colors = new Color[] {
                Color.AQUA,
                Color.BLACK,
                Color.BLUE,
                Color.FUCHSIA,
                Color.GRAY,
                Color.GREEN,
                Color.LIME,
                Color.MAROON,
                Color.NAVY,
                Color.ORANGE,
                Color.PURPLE,
                Color.RED,
                Color.SILVER,
                Color.WHITE,
                Color.YELLOW
        };

        ColorUtils.dyeColors = new DyeColor[] {
                DyeColor.WHITE,
                DyeColor.ORANGE,
                DyeColor.MAGENTA,
                DyeColor.LIGHT_BLUE,
                DyeColor.YELLOW,
                DyeColor.LIME,
                DyeColor.PINK,
                DyeColor.SILVER,
                DyeColor.CYAN,
                DyeColor.PURPLE,
                DyeColor.BLUE,
                DyeColor.BROWN,
                DyeColor.GREEN,
                DyeColor.RED,
                DyeColor.BLACK
        };

        ColorUtils.chatColors = new ChatColor[] {
                ChatColor.WHITE,
                ChatColor.YELLOW,
                ChatColor.GRAY,
                ChatColor.BLUE,
                ChatColor.GREEN,
                ChatColor.RED,
                ChatColor.BLACK
        };

        ColorUtils.enumColors = new EnumColor[] {
                EnumColor.BLACK,
                EnumColor.BLUE,
                EnumColor.BROWN,
                EnumColor.CYAN,
                EnumColor.GRAY,
                EnumColor.GREEN,
                EnumColor.LIGHT_BLUE,
                EnumColor.BLUE,
                EnumColor.LIME,
                EnumColor.MAGENTA,
                EnumColor.ORANGE,
                EnumColor.PINK,
                EnumColor.PURPLE,
                EnumColor.RED,
                EnumColor.SILVER,
                EnumColor.WHITE,
                EnumColor.YELLOW
        };
    }
}
