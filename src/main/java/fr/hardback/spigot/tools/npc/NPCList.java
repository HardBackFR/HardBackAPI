package fr.hardback.spigot.tools.npc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public enum NPCList {

    NAVIGATEUR(ChatColor.GOLD + "Navigateur", "ewogICJ0aW1lc3RhbXAiIDogMTY1NDk3MzkxNTM0OCwKICAicHJvZmlsZUlkIiA6ICJhMGM1MjYwOWRmOTA0NzVkOTNmM2U4YWQ0YWI5Nzg2YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbGxhaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lODkyMTZhODI4MGM2YWY4NzViZDUyMGI3MDJjZjFkNTNlZjU1N2ZiYjI4ZWQ3OWJjZGI4MTVkZTMxYWE1NzM1IgogICAgfQogIH0KfQ==", "QhTrQLQZauSqtTuQGngCLhcvLiiJILC4bEM3IyriN8BiIN4GkY/VPiKoODLq5CbqY94/DYyQesWRs1Xov7w8eVlYt/vdXCNBwVBYR9FYaa0AfowWYWXj8yWHBW86cU4VRYxDvRH3/pzeIWtO0xStjGFG94Kzq9UdSIaruVKkpW2Bqz69pgBIPIznwbEw85U2Zr1fjxcqm+0+TCuZhYyK6EJo7r7+WCCFrIWVV42nJPWqC8eIjpqa0D4ljADrzsD/8yHJY78v7Zf1cE8IVlNVh39krsq8ZarnECGWbVGeDhE/kPOe4ifT7KYF7mpahmUBYIt043sN/FOpzkRO8ZUXkgd7uotFyNqAnSEx8nFv21kRXMENHLnFi6z0SmBxyXGMe+odkm7szEoplyL6+Wmem3FW/T6NvMNRoajuPUV+dO2DTwZXPL8s+OaZACI0fAKxn9VE/oicyA90qUOEOnRyNapYjnugDwuMVT7U6Jw1UzygN/VgdgRJpQJ1BG55yYC1lzmg/BT+wRyjQ5onRYYo4W3GllO5IlbjC8WLAlxWo4+s0o5UGlsCjrXDlTGCShWQ1zuNfA6KH9HJt1NBXGfkEfQuPsaOC9nl9L+6efMBnzAMV6oAUoQ8dmk0u1vVUT5xgv1Imqi3RDLNLlmppVpJKUrWSPcViJKCD04ryX7Mbdg=", new ItemStack(Material.COMPASS), new Location(Bukkit.getWorld("world"), -0.477, 100.0, 0.497, -179.8f, 1.6f)),
    EVOLUTION(ChatColor.GREEN + "Evolution", "ewogICJ0aW1lc3RhbXAiIDogMTY1NDk3MzkxNTM0OCwKICAicHJvZmlsZUlkIiA6ICJhMGM1MjYwOWRmOTA0NzVkOTNmM2U4YWQ0YWI5Nzg2YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbGxhaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lODkyMTZhODI4MGM2YWY4NzViZDUyMGI3MDJjZjFkNTNlZjU1N2ZiYjI4ZWQ3OWJjZGI4MTVkZTMxYWE1NzM1IgogICAgfQogIH0KfQ==", "QhTrQLQZauSqtTuQGngCLhcvLiiJILC4bEM3IyriN8BiIN4GkY/VPiKoODLq5CbqY94/DYyQesWRs1Xov7w8eVlYt/vdXCNBwVBYR9FYaa0AfowWYWXj8yWHBW86cU4VRYxDvRH3/pzeIWtO0xStjGFG94Kzq9UdSIaruVKkpW2Bqz69pgBIPIznwbEw85U2Zr1fjxcqm+0+TCuZhYyK6EJo7r7+WCCFrIWVV42nJPWqC8eIjpqa0D4ljADrzsD/8yHJY78v7Zf1cE8IVlNVh39krsq8ZarnECGWbVGeDhE/kPOe4ifT7KYF7mpahmUBYIt043sN/FOpzkRO8ZUXkgd7uotFyNqAnSEx8nFv21kRXMENHLnFi6z0SmBxyXGMe+odkm7szEoplyL6+Wmem3FW/T6NvMNRoajuPUV+dO2DTwZXPL8s+OaZACI0fAKxn9VE/oicyA90qUOEOnRyNapYjnugDwuMVT7U6Jw1UzygN/VgdgRJpQJ1BG55yYC1lzmg/BT+wRyjQ5onRYYo4W3GllO5IlbjC8WLAlxWo4+s0o5UGlsCjrXDlTGCShWQ1zuNfA6KH9HJt1NBXGfkEfQuPsaOC9nl9L+6efMBnzAMV6oAUoQ8dmk0u1vVUT5xgv1Imqi3RDLNLlmppVpJKUrWSPcViJKCD04ryX7Mbdg=",new ItemStack(Material.DIAMOND_SWORD), new Location(Bukkit.getWorld("world"), 3.526, 100.0, 4.410, -178.4f, -2.7f)),
    KILLSKILL(ChatColor.GREEN + "KillSkill", "ewogICJ0aW1lc3RhbXAiIDogMTY1NDk3MzkxNTM0OCwKICAicHJvZmlsZUlkIiA6ICJhMGM1MjYwOWRmOTA0NzVkOTNmM2U4YWQ0YWI5Nzg2YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbGxhaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lODkyMTZhODI4MGM2YWY4NzViZDUyMGI3MDJjZjFkNTNlZjU1N2ZiYjI4ZWQ3OWJjZGI4MTVkZTMxYWE1NzM1IgogICAgfQogIH0KfQ==", "QhTrQLQZauSqtTuQGngCLhcvLiiJILC4bEM3IyriN8BiIN4GkY/VPiKoODLq5CbqY94/DYyQesWRs1Xov7w8eVlYt/vdXCNBwVBYR9FYaa0AfowWYWXj8yWHBW86cU4VRYxDvRH3/pzeIWtO0xStjGFG94Kzq9UdSIaruVKkpW2Bqz69pgBIPIznwbEw85U2Zr1fjxcqm+0+TCuZhYyK6EJo7r7+WCCFrIWVV42nJPWqC8eIjpqa0D4ljADrzsD/8yHJY78v7Zf1cE8IVlNVh39krsq8ZarnECGWbVGeDhE/kPOe4ifT7KYF7mpahmUBYIt043sN/FOpzkRO8ZUXkgd7uotFyNqAnSEx8nFv21kRXMENHLnFi6z0SmBxyXGMe+odkm7szEoplyL6+Wmem3FW/T6NvMNRoajuPUV+dO2DTwZXPL8s+OaZACI0fAKxn9VE/oicyA90qUOEOnRyNapYjnugDwuMVT7U6Jw1UzygN/VgdgRJpQJ1BG55yYC1lzmg/BT+wRyjQ5onRYYo4W3GllO5IlbjC8WLAlxWo4+s0o5UGlsCjrXDlTGCShWQ1zuNfA6KH9HJt1NBXGfkEfQuPsaOC9nl9L+6efMBnzAMV6oAUoQ8dmk0u1vVUT5xgv1Imqi3RDLNLlmppVpJKUrWSPcViJKCD04ryX7Mbdg=", new ItemStack(Material.GOLDEN_SWORD),new Location(Bukkit.getWorld("world"), -4.482, 100.0, 4.382, -179.8f, 1f));

    private final String name, texture, signature;
    private final ItemStack itemStack;
    private final Location location;

    public static final String SUB_TITLE = ChatColor.GRAY + "CLIC-DROIT";

    NPCList(String name, String texture, String signature, ItemStack itemStack, Location location) {
        this.name = name;
        this.texture = texture;
        this.signature = signature;
        this.itemStack = itemStack;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getTexture() {
        return texture;
    }

    public String getSignature() {
        return signature;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Location getLocation() {
        return location;
    }
}
