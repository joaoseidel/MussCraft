package com.musscraft.controllers.mussPlayer.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MussPlayerJoinListener implements Listener {
    private Main plugin;
    private MussPlayerRepository mussPlayerRepository;
    private MussPlayerManager mussPlayerManager;
    private MussPlayerController mussPlayerController;

    public MussPlayerJoinListener(Main plugin) {
        this.plugin = plugin;
        this.mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
        this.mussPlayerRepository = mussPlayerController.getMussPlayerRepository();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");

        Player player = e.getPlayer();
        String playerName = player.getName();

        prepareLoginSpawn(player);

        MussPlayer mussPlayer = new MussPlayer(playerName);
        mussPlayerManager.add(mussPlayer);

        if (!mussPlayerRepository.exists(playerName)) {
            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', "&aUse /registrar <senha> <e-mail>")
            );

            mussPlayer.setRegistered(false);
            return;
        }

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&aUse /login <senha>")
        );

        mussPlayer.setRegistered(true);
        mussPlayerManager.add(mussPlayer);
    }

    private void prepareLoginSpawn(Player player) {
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
}
