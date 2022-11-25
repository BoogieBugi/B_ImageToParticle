package com.bugi.b_imagetoparticle;

import org.bukkit.*;
import org.bukkit.Color;

public class ImageParticle {
    private World world = null;
    private Location loc = null;

    private int[][][] imageData = null;

    public ImageParticle(World world, Location loc, String imageName) {
        this.world = world;
        this.loc = loc;

        this.imageData = ImageLoader.getImageData(imageName);
    }

    /**
     * Spawn particle.
     *
     * @param rotateY Vertical angle of rotation
     * @param rotateP Horizontal angle of rotation
     * @param interval Interval between particles
     * @param size Size of particle
     */
    public void spawnParticle(float rotateY, float rotateP, float interval, float size) {
        float height = imageData.length;
        float width = imageData[0].length;
        Location cricLoc = loc.clone().subtract(0, height / 2 * interval * -1, width / 2 * interval);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] rgb = imageData[y][x];
                if (rgb[3] != 255) {
                    continue;
                }

                Location spawnLoc = cricLoc.clone().add(0, y * -interval, x * interval);
                double spawnX = spawnLoc.getX();
                double spawnY = spawnLoc.getY();
                double spawnZ = spawnLoc.getZ();
                double centerX = loc.getX();
                double centerY = loc.getY();
                double centerZ = loc.getZ();
                double radianY = Math.toRadians(rotateY);
                double radianP = Math.toRadians(rotateP);
                spawnLoc.setY((spawnX - centerX) * Math.sin(radianP) + (spawnY - centerY) * Math.cos(radianP) + centerY);
                spawnX = (spawnX - centerX) * Math.cos(radianP) - (spawnY - centerY) * Math.sin(radianP) + centerX;
                spawnLoc.setZ((spawnZ - centerZ) * Math.cos(radianY) - (spawnX - centerX) * Math.sin(radianY) + centerZ);
                spawnLoc.setX((spawnZ - centerZ) * Math.sin(radianY) + (spawnX - centerX) * Math.cos(radianY) + centerX);
                world.spawnParticle(Particle.REDSTONE, spawnLoc, 1, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(rgb[0], rgb[1], rgb[2]), size));
            }
        }
    }

    /**
     * Set location of particle.
     *
     * @param loc Location of particle
     * @return Previous location
     */
    public Location setLocation(Location loc) {
        Location beforeLoc = this.loc;
        this.loc = loc;
        return beforeLoc;
    }
}
