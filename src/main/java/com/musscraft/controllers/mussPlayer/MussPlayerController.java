package com.musscraft.controllers.mussPlayer;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.models.MussPlayer;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import com.musscraft.utils.PasswordUtils;

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
        mussPlayer.setPassword(password);
        mussPlayer.setEmail(email);
        mussPlayer.setMoney(30.0);
        mussPlayer.setExperience(0.0);
        mussPlayer.setLogged(true);

        mussPlayerRepository.add(mussPlayer);
        mussPlayerManager.saveOrUpdate(mussPlayer);
    }

    public boolean attempLogin(MussPlayer mussPlayer, String password) {
        if (PasswordUtils.comparePassword(password, mussPlayer.getPassword())) {
            mussPlayer.setLogged(true);
            mussPlayerManager.saveOrUpdate(mussPlayer);
            return true;
        }
        return false;
    }
}
