package com.musscraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.NumberConversions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocationUtils {
    public static String serializeLocation(Location location) {
        return location.toString();
    }

    public static Location getDeserializedLocation(String serializedLocation) {
        String[] deserializedLocationString = serializedLocation.split(",");
        String worldName = deserializedLocationString[0].split("name=")[1].replaceAll("}", "");

        return new Location(
                Bukkit.getWorld(worldName),
                NumberConversions.toDouble(deserializedLocationString[1].split("=")[1]),
                NumberConversions.toDouble(deserializedLocationString[2].split("=")[1]),
                NumberConversions.toDouble(deserializedLocationString[3].split("=")[1]),
                NumberConversions.toFloat(deserializedLocationString[4].split("=")[1]),
                NumberConversions.toFloat(deserializedLocationString[5].split("=")[1])
        );
    }

    public static Location randomLocationWithinRadius(Location location, int radius) {
        Random random = new Random();
        Location center = location.clone();
        World locationWorld = center.getWorld();

        double diameter = radius*2;
        double x = center.getX() + (random.nextDouble()*diameter - radius);
        double z = center.getZ() + (random.nextDouble()*diameter - radius);

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
