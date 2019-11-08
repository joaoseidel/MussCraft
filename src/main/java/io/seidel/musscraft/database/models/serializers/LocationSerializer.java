package io.seidel.musscraft.database.models.serializers;

import com.dieselpoint.norm.serialize.DbSerializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationSerializer implements DbSerializable {
    public String serialize(Object location) {
        return location.toString();
    }

    public Object deserialize(String serializedLocation, Class<?> aClass) {
        String[] deserializedLocation = serializedLocation.split(",");
        String worldName = deserializedLocation[0].split("name=")[1].replaceAll("}", "");

        return new Location(
                Bukkit.getWorld(worldName),
                Double.parseDouble(deserializedLocation[1].split("=")[1]),
                Double.parseDouble(deserializedLocation[2].split("=")[1]),
                Double.parseDouble(deserializedLocation[3].split("=")[1]),
                Float.parseFloat(deserializedLocation[4].split("=")[1]),
                Float.parseFloat(deserializedLocation[5].split("=")[1])
        );
    }
}
