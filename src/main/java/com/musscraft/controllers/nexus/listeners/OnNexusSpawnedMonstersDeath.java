package com.musscraft.controllers.nexus.listeners;

import com.musscraft.Main;
import com.musscraft.controllers.nexus.NexusManager;
import com.musscraft.controllers.nexus.repositories.models.Nexus;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class OnNexusSpawnedMonstersDeath implements Listener {
    private NexusManager nexusManager;

    public OnNexusSpawnedMonstersDeath(Main plugin) {
        this.nexusManager = plugin.getNexusController(plugin).getNexusManager();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        List<Nexus> nexusWithSpawnedMonsters = nexusManager.findNexusWithSpawnedMonsters();
        if (nexusWithSpawnedMonsters.isEmpty())
            return;

        nexusWithSpawnedMonsters.forEach(nexus -> {
            List<Entity> monsters = nexus.getSpawnedMonsters();
            Entity deadEntity = e.getEntity();
            if (monsters.contains(deadEntity)) {
                e.getDrops().clear();
                e.setDroppedExp(0);

                nexus.getSpawnedMonsters().remove(deadEntity);
                nexusManager.saveOrUpdate(nexus);
            }
        });
    }
}
