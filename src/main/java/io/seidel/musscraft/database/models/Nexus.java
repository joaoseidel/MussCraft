package io.seidel.musscraft.database.models;

import com.dieselpoint.norm.serialize.DbSerializer;
import io.seidel.musscraft.database.models.serializers.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Nexus")
public class Nexus extends AbstractBaseEntity {

    private String name;

    private Double health;

    private Double power;

    private Date destroyedDate;

    @DbSerializer(LocationSerializer.class)
    private Location location;

    private MussPlayer owner;

    @Transient
    private BossBar bossBar;

    private boolean destroyed;

    @Transient
    private List<org.bukkit.entity.Entity> minions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Date getDestroyedDate() {
        return destroyedDate;
    }

    public void setDestroyedDate(Date destroyedDate) {
        this.destroyedDate = destroyedDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MussPlayer getOwner() {
        return owner;
    }

    public void setOwner(MussPlayer owner) {
        this.owner = owner;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public List<org.bukkit.entity.Entity> getMinions() {
        return minions;
    }

    public void setMinions(List<org.bukkit.entity.Entity> minions) {
        this.minions = minions;
    }

    @Override
    public String toString() {
        return super.getUuid()
                .concat("@")
                .concat(name);
    }
}