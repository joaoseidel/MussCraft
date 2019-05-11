package com.musscraft.controllers.mussPlayer;

import com.musscraft.controllers.mussPlayer.exceptions.MussPlayerNotFoundException;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MussPlayerManager {
    private List<MussPlayer> loggedMussPlayers = new ArrayList<>(Bukkit.getMaxPlayers());

    public MussPlayer findMussPlayer(Player player) {
        MussPlayer mussPlayer = null;
        try {
            mussPlayer = findMussPlayer(player.getName());
        } catch (MussPlayerNotFoundException e) {
            e.printStackTrace();
        }
        return mussPlayer;
    }

    public MussPlayer findMussPlayer(String username) throws MussPlayerNotFoundException {
        for (MussPlayer mussPlayer : loggedMussPlayers) {
            if (mussPlayer.getUsername().equals(username)) {
                return mussPlayer;
            }
        }

        throw new MussPlayerNotFoundException();
    }

    public void add(MussPlayer mussPlayer) {
        mussPlayer.setUsername(
                mussPlayer.getUsername().toLowerCase()
        );
        loggedMussPlayers.add(mussPlayer);
    }

    public void remove(MussPlayer mussPlayer) {
        loggedMussPlayers.remove(mussPlayer);
    }

    public void saveOrUpdate(MussPlayer mussPlayer) {
        loggedMussPlayers.remove(mussPlayer);
        add(mussPlayer);
    }
}
