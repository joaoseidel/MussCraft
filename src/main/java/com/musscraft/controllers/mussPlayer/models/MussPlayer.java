package com.musscraft.controllers.mussPlayer.models;

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

    public MussPlayer() {

    }

    public MussPlayer(UUID uuid) {
        this();
        setUid(uuid);
    }

    public MussPlayer(String username) {

        setUsername(username);
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
