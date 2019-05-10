package com.musscraft.controllers.mussPlayer.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.models.MussPlayer;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class MussPlayerQuitListener implements Listener {
    private MussPlayerManager mussPlayerManager;
    private MussPlayerRepository mussPlayerRepository;
    private MussPlayerController mussPlayerController;

    public MussPlayerQuitListener(Main plugin) {
        this.mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
        this.mussPlayerRepository = mussPlayerController.getMussPlayerRepository();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");

        Player player = e.getPlayer();
        MussPlayer mussPlayer = mussPlayerManager.findMussPlayerByPlayer(player);

        if (mussPlayer.isLogged() && mussPlayer.isRegistered()) {
            mussPlayer.setLogged(false);
            mussPlayer.setLocation(player.getLocation());
            mussPlayerRepository.saveOrUpdate(mussPlayer);
        }

        mussPlayerManager.remove(mussPlayer);
    }
}
