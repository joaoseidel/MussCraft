package com.musscraft.controllers.mussPlayer.commands;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.exceptions.MussPlayerNotFoundException;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import com.musscraft.utils.PasswordUtils;
import io.github.mrblobman.spigotcommandlib.CommandHandle;
import io.github.mrblobman.spigotcommandlib.CommandHandler;
import io.github.mrblobman.spigotcommandlib.args.ArgDescription;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AuthCommands implements CommandHandler {
    private MussPlayerManager mussPlayerManager;
    private MussPlayerRepository mussPlayerRepository;

    public AuthCommands(Main plugin) {
        MussPlayerController mussPlayerController = plugin.getMussPlayerController();
        this.mussPlayerManager = mussPlayerController.getMussPlayerManager();
        this.mussPlayerRepository = mussPlayerController.getMussPlayerRepository();
    }

    @CommandHandle(
            command = {"logar"},
            description = "Faz login no servidor",
            permission = "musscraft.auth.login"
    )
    public void login(
            Player player,
            @ArgDescription(
                    name = "Senha",
                    description = "Sua senha de login"
            ) String password
    ) throws MussPlayerNotFoundException {
        mussPlayerManager.remove(mussPlayerManager.findMussPlayer(player.getName()));
        MussPlayer mussPlayer = mussPlayerRepository.get(player.getName()).setPlayer(player);

        if (!PasswordUtils.comparePassword(password, mussPlayer.getPassword())) {
            player.sendMessage("Senha errada!");
            return;
        }

        mussPlayer = mussPlayer.removeLoginScreen();
        mussPlayerRepository.saveOrUpdate(mussPlayer);
        mussPlayerManager.saveOrUpdate(mussPlayer);

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&bLogado com sucesso!")
        );
    }

    @CommandHandle(
            command = {"registrar"},
            description = "Se registra no servidor.",
            permission = "musscraft.auth.register"
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

        MussPlayer mussPlayer = mussPlayerManager.findMussPlayer(player)
                .populateDefault(true, true)
                .removeLoginScreen();
        mussPlayerRepository.add(mussPlayer);
        mussPlayerManager.saveOrUpdate(mussPlayer);

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&aVocê se registrou com sucesso!")
        );
    }

    @CommandHandle(
            command = {"trocarsenha"},
            description = "Altera sua senha",
            permission = "musscraft.auth.changepassword"
    )
    public void changePass(
            Player player,
            String oldPassword,
            String newPassword) {
        try {
            MussPlayer mussPlayer = mussPlayerManager.findMussPlayer(player.getName())
                    .changePassword(oldPassword, newPassword);
            mussPlayerManager.saveOrUpdate(mussPlayer);
            mussPlayerRepository.saveOrUpdate(mussPlayer);
        } catch (Exception e) {
            player.sendMessage(e.getMessage());
            return;
        }

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&bVocê alterou sua senha com sucesso.")
        );
    }
}
