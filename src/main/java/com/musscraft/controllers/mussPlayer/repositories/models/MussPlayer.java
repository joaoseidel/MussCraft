package com.musscraft.controllers.mussPlayer.repositories.models;

import org.bukkit.Location;

import java.util.UUID;

public class MussPlayer {
    private UUID uid;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
