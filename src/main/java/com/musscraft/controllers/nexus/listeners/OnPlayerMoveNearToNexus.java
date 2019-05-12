package com.musscraft.controllers.nexus.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.nexus.NexusController;
import com.musscraft.controllers.nexus.NexusManager;
import com.musscraft.controllers.nexus.exceptions.NexusDoesntExistsException;
import com.musscraft.controllers.nexus.repositories.models.Nexus;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveNearToNexus implements Listener {
    private NexusManager nexusManager;

    public OnPlayerMoveNearToNexus(Main plugin) {
        NexusController nexusController = plugin.getNexusController(plugin);
        this.nexusManager = nexusController.getNexusManager();
    }

    @EventHandler
    public void OnPlayerMoveNearToNexus(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        try {
            Nexus nexus = nexusManager.findNexus(player, 10);
            player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    new ComponentBuilder("[Você está nas redondezas do Nexus " + nexus.getName() + "]")
                            .color(ChatColor.AQUA).create()
            );
        } catch (NexusDoesntExistsException ex) {
        }
    }
}
