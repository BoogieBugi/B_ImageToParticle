package com.bugi.b_imagetoparticle;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class B_ImageToParticle extends JavaPlugin implements Listener {
    private static B_ImageToParticle plugin = null;
    public static B_ImageToParticle getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("img2particle").setExecutor(new Img2ParticleCommand());

        ImageLoader.registerImage(this, "img2particle:angel", "angel.png");
        ImageLoader.registerImage(this, "img2particle:pickaxe", "pickaxe.png");
        ImageLoader.registerImage(this, "img2particle:heart", "heart.png");
    }
}
