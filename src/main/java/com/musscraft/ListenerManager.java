package com.musscraft;

import com.musscraft.controllers.mussPlayer.listeners.MussPlayerJoinListener;
import com.musscraft.controllers.mussPlayer.listeners.MussPlayerMoveListener;
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
    }
}
