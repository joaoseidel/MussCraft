package com.musscraft.controllers.nexus.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.nexus.NexusManager;
import com.musscraft.controllers.nexus.repositories.models.Nexus;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class OnNexusMinionDeath implements Listener {
    private NexusManager nexusManager;

    public OnNexusMinionDeath(Main plugin) {
        this.nexusManager = plugin.getNexusController(plugin).getNexusManager();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        List<Nexus> nexusWithSpawnedMinions = nexusManager.findNexusWithSpawnedMinions();
        if (nexusWithSpawnedMinions.isEmpty())
            return;

        nexusWithSpawnedMinions.forEach(nexus -> {
            List<Entity> minions = nexus.getMinions();
            Entity deadMinionEntity = e.getEntity();

            if (minions.contains(deadMinionEntity)) {
                e.getDrops().clear();
                e.setDroppedExp(0);

                nexus.getMinions().remove(deadMinionEntity);
                nexusManager.saveOrUpdate(nexus);
            }
        });
    }
}
