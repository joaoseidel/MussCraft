package com.musscraft.controllers.nexus.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.nexus.NexusController;
import com.musscraft.controllers.nexus.NexusManager;
import com.musscraft.controllers.nexus.exceptions.NexusDoesntExistsException;
import com.musscraft.controllers.nexus.repositories.models.Nexus;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.WEAKNESS;

public class OnPlayerInteractWithNexus implements Listener {
    private NexusController nexusController;
    private NexusManager nexusManager;

    public OnPlayerInteractWithNexus(Main plugin) {
        this.nexusController = plugin.getNexusController(plugin);
        this.nexusManager = nexusController.getNexusManager();
    }

    @EventHandler
    public void onPlayerHitNexus(PlayerInteractEvent e) throws NexusDoesntExistsException {
        Player player = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == NexusController.NEXUS_MATERIAL) {
            Location nexusLocation = e.getClickedBlock().getLocation();
            Nexus nexus = nexusManager.findNexus(nexusLocation);

            if (nexus.isDestroyed()) return;
            if (!player.getActivePotionEffects().contains(WEAKNESS)) player.addPotionEffect(
                    new PotionEffect(WEAKNESS, 100, 1)
            );

            nexus.hit();
            nexus.spawnMinions(player, 10, 2, 10);
            nexus.showHealth(player);
            if (nexus.getHealth() <= 0.0) {
                nexus.destroy();
            }
            nexusManager.saveOrUpdate(nexus);
        }
    }
}
