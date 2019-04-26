package com.musscraft.controllers.MussPlayerController.Commands;

import com.musscraft.Main;
import com.musscraft.controllers.MussPlayerController.MussPlayerController;
import com.musscraft.controllers.MussPlayerController.MussPlayerManager;
import com.musscraft.exceptions.MussPlayerExceptions.MussPlayerNotExistsException;
import com.musscraft.exceptions.MussPlayerExceptions.MussPlayerNotFoundException;
import com.musscraft.models.MussPlayer;
import com.musscraft.repositories.MussPlayerRepository;
import io.github.mrblobman.spigotcommandlib.CommandHandle;
import io.github.mrblobman.spigotcommandlib.CommandHandler;
import io.github.mrblobman.spigotcommandlib.args.ArgDescription;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AuthCommands implements CommandHandler {
    private MussPlayerManager mussPlayerManager;
    private MussPlayerRepository mussPlayerRepository;
    private MussPlayerController mussPlayerController;

    public AuthCommands(Main plugin) {
        this.mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
        this.mussPlayerRepository = mussPlayerController.getMussPlayerRepository();
    }

    @CommandHandle(
            command = {"login|lo"},
            description = "Faz login no servidor",
            permission = "musscraft.mussplayer.login"
    )
    public void login(
            Player player,
            @ArgDescription(
                    name = "Senha",
                    description = "Sua senha de login"
            ) String password
    ) {
        try {
            MussPlayer mussPlayer = mussPlayerRepository.get(player.getName());

            if (!mussPlayerController.attempLogin(mussPlayer, password)) {
                player.sendMessage("Senha errada!");
                return;
            }
            player.sendMessage("Logado com sucesso!");
        } catch (MussPlayerNotExistsException e) {
            e.printStackTrace();
        }
    }

    @CommandHandle(
            command = {"register|registrar"},
            description = "Se registra no servidor.",
            permission = "musscraft.mussplayer.register"
    )
    public void register(
            Player player,
            @ArgDescription(
                    name = "Senha",
                    description = "Senha para registro"
            ) String password,
            @ArgDescription(
                    name = "E-mail",
                    description = "Seu e-mail pessoal"
            ) String email
    ) {
        if (mussPlayerRepository.exists(player.getName())) {
            player.sendMessage("Você já está registrado.");
            return;
        }

        try {
            MussPlayer mussPlayer = mussPlayerManager.findMussPlayerByPlayer(player);
            mussPlayerController.registerMussPlayer(mussPlayer, password, email);
        } catch (MussPlayerNotFoundException e) {
            e.printStackTrace();
        }

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&aVocê se registrou com sucesso!")
        );
    }

    @CommandHandle(
            command = {"changepassword|trocarsenha"},
            description = "Altera sua senha",
            permission = "musscraft.mussplayer.changepassword"
    )
    public void changePass(Player player, String oldPassword, String newPassword) {

    }
}
