package io.seidel.musscraft.services.vo;

import io.seidel.musscraft.database.models.MussPlayer;
import org.bukkit.Location;

public class HomeCreateVO {

    private String name;

    private MussPlayer owner;

    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MussPlayer getOwner() {
        return owner;
    }

    public void setOwner(MussPlayer owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
