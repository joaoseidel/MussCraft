package com.musscraft;

import com.musscraft.controllers.mussPlayer.listeners.MussPlayerJoinListener;
import com.musscraft.controllers.mussPlayer.listeners.MussPlayerMoveListener;
import com.musscraft.controllers.mussPlayer.listeners.MussPlayerQuitListener;
import com.musscraft.controllers.nexus.listeners.OnNexusMinionDeath;
import com.musscraft.controllers.nexus.listeners.OnPlayerInteractWithNexus;
import com.musscraft.controllers.nexus.listeners.OnPlayerMoveNearToNexus;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {
    private Main plugin;

    private PluginManager pluginManager;

    public ListenerManager(Main plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
    }

    public void registerMussPlayerListener() {
        pluginManager.registerEvents(new MussPlayerJoinListener(plugin), plugin);
        pluginManager.registerEvents(new MussPlayerMoveListener(plugin), plugin);
        pluginManager.registerEvents(new MussPlayerQuitListener(plugin), plugin);
    }

    public void registerNexusListeners() {
        pluginManager.registerEvents(new OnPlayerInteractWithNexus(plugin), plugin);
        pluginManager.registerEvents(new OnPlayerMoveNearToNexus(plugin), plugin);
        pluginManager.registerEvents(new OnNexusMinionDeath(plugin), plugin);
    }

    public void unregisterAll() {
        HandlerList.unregisterAll(plugin);
    }
}
