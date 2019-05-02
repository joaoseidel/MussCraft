package com.musscraft.controllers.mussPlayer;

import com.musscraft.controllers.mussPlayer.exceptions.MussPlayerNotFoundException;
import com.musscraft.controllers.mussPlayer.models.MussPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MussPlayerManager {
    private List<MussPlayer> loggedMussPlayers = new ArrayList<MussPlayer>();

    public MussPlayer findMussPlayerByPlayer(Player player) {
        MussPlayer mussPlayer = null;
        try {
            mussPlayer = findMussPlayerByUsername(player.getName());
        } catch (MussPlayerNotFoundException e) {
            e.printStackTrace();
        }
        return mussPlayer;
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
        mussPlayer.setUsername(
                mussPlayer.getUsername().toLowerCase()
        );
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
