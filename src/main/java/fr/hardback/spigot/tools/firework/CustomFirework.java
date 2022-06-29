package fr.hardback.spigot.tools.firework;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.concurrent.ThreadLocalRandom;

public class CustomFirework {

    public static void launchFirework(Player player) {
        final Firework firework = player.getWorld().spawn(player.getEyeLocation(), Firework.class);
        final FireworkMeta fireworkMeta = firework.getFireworkMeta();

        final Color[] COLORS = new Color[]{
                Color.AQUA,
                Color.BLACK,
                Color.BLUE,
                Color.FUCHSIA,
                Color.GRAY,
                Color.GREEN,
                Color.LIME,
                Color.MAROON,
                Color.NAVY,
                Color.OLIVE,
                Color.ORANGE,
                Color.PURPLE,
                Color.RED,
                Color.SILVER,
                Color.TEAL,
                Color.WHITE,
                Color.YELLOW
        };

        fireworkMeta.addEffects(FireworkEffect.builder().withColor(COLORS[ThreadLocalRandom.current().nextInt(COLORS.length)]).withColor(COLORS[ThreadLocalRandom.current().nextInt(COLORS.length)]).with(Type.BALL_LARGE).withFlicker().build());
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);
    }
}
