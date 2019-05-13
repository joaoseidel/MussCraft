package com.musscraft.controllers.nexus.repositories.models;

import com.musscraft.controllers.nexus.NexusController;
import com.musscraft.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class Nexus {
    private UUID uid;
    private UUID creatorUid;
    private String name;
    private Double health;
    private Integer power;
    private Date destroyedDate;
    private Location location;
    private BossBar bossBar;
    private List<Entity> minions = new ArrayList<>();

    private static final List<EntityType> minionsType = Arrays.asList(
            EntityType.BLAZE, EntityType.ZOMBIE, EntityType.SKELETON,
            EntityType.SPIDER, EntityType.PIG_ZOMBIE
    );

    public Nexus() {
        BossBar bossBar = Bukkit.createBossBar(
                this.getName(),
                BarColor.RED,
                BarStyle.SEGMENTED_20,
                BarFlag.CREATE_FOG,
                BarFlag.DARKEN_SKY,
                BarFlag.PLAY_BOSS_MUSIC
        );

        this.setBossBar(bossBar);
    }

    public Nexus(String name) {
        this();
        this.name = name;
    }

    public Nexus(UUID uuid) {
        this();
        this.uid = uuid;
    }

    public UUID getUid() {
        return uid;
    }

    public Nexus setUid(UUID uid) {
        this.uid = uid;
        return this;
    }

    public UUID getCreatorUid() {
        return creatorUid;
    }

    public Nexus setCreatorUid(UUID creatorUid) {
        this.creatorUid = creatorUid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Nexus setName(String name) {
        this.name = name;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Nexus setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Double getHealth() {
        return health;
    }

    public Nexus setHealth(Double health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public Nexus setPower(Integer power) {
        this.power = power;
        return this;
    }

    public Date getDestroyedDate() {
        return destroyedDate;
    }

    public Nexus setDestroyedDate(Date destroyedDate) {
        this.destroyedDate = destroyedDate;
        return this;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public Nexus setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
        return this;
    }

    public List<Entity> getMinions() {
        return minions;
    }

    public Nexus setMinions(List<Entity> minions) {
        this.minions = minions;
        return this;
    }

    public void clearMinions() {
        if (getMinions().isEmpty())
            return;

        getMinions().forEach(Entity::remove);
        getMinions().clear();
    }

    public void hit() {
        Integer power = getPower();
        double health = getHealth();
        double damagePower = NexusController.PLAYER_DAMAGE - ((NexusController.PLAYER_DAMAGE * power) / 100);
        double damage = damagePower <= 0.1 ? (0.1) : (damagePower);
        double finalHealth = health - damage;

        setHealth(finalHealth <= 0.0 ? (0) : (finalHealth));
    }

    public void spawnMinions(Player minionAttackTarget, int chance, int minimum, int maximum) {
        Random random = new Random();
        boolean isTimeToSpawnMinions = random.nextInt(99) + 1 <= chance;

        if (isTimeToSpawnMinions) {
            for (int i = 0; i < random.nextInt(maximum) + minimum; i++) {
                World nexusWorld = getLocation().getWorld();
                Location randomSpawnLocation =
                        LocationUtils.randomLocationWithinRadius(getLocation(), 10);

                generateMinions(nexusWorld, randomSpawnLocation).setTarget(minionAttackTarget);
                nexusWorld.createExplosion(randomSpawnLocation, 0);
            }
        }
    }

    private Creature generateMinions(World nexusWorld, Location randomSpawnLocation) {
        Random random = new Random();

        Creature minion = (Creature) nexusWorld.spawnEntity(
                randomSpawnLocation,
                minionsType.get(random.nextInt(minionsType.size()))
        );

        minion.setCustomName(
                ChatColor.translateAlternateColorCodes('&', "&c" + getName() + " minion")
        );
        minion.setCustomNameVisible(true);
        minion.setCanPickupItems(false);
        minion.setLootTable(null);

        getMinions().add(minion);
        return minion;
    }

    public boolean isDestroyed() {
        if (getHealth() <= 0) {
            BossBar bossBar = getBossBar();

            setDestroyedDate(new Date());
            getLocation().getWorld().createExplosion(location, 0);
            clearMinions();
            bossBar.removeAll();
            return true;
        }

        return false;
    }

    public void showHealth(Player player) {
        BossBar bossBar = getBossBar();

        final double maxBarbossPercentual = 1.0;
        double finalHealth = (maxBarbossPercentual * getHealth()) / 100;

        if (bossBar.getPlayers().contains(player)) {
            bossBar.setProgress(finalHealth);
            return;
        }

        bossBar.setTitle(getName());
        bossBar.setProgress(finalHealth);
        bossBar.setVisible(true);
        bossBar.addPlayer(player);
    }

    public void hideHealth(Player player) {
        BossBar bossBar = getBossBar();

        if (bossBar.getPlayers().contains(player)) {
            bossBar.removePlayer(player);
        }
    }

    @Override
    public String toString() {
        return uid.toString()
                .concat("@")
                .concat(name);
    }
}
