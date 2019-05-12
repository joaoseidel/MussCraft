package com.musscraft.controllers.nexus;

import com.musscraft.Main;
import com.musscraft.controllers.nexus.repositories.NexusRepository;
import org.bukkit.Material;

public class NexusController {
    public static final Material NEXUS_MATERIAL = Material.BEDROCK;
    public static final Double PLAYER_DAMAGE = 1.00;
    public static final Double NEXUS_BASE_HEALTH = 100.00;

    private NexusManager nexusManager;
    private NexusRepository nexusRepository;

    public NexusController(Main plugin) {
        this.nexusManager = new NexusManager();
        this.nexusRepository = new NexusRepository(plugin);

        nexusRepository.getAll().forEach(nexus -> nexusManager.add(nexus));
    }

    public void updateAllNexus() {
        nexusManager.listAll().forEach(nexus -> nexusRepository.saveOrUpdate(nexus));
    }

    public NexusManager getNexusManager() {
        return nexusManager;
    }

    public NexusRepository getNexusRepository() {
        return nexusRepository;
    }
}
