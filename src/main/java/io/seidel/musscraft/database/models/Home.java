package io.seidel.musscraft.database.models;

import com.dieselpoint.norm.serialize.DbSerializer;
import io.seidel.musscraft.database.serializers.LocationSerializer;
import org.bukkit.Location;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Home")
public class Home extends AbstractBaseEntity {

    private String name;

    private MussPlayer player;

    private boolean isRemoved;

    private boolean isPublic;

    @DbSerializer(LocationSerializer.class)
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MussPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MussPlayer player) {
        this.player = player;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        this.isRemoved = removed;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
