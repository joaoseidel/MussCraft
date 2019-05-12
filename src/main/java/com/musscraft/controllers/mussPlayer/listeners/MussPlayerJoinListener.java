package com.musscraft.controllers.mussPlayer.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class MussPlayerJoinListener implements Listener {
    private MussPlayerRepository mussPlayerRepository;
    private MussPlayerManager mussPlayerManager;

    public MussPlayerJoinListener(Main plugin) {
        MussPlayerController mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
        this.mussPlayerRepository = mussPlayerController.getMussPlayerRepository();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player player = e.getPlayer();

        MussPlayer mussPlayer = new MussPlayer(player.getName());
        mussPlayer.prepareLoginScreen();
        mussPlayerManager.add(mussPlayer);

        if (!mussPlayerRepository.exists(mussPlayer.getUsername())) {
            player.sendMessage(translateAlternateColorCodes('&', "&aUse /registrar <senha> <e-mail>"));
            mussPlayer.setRegistered(false);
            return;
        }

        player.sendMessage(translateAlternateColorCodes('&', "&aUse /login <senha>"));
        mussPlayer.setRegistered(true);
        mussPlayerManager.add(mussPlayer);
    }
}
