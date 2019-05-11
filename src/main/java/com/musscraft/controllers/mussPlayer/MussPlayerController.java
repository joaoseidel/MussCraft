package com.musscraft.controllers.mussPlayer;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;

public class MussPlayerController {
    private Main plugin;

    private MussPlayerManager mussPlayerManager;
    private MussPlayerRepository mussPlayerRepository;

    public MussPlayerController(Main plugin) {
        this.plugin = plugin;
        this.mussPlayerManager = new MussPlayerManager();
        this.mussPlayerRepository = new MussPlayerRepository(plugin);
    }

    public MussPlayerManager getMussPlayerManager() {
        return mussPlayerManager;
    }

    public MussPlayerRepository getMussPlayerRepository() {
        return mussPlayerRepository;
    }
}
