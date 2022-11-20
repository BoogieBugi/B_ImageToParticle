package com.bugi.b_imagetoparticle;

import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageLoader {
    private static HashMap<String, int[][][]> imageData = new HashMap<>();

    /**
     * Register RGB data of image in plugin's resources folder.
     *
     * @param plugin Plugin
     * @param imageName Name of image
     * @param fileName Path of image
     * @return True if registeration successed.
     */
    public static boolean registerImage(JavaPlugin plugin, String imageName, String fileName) {
        if (isRegistered(imageName)) {
            B_ImageToParticle.getInstance().getLogger().warning("Image is already registered.");
            return false;
        }

        if(plugin.getResource(fileName) == null) {
            B_ImageToParticle.getInstance().getLogger().warning("Image not found.");
            return false;
        }

        try {
            InputStream inputStream = plugin.getResource(fileName);
            BufferedImage image = ImageIO.read(inputStream);

            int[][][] data = new int[image.getHeight()][image.getWidth()][4];
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Color rgb = new Color(image.getRGB(x, y), true);
                    data[y][x][0] = rgb.getRed();
                    data[y][x][1] = rgb.getGreen();
                    data[y][x][2] = rgb.getBlue();
                    data[y][x][3] = rgb.getAlpha();
                }
            }

            imageData.put(imageName, data);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        B_ImageToParticle.getInstance().getLogger().info("Success to register image.");
        return true;
    }

    /**
     * Unregister RGB data of image.
     *
     * @param imageName Name of image
     * @return True if unregisteration successed
     */
    public static boolean unregisterImage(String imageName) {
        if (!isRegistered(imageName)) {
            B_ImageToParticle.getInstance().getLogger().warning("Image doesn't exist.");
            return false;
        }

        imageData.remove(imageName);
        B_ImageToParticle.getInstance().getLogger().info("Image is now unregistered.");
        return true;
    }

    /**
     * Return if image is already registered.
     * 
     * @param imageName Name of image
     * @return True if image is already registered
     */
    public static boolean isRegistered(String imageName) {
        return imageData.containsKey(imageName);
    }

    /**
     * Get registered image list.
     * 
     * @return Name list of registered image
     */
    public static List<String> getImageList() {
        return new ArrayList<>(imageData.keySet());
    }

    /**
     * Get RGB data of image.
     *
     * @param imageName Name of Image
     * @return RGB data of image.
     */
    public static int[][][] getImageData(String imageName) {
        if (!isRegistered(imageName)) {
            B_ImageToParticle.getInstance().getLogger().warning("Image doesn't exist.");
            return null;
        }

        return imageData.get(imageName);
    }
}
