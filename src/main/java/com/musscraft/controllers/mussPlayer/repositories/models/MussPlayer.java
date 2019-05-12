package com.musscraft.controllers.mussPlayer.repositories.models;

import com.musscraft.Main;
import com.musscraft.utils.PasswordUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MussPlayer {
    private UUID uid;
    private Player player;
    private String username;
    private String password;
    private String email;
    private Double money;
    private Double experience;
    private boolean logged;
    private boolean registered;
    private Location location;

    public MussPlayer(UUID uuid) {
        setUid(uuid);
    }

    public MussPlayer(String username) {
        setUsername(username);
    }

    public UUID getUid() {
        return uid;
    }

    public MussPlayer setUid(UUID uid) {
        this.uid = uid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MussPlayer setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MussPlayer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MussPlayer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Double getMoney() {
        return money;
    }

    public MussPlayer setMoney(Double money) {
        this.money = money;
        return this;
    }

    public Double getExperience() {
        return experience;
    }

    public MussPlayer setExperience(Double experience) {
        this.experience = experience;
        return this;
    }

    public boolean isLogged() {
        return logged;
    }

    public MussPlayer setLogged(Boolean logged) {
        this.logged = logged;
        return this;
    }

    public boolean isRegistered() {
        return registered;
    }

    public MussPlayer setRegistered(boolean registered) {
        this.registered = registered;
        return this;
    }

    public Location getLocation() {
        return this.location;
    }

    public MussPlayer setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public MussPlayer setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public void prepareLoginScreen() {
        Player player = getPlayer();

        player.setAllowFlight(true);
        player.setFlying(true);
        player.teleport(
                new Location(
                        player.getWorld(),
                        0.0,
                        1000.0,
                        0.0
                )
        );
    }

    public MussPlayer removeLoginScreen() {
        Player player = getPlayer();

        setRegistered(true);
        setLogged(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(getLocation());
        return this;
    }

    public MussPlayer populateDefault(boolean logged, boolean registered){
        setPassword(PasswordUtils.hashPassword(password));
        setEmail(email);
        setMoney(30.0);
        setExperience(0.0);
        setLogged(logged);
        setRegistered(registered);
        setLocation(Bukkit.getWorld("world").getSpawnLocation());
        return this;
    }

    public MussPlayer changePassword(String oldPassword, String newPassword) throws Exception {
        if (!PasswordUtils.comparePassword(oldPassword, getPassword())) {
            throw new Exception("A senha antiga digitada não confere com sua senha atual.");
        }

        if (PasswordUtils.comparePassword(newPassword, getPassword())) {
            throw new Exception("A senha nova digitada é igual a antiga.");
        }

        setPassword(PasswordUtils.hashPassword(newPassword));
        return this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
