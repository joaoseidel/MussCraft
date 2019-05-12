package com.musscraft.controllers.mussPlayer.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.exceptions.MussPlayerNotFoundException;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MussPlayerMoveListener implements Listener {
    private MussPlayerManager mussPlayerManager;
    private MussPlayerController mussPlayerController;

    public MussPlayerMoveListener(Main plugin) {
        this.mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
    }

    @EventHandler
    public void onMussPlayerMove(PlayerMoveEvent e) throws MussPlayerNotFoundException {
        Player player = e.getPlayer();

        MussPlayer mussPlayer = mussPlayerManager.findMussPlayer(player.getName());
        if (!mussPlayer.isLogged()) {
            e.setCancelled(true);
        }
    }
}
