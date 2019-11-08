package io.seidel.musscraft.helpers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocationHelper {
    public static Location getRandomLocationWithinRadius(Location location, int radius) {
        Random random = new Random();
        Location center = location.clone();
        World locationWorld = center.getWorld();

        double diameter = radius * 2;
        double x = center.getX() + (random.nextDouble() * diameter - radius);
        double z = center.getZ() + (random.nextDouble() * diameter - radius);

        return new Location(
                locationWorld, x,
                locationWorld.getHighestBlockYAt(center), z);
    }

    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

}