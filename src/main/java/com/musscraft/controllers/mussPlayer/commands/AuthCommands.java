package com.musscraft.controllers.mussPlayer.commands;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.MussPlayerController;
import com.musscraft.controllers.mussPlayer.MussPlayerManager;
import com.musscraft.controllers.mussPlayer.repositories.MussPlayerRepository;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import com.musscraft.utils.PasswordUtils;
import io.github.mrblobman.spigotcommandlib.CommandHandle;
import io.github.mrblobman.spigotcommandlib.CommandHandler;
import io.github.mrblobman.spigotcommandlib.args.ArgDescription;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AuthCommands implements CommandHandler {
    private Main plugin;
    private MussPlayerManager mussPlayerManager;
    private MussPlayerRepository mussPlayerRepository;

    public AuthCommands(Main plugin) {
        this.plugin = plugin;
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
    ) {
        MussPlayer mussPlayer = mussPlayerRepository.get(player.getName());

        if (!attempLogin(mussPlayer, password)) {
            player.sendMessage("Senha errada!");
            return;
        }

        mussPlayer.removeLoginScreen();
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

        MussPlayer mussPlayer = mussPlayerManager.findMussPlayer(player);
        mussPlayer.populateDefault(true, true);
        mussPlayerRepository.add(mussPlayer);
        mussPlayerManager.saveOrUpdate(mussPlayer);
        mussPlayer.removeLoginScreen();

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
            MussPlayer mussPlayer = mussPlayerManager.findMussPlayer(player.getName());
            mussPlayer.changePassword(oldPassword, newPassword);
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

    private boolean attempLogin(MussPlayer mussPlayer, String password) {
        if (PasswordUtils.comparePassword(password, mussPlayer.getPassword())) {
            mussPlayer.setLogged(true);
            mussPlayerRepository.saveOrUpdate(mussPlayer);
            mussPlayerManager.saveOrUpdate(mussPlayer);
            return true;
        }
        return false;
    }
}
