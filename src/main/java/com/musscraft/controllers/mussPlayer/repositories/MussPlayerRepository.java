package com.musscraft.controllers.mussPlayer.repositories;

import com.musscraft.Main;
import com.musscraft.controllers.mussPlayer.repositories.models.MussPlayer;
import com.musscraft.database.jOOQ.tables.records.MussplayerRecord;
import com.musscraft.utils.LocationSerializer;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.musscraft.database.jOOQ.tables.Mussplayer.MUSSPLAYER;
import static org.jooq.impl.DSL.select;


public class MussPlayerRepository {
    private DSLContext create;

    public MussPlayerRepository(Main plugin) {
        this.create = plugin.getConnectionFactory().getContext();
    }

    public MussPlayer add(MussPlayer mussPlayer) {
        MussplayerRecord mussplayerRecord = create.newRecord(MUSSPLAYER);

        mussPlayer.setUid(UUID.randomUUID());

        mussplayerRecord.setUid(mussPlayer.getUid().toString());
        mussplayerRecord.setUsername(mussPlayer.getUsername());
        mussplayerRecord.setPassword(mussPlayer.getPassword());
        mussplayerRecord.setEmail(mussPlayer.getEmail());
        mussplayerRecord.store();

        return mussPlayer;
    }

    public MussPlayer get(String username) {
        MussplayerRecord mussplayerRecord = create.selectFrom(MUSSPLAYER)
                .where(MUSSPLAYER.USERNAME.eq(username))
                .fetchOne();

        return fillMussPlayer(mussplayerRecord);
    }

    public List<MussPlayer> getAll() {
        Result<MussplayerRecord> fetch = create.selectFrom(MUSSPLAYER)
                .orderBy(MUSSPLAYER.USERNAME)
                .fetch();

        List<MussPlayer> mussPlayerList = new ArrayList<>(fetch.size());
        fetch.forEach(record -> mussPlayerList.add(fillMussPlayer(record)));
        return mussPlayerList;
    }

    public boolean exists(String username) {
        return create.fetchExists(
                select()
                        .from(MUSSPLAYER)
                        .where(MUSSPLAYER.USERNAME.eq(username)));
    }

    public void remove(MussPlayer mussPlayer) {
        if (exists(mussPlayer.getUsername())) {
            create.delete(MUSSPLAYER)
                    .where(MUSSPLAYER.UID.eq(mussPlayer.getUid().toString()))
                    .execute();
        }
    }

    public void saveOrUpdate(MussPlayer mussPlayer) {
        if (!exists(mussPlayer.getUsername())) {
            add(mussPlayer);
            return;
        }

        MussplayerRecord mussplayerRecord = create.selectFrom(MUSSPLAYER)
                .where(MUSSPLAYER.USERNAME.eq(mussPlayer.getUsername()))
                .fetchOne();

        fillMussPlayer(mussplayerRecord, mussPlayer).update();
    }


    private MussPlayer fillMussPlayer(MussplayerRecord record) {
        MussPlayer mussPlayer = new MussPlayer(record.getUsername())
                .setUid(UUID.fromString(record.getUid()))
                .setPassword(record.getPassword())
                .setEmail(record.getEmail())
                .setMoney(record.getMoney())
                .setExperience(record.getExperience())
                .setLogged(record.getLogged().intValue() == 1 ? (Boolean.TRUE) : (Boolean.FALSE))
                .setLocation(LocationSerializer.getDeserializedLocation(record.getLocation()));
        return mussPlayer;
    }

    private MussplayerRecord fillMussPlayer(MussplayerRecord mussplayerRecord, MussPlayer mussPlayer) {
        mussplayerRecord.setUid(mussPlayer.getUid().toString());
        mussplayerRecord.setUsername(mussPlayer.getUsername());
        mussplayerRecord.setPassword(mussPlayer.getPassword());
        mussplayerRecord.setEmail(mussPlayer.getEmail());
        mussplayerRecord.setMoney(mussPlayer.getMoney());
        mussplayerRecord.setExperience(mussPlayer.getExperience());
        mussplayerRecord.setLocation(LocationSerializer.serializeLocation(mussPlayer.getLocation()));
        mussplayerRecord.setLogged((byte) (mussPlayer.isLogged() ? 1 : 0));
        return mussplayerRecord;
    }
}
