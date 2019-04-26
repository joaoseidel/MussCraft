package com.musscraft.controllers.MussPlayerController.Listeners;

import com.musscraft.Main;
import com.musscraft.controllers.MussPlayerController.MussPlayerController;
import com.musscraft.controllers.MussPlayerController.MussPlayerManager;
import com.musscraft.exceptions.MussPlayerExceptions.MussPlayerNotFoundException;
import com.musscraft.models.MussPlayer;
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
    public void onMussPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        try {
            MussPlayer mussPlayer = mussPlayerManager.findMussPlayerByPlayer(player);
            if (!mussPlayer.isLogged()) {
                e.setCancelled(true);
            }
        } catch (MussPlayerNotFoundException ex) {
            e.setCancelled(true);
        }
    }
}
