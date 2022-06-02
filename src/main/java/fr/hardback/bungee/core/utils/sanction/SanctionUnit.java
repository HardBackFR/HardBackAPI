package fr.hardback.bungee.core.utils.sanction;

public enum SanctionUnit {

    BAN("Bannissement"),
    MUTE("Mute"),
    KICK("Kick");

    private final String name;

    SanctionUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
