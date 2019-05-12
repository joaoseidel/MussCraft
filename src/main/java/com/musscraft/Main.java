package com.musscraft;

import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.nexus.NexusController;
import com.musscraft.database.ConnectionFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

@Plugin(name = "MussCraft", version = "1.0.0")
public class Main extends JavaPlugin {
    private ConnectionFactory connectionFactory;
    private MussPlayerController mussPlayerController;
    private NexusController nexusController;

    public void onEnable() {
        connectionFactory = new ConnectionFactory();

        CommandManager commandManager = new CommandManager(this);
        ListenerManager listenerManager = new ListenerManager(this);

        mussPlayerController = new MussPlayerController(this);
        commandManager.registerMussPlayerCommands();
        listenerManager.registerMussPlayerListener();

        nexusController = new NexusController(this);
        listenerManager.registerNexusListeners();
    }

    public void onDisable() {
        nexusController.updateAllNexus();

        ListenerManager listenerManager = new ListenerManager(this);
        listenerManager.unregisterAll();
    }

    public MussPlayerController getMussPlayerController() {
        return mussPlayerController;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public NexusController getNexusController(Main plugin) {
        return nexusController;
    }
}
