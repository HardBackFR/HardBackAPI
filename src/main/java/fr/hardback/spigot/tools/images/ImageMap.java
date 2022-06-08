package fr.hardback.spigot.tools.images;

import java.util.List;
import java.util.UUID;

public class ImageMap {

    private final UUID uuid;
    private final String path;
    private final List<Short> mapIds;

    public ImageMap(UUID uuid, String path, List<Short> mapIds) {
        this.uuid = uuid;
        this.path = path;
        this.mapIds = mapIds;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPath() {
        return path;
    }

    public List<Short> getMapIds() {
        return mapIds;
    }
}
