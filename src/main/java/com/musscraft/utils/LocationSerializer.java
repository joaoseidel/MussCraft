package com.musscraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;

public class LocationSerializer {
    public static String serializeLocation(Location location) {
        return location.toString();
    }

    public static Location getDeserializedLocation(String serializedLocation) {
        String[] deserializedLocationString = serializedLocation.split(",");
        String worldName = deserializedLocationString[0].split("name=")[1].replaceAll("}", "");

        Location deserializedLocation = new Location(
                Bukkit.getWorld(worldName),
                NumberConversions.toDouble(deserializedLocationString[1].split("=")[1]),
                NumberConversions.toDouble(deserializedLocationString[2].split("=")[1]),
                NumberConversions.toDouble(deserializedLocationString[3].split("=")[1]),
                NumberConversions.toFloat(deserializedLocationString[4].split("=")[1]),
                NumberConversions.toFloat(deserializedLocationString[5].split("=")[1])
        );

        return deserializedLocation;
    }
}
