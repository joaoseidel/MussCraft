package com.musscraft;

import com.musscraft.controllers.mussPlayer.MussPlayerController;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

@Plugin(name = "MussCraft", version = "1.0.0")
public class Main extends JavaPlugin {
    private MussPlayerController mussPlayerController;

    public void onEnable() {
        CommandManager commandManager = new CommandManager(this);
        ListenerManager listenerManager = new ListenerManager(this);

        mussPlayerController = new MussPlayerController(this);
        commandManager.registerMussPlayerCommands();
        listenerManager.registerMussPlayerListener();
    }

    public void onDisable() {

    }

    public MussPlayerController getMussPlayerController() {
        return mussPlayerController;
    }
}
