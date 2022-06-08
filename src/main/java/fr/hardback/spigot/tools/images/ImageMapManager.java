package fr.hardback.spigot.tools.images;

import java.util.ArrayList;
import java.util.List;

public class ImageMapManager {

    private final List<ImageMap> imageMaps;

    public ImageMapManager() {
        this.imageMaps = new ArrayList<>();
    }

    public void addImageMap(ImageMap imageMap){
        this.imageMaps.add(imageMap);
    }

    public void removeImageMap(ImageMap imageMap){
        this.imageMaps.remove(imageMap);
    }

    public List<ImageMap> getImageMaps() {
        return imageMaps;
    }
}
