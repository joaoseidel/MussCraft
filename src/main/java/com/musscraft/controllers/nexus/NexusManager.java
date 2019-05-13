package com.musscraft.controllers.nexus;

import com.musscraft.controllers.nexus.exceptions.NexusDoesntExistsException;
import com.musscraft.controllers.nexus.repositories.models.Nexus;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NexusManager {
    private  List<Nexus> nexusList = new ArrayList<>();

    public Nexus findNexus(UUID uuid) throws NexusDoesntExistsException {
        for (Nexus nexus : nexusList) {
            if (nexus.getUid().equals(uuid))
                return nexus;
        }

        throw new NexusDoesntExistsException();
    }

    public Nexus findNexus(Location location) throws NexusDoesntExistsException {
        for (Nexus nexus : nexusList) {
            if (location.equals(nexus.getLocation()))
                return nexus;
        }

        throw new NexusDoesntExistsException();
    }

    public Nexus findNexus(Player player, int radius) throws NexusDoesntExistsException {
        for (Nexus nexus : nexusList) {
            if (player.getLocation().distance(nexus.getLocation()) <= radius)
                return nexus;
        }

        throw new NexusDoesntExistsException();
    }

    public List<Nexus> findNexusWithSpawnedMinions() {
        List<Nexus> nexusWithSpawnedMinions = new ArrayList<>();
        nexusList.forEach(nexus -> {
            if (!nexus.getMinions().isEmpty())
                nexusWithSpawnedMinions.add(nexus);
        });

        return nexusWithSpawnedMinions;
    }

    public List<Nexus> listAll() {
        return nexusList;
    }

    public Nexus add(Nexus nexus) {
        nexusList.add(nexus);
        return nexus;
    }

    public void remove(Nexus nexus) {
        nexusList.remove(nexus);
    }

    public Nexus saveOrUpdate(Nexus nexus) {
        if (nexusList.contains(nexus)) remove(nexus);
        return add(nexus);
    }
}
