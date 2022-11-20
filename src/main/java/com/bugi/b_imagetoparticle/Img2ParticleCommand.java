package com.bugi.b_imagetoparticle;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Img2ParticleCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "/img2particle <image> [particleSize] [particleInterval] [rotateY] [rotateP]");
            return false;
        } else {
            if (!sender.hasPermission("img2particle.use.command")) {
                sender.sendMessage(ChatColor.RED + "You don't have permmision to use this command.");
                return false;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                return false;
            }

            if (!ImageLoader.isRegistered(args[0])) {
                sender.sendMessage(ChatColor.RED + args[0] + " not found.");
                return false;
            }

            Player player = (Player) sender;
            ImageParticle imgParticle = new ImageParticle(player.getWorld(), player.getLocation(), args[0]);
            float size = args.length >= 2 ? Float.valueOf(args[1]) : 1.0f;
            float interval = args.length >= 3 ? Float.valueOf(args[2]) : 0.5f;
            float rotateY = args.length >= 4 ? Float.valueOf(args[3]) : 0.0f;
            float rotateP = args.length >= 5 ? Float.valueOf(args[4]) : 0.0f;
            imgParticle.spawnParticle(rotateY, rotateP, interval, size);
            sender.sendMessage("Particle Spawned.");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            List<String> imageList = ImageLoader.getImageList();
            for (String imageName : imageList) {
                if (imageName.toLowerCase().startsWith(args[0].toLowerCase())) {
                    arguments.add(imageName);
                }
            }
            return arguments;
        }
        return null;
    }
}
