package com.musscraft.controllers.mussPlayer;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.models.MussPlayer;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import com.musscraft.utils.PasswordUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MussPlayerController {
    private Main plugin;

    private MussPlayerManager mussPlayerManager;
    private MussPlayerRepository mussPlayerRepository;

    public MussPlayerController(Main plugin) {
        this.plugin = plugin;
        this.mussPlayerManager = new MussPlayerManager();
        this.mussPlayerRepository = new MussPlayerRepository();
    }

    public MussPlayerManager getMussPlayerManager() {
        return mussPlayerManager;
    }

    public MussPlayerRepository getMussPlayerRepository() {
        return mussPlayerRepository;
    }

    public void registerMussPlayer(MussPlayer mussPlayer, String password, String email) {
        mussPlayer.setPassword(PasswordUtils.hashPassword(password));
        mussPlayer.setEmail(email);
        mussPlayer.setMoney(30.0);
        mussPlayer.setExperience(0.0);
        mussPlayer.setLogged(true);
        mussPlayer.setRegistered(true);

        mussPlayerRepository.add(mussPlayer);
        mussPlayerManager.saveOrUpdate(mussPlayer);
    }

    public boolean attempLogin(MussPlayer mussPlayer, String password) {
        if (PasswordUtils.comparePassword(password, mussPlayer.getPassword())) {
            mussPlayer.setLogged(true);
            mussPlayer.setRegistered(true);
            mussPlayerManager.saveOrUpdate(mussPlayer);
            mussPlayerRepository.saveOrUpdate(mussPlayer);
            return true;
        }
        return false;
    }

    public void changeMussPlayerPassword(MussPlayer mussPlayer, String oldPassword, String newPassword) throws Exception {
        if (!PasswordUtils.comparePassword(oldPassword, mussPlayer.getPassword())) {
            throw new Exception("A senha antiga digitada não confere com sua senha atual.");
        }

        if (PasswordUtils.comparePassword(newPassword, mussPlayer.getPassword())) {
            throw new Exception("A senha nova digitada é igual a antiga.");
        }

        mussPlayer.setPassword(PasswordUtils.hashPassword(newPassword));
        mussPlayerManager.saveOrUpdate(mussPlayer);
        mussPlayerRepository.saveOrUpdate(mussPlayer);
    }

    public void prepareLoginSpawn(Player player) {
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
        Bukkit.getOnlinePlayers().forEach(p -> player.hidePlayer(plugin, p));
    }

    public void doLoginSpawn(Player player) {
        MussPlayer mussPlayer = mussPlayerManager.findMussPlayerByPlayer(player);

        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(mussPlayer.getLocation());
        Bukkit.getOnlinePlayers().forEach(p -> player.showPlayer(plugin, p));
    }
}
