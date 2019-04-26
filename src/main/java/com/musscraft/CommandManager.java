package com.musscraft;

import com.musscraft.controllers.mussPlayer.commands.AuthCommands;
import io.github.mrblobman.spigotcommandlib.registry.CommandLib;

public class CommandManager {
    private Main plugin;
    private CommandLib lib;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
        lib = new CommandLib(plugin);
    }

    public void registerMussPlayerCommands() {
        lib.registerCommandHandler(new AuthCommands(plugin));
    }
}
