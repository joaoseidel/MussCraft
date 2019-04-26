package com.musscraft.controllers.MussPlayerController;

import com.musscraft.exceptions.MussPlayerExceptions.MussPlayerNotFoundException;
import com.musscraft.models.MussPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MussPlayerManager {
    private List<MussPlayer> loggedMussPlayers = new ArrayList<MussPlayer>();

    public MussPlayer findMussPlayerByPlayer(Player player) throws MussPlayerNotFoundException {
        for (MussPlayer mussPlayer : loggedMussPlayers) {
            if (mussPlayer.getUsername().equals(player.getName())) {
                return mussPlayer;
            }
        }

        System.out.println(loggedMussPlayers);

        throw new MussPlayerNotFoundException();
    }

    public MussPlayer findMussPlayerByUsername(String username) throws MussPlayerNotFoundException {
        for (MussPlayer mussPlayer : loggedMussPlayers) {
            if (mussPlayer.getUsername().equals(username)) {
                return mussPlayer;
            }
        }

        throw new MussPlayerNotFoundException();
    }

    public void add(MussPlayer mussPlayer) {
        loggedMussPlayers.add(mussPlayer);
    }

    public void remove(MussPlayer mussPlayer) {
        loggedMussPlayers.remove(mussPlayer);
    }

    public void saveOrUpdate(MussPlayer mussPlayer) {
        try {
            MussPlayer updatableMussPlayer = findMussPlayerByUsername(mussPlayer.getUsername());
            if (updatableMussPlayer.getUsername().length() != 0) {
                loggedMussPlayers.remove(updatableMussPlayer);
                loggedMussPlayers.add(mussPlayer);
                return;
            }
            loggedMussPlayers.add(mussPlayer);
        } catch (MussPlayerNotFoundException e) {
            e.printStackTrace();
        }
    }
}
