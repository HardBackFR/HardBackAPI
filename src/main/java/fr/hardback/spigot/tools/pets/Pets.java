package fr.hardback.spigot.tools.pets;

import org.bukkit.ChatColor;

public enum Pets {

    RubiksCube("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGY5MjMzYzEyNDdlMDNlOWZkMjc3NDI3MzdlNzllNGNjZWJkMjI1YTliMDU5ZDU5NmQ1Y2QzNGUyNmYyMTY1In19fQ==", ChatColor.AQUA + "" + ChatColor.BOLD + "Rubik's Cube"),
    Turtle("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFlOTAzNzEzN2I2Mjg4NDJhMjkzODBmODI4YzI2ZWM2NjE4NjY0YzM4MDJjOTk0NWY0YzAxYTk0M2FhNDBlOSJ9fX0=", ChatColor.GREEN + "" + ChatColor.BOLD + "Turtle");

    private final String headURL, name;

    Pets(String headURL, String name){
        this.headURL = headURL;
        this.name = name;
    }

    public String getHeadURL() {
        return headURL;
    }

    public String getName() {
        return name;
    }
}