package fr.hardback.spigot.tools.firework;

import fr.hardback.spigot.tools.color.ColorUtils;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class CustomFirework {

    public static void launchFirework(Player player) {
        Firework firework = player.getWorld().spawn(player.getEyeLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.addEffects(FireworkEffect.builder().withColor(ColorUtils.getRandomColor()).withColor(ColorUtils.getRandomColor()).with(Type.BALL_LARGE).withFlicker().build());
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);
    }
}
