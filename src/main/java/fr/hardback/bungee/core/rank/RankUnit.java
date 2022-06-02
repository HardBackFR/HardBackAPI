package fr.hardback.bungee.core.rank;

import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum RankUnit {

    Onehead("1head", ChatColor.BLACK + "[1HEAD] ", 1, false),
    Joueur("Joueur", ChatColor.GRAY + "[Joueur] ", 2, false),
    VIP("VIP", ChatColor.YELLOW + "[VIP] ", 3, false),
    VIPP("VIPP", ChatColor.GOLD + "[VIP+] ", 4, false),
    Friend("Friend", ChatColor.LIGHT_PURPLE + "[Friend] ", 5, false),
    Partenaire("Partenaire", ChatColor.AQUA + "[Partenaire] ", 6, false),
    Builder("Builder", ChatColor.DARK_GREEN + "[Builder] ", 7, true),
    Assistant("Assistant", ChatColor.GREEN + "[Assistant] ", 8, true),
    Moderateur("Moderateur", ChatColor.BLUE + "[Modérateur] ", 9, true),
    Developpeur("Developpeur", ChatColor.DARK_PURPLE + "[Développeur] ", 10, true),
    Responsable("Responsable", ChatColor.RED + "[Responsable] ", 11, true),
    Admin("Admin", ChatColor.DARK_RED+"[Admin] ", 12, true);


    private final String name, prefix;
    private final int power;
    private final boolean isStaff;

    RankUnit(String name, String prefix, int power, boolean isStaff) {
        this.name = name;
        this.prefix = prefix;
        this.power = power;
        this.isStaff = isStaff;
    }

    public static Map<Integer, RankUnit> rank = new HashMap<>();

    static{
        for(RankUnit ranks : RankUnit.values()){
            rank.put(ranks.getPower(), ranks);
        }
    }

    public static RankUnit getByName(String name){
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(RankUnit.Joueur);
    }

    public static RankUnit getByPower(int power){
        return Arrays.stream(values()).filter(r -> r.getPower() == power).findAny().orElse(RankUnit.Joueur);
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPower() {
        return power;
    }

    public boolean isStaff() {
        return isStaff;
    }
}
