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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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

        doLoginSpawn(mussPlayer, player);
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
        registerMussPlayer(mussPlayer, password, email);
        doLoginSpawn(mussPlayer, player);

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
            changeMussPlayerPassword(mussPlayer, oldPassword, newPassword);
        } catch (Exception e) {
            player.sendMessage(e.getMessage());
            return;
        }

        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&bVocê alterou sua senha com sucesso.")
        );
    }

    private void registerMussPlayer(MussPlayer mussPlayer, String password, String email) {
        mussPlayer.setPassword(PasswordUtils.hashPassword(password));
        mussPlayer.setEmail(email);
        mussPlayer.setMoney(30.0);
        mussPlayer.setExperience(0.0);
        mussPlayer.setLogged(true);
        mussPlayer.setRegistered(true);
        mussPlayer.setLocation(Bukkit.getWorld("world").getSpawnLocation());

        mussPlayerRepository.add(mussPlayer);
        mussPlayerManager.saveOrUpdate(mussPlayer);
    }

    private boolean attempLogin(MussPlayer mussPlayer, String password) {
        if (PasswordUtils.comparePassword(password, mussPlayer.getPassword())) {
            mussPlayer.setLogged(true);
            mussPlayer.setRegistered(true);
            mussPlayerManager.saveOrUpdate(mussPlayer);
            mussPlayerRepository.saveOrUpdate(mussPlayer);
            return true;
        }
        return false;
    }

    private void changeMussPlayerPassword(MussPlayer mussPlayer, String oldPassword, String newPassword) throws Exception {
        if (!PasswordUtils.comparePassword(oldPassword, mussPlayer.getPassword())) {
            throw new Exception("A senha antiga digitada não confere com sua senha atual.");
        }

        if (PasswordUtils.comparePassword(newPassword, mussPlayer.getPassword())) {
            throw new Exception("A senha nova digitada é igual a antiga.");
        }

        mussPlayer.setPassword(PasswordUtils.hashPassword(newPassword));
        mussPlayerManager.saveOrUpdate(mussPlayer);
        mussPlayerRepository.saveOrUpdate(mussPlayer);
    }

    private void doLoginSpawn(MussPlayer mussPlayer, Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(mussPlayer.getLocation());
        Bukkit.getOnlinePlayers().forEach(p -> player.showPlayer(plugin, p));
    }
}
