package com.musscraft.controllers.mussPlayer.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.models.MussPlayer;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MussPlayerJoinListener implements Listener {
    private MussPlayerRepository mussPlayerRepository;
    private MussPlayerManager mussPlayerManager;
    private MussPlayerController mussPlayerController;

    public MussPlayerJoinListener(Main plugin) {
        this.mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
        this.mussPlayerRepository = mussPlayerController.getMussPlayerRepository();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");

        Player player = e.getPlayer();
        String playerName = player.getName();

        MussPlayer mussPlayer = new MussPlayer(playerName);

        if (!mussPlayerRepository.exists(playerName)) {
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', "&aUse /registrar <senha> <e-mail>")
            );

            mussPlayer.setRegistered(false);
            mussPlayerManager.add(mussPlayer);
            return;
        }

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&aUse /login <senha>")
        );

        mussPlayer.setRegistered(true);
        mussPlayerManager.add(mussPlayer);
    }
}
