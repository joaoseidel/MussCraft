package com.musscraft.controllers.nexus.repositories;

import com.musscraft.Main;
import com.musscraft.controllers.nexus.repositories.models.Nexus;
import com.musscraft.database.ConnectionFactory;
import com.musscraft.database.jOOQ.tables.records.NexusRecord;
import com.musscraft.utils.LocationUtils;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.musscraft.database.jOOQ.tables.Nexus.NEXUS;
import static org.jooq.impl.DSL.select;

public class NexusRepository {
    private DSLContext context;

    public NexusRepository(Main plugin) {
        ConnectionFactory connectionFactory = plugin.getConnectionFactory();
        this.context = connectionFactory.getContext();
    }

    public Nexus add(Nexus nexus) {
        nexus.setUid(UUID.randomUUID());

        NexusRecord nexusRecord = context.newRecord(NEXUS);
        fillNexus(nexus, nexusRecord);
        nexusRecord.store();
        return nexus;
    }

    public Nexus get(UUID uuid) {
        NexusRecord nexusRecord = context.selectFrom(NEXUS)
                .where(NEXUS.UID.eq(uuid.toString()))
                .fetchOne();

        return fillNexus(nexusRecord);
    }

    public List<Nexus> getAll() {
        Result<NexusRecord> fetch = context.selectFrom(NEXUS).fetch();
        List<Nexus> nexusList = new ArrayList<>(fetch.size());
        fetch.forEach(nexus -> nexusList.add(fillNexus(nexus)));
        return nexusList;
    }

    public boolean exists(Nexus nexus) {
        return context.fetchExists(
                select()
                        .from(NEXUS)
                        .where(NEXUS.UID.eq(nexus.getUid().toString())));
    }

    public void remove(Nexus nexus) {
        context.deleteFrom(NEXUS)
                .where(NEXUS.UID.eq(nexus.getUid().toString()))
                .execute();
    }

    public Nexus saveOrUpdate(Nexus nexus) {
        if (!exists(nexus)) {
            add(nexus);
            return nexus;
        }

        NexusRecord nexusRecord = context.selectFrom(NEXUS)
                .where(NEXUS.UID.eq(nexus.getUid().toString()))
                .fetchAny();

        nexusRecord = fillNexus(nexus, nexusRecord);
        nexusRecord.update();
        return nexus;
    }

    public Nexus fillNexus(NexusRecord nexusRecord) {
        Nexus nexus = new Nexus(UUID.fromString(nexusRecord.getUid()))
                .setName(nexusRecord.getName())
                .setCreatorUid(UUID.fromString(nexusRecord.getCreatoruid()))
                .setHealth(nexusRecord.getHealth())
                .setPower(nexusRecord.getPower())
                .setLocation(LocationUtils.getDeserializedLocation(nexusRecord.getLocation()))
                .setDestroyedDate(nexusRecord.getDestroyeddate());
        return nexus;
    }

    private NexusRecord fillNexus(Nexus nexus, NexusRecord nexusRecord) {
        nexusRecord.setUid(nexus.getUid().toString());
        nexusRecord.setName(nexus.getName());
        nexusRecord.setCreatoruid(nexus.getCreatorUid().toString());
        nexusRecord.setHealth(nexus.getHealth());
        nexusRecord.setPower(nexus.getPower());
        nexusRecord.setLocation(LocationUtils.serializeLocation(nexus.getLocation()));
        nexusRecord.setDestroyeddate((Date) nexus.getDestroyedDate());
        return nexusRecord;
    }
}
