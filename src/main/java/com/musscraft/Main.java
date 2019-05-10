package com.musscraft;

import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.database.ConnectionFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

@Plugin(name = "MussCraft", version = "1.0.0")
public class Main extends JavaPlugin {
    private ConnectionFactory connectionFactory;
    private MussPlayerController mussPlayerController;

    public void onEnable() {
        connectionFactory = new ConnectionFactory();

        CommandManager commandManager = new CommandManager(this);
        ListenerManager listenerManager = new ListenerManager(this);

        mussPlayerController = new MussPlayerController(this);
        commandManager.registerMussPlayerCommands();
        listenerManager.registerMussPlayerListener();
    }

    public void onDisable() {
        ListenerManager listenerManager = new ListenerManager(this);
        listenerManager.unregisterAll();
    }

    public MussPlayerController getMussPlayerController() {
        return mussPlayerController;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
