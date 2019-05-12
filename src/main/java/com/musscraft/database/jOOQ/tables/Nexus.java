/*
 * This file is generated by jOOQ.
 */
package com.musscraft.database.jOOQ.tables;


import com.musscraft.database.jOOQ.Indexes;
import com.musscraft.database.jOOQ.Keys;
import com.musscraft.database.jOOQ.Musscraft;
import com.musscraft.database.jOOQ.tables.records.NexusRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Nexus extends TableImpl<NexusRecord> {

    private static final long serialVersionUID = -449604755;

    /**
     * The reference instance of <code>MussCraft.Nexus</code>
     */
    public static final Nexus NEXUS = new Nexus();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NexusRecord> getRecordType() {
        return NexusRecord.class;
    }

    /**
     * The column <code>MussCraft.Nexus.uid</code>.
     */
    public final TableField<NexusRecord, String> UID = createField("uid", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>MussCraft.Nexus.name</code>.
     */
    public final TableField<NexusRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>MussCraft.Nexus.health</code>.
     */
    public final TableField<NexusRecord, Double> HEALTH = createField("health", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>MussCraft.Nexus.power</code>.
     */
    public final TableField<NexusRecord, Integer> POWER = createField("power", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MussCraft.Nexus.destroyedDate</code>.
     */
    public final TableField<NexusRecord, Date> DESTROYEDDATE = createField("destroyedDate", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>MussCraft.Nexus.location</code>.
     */
    public final TableField<NexusRecord, String> LOCATION = createField("location", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>MussCraft.Nexus.creatorUid</code>.
     */
    public final TableField<NexusRecord, String> CREATORUID = createField("creatorUid", org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * Create a <code>MussCraft.Nexus</code> table reference
     */
    public Nexus() {
        this(DSL.name("Nexus"), null);
    }

    /**
     * Create an aliased <code>MussCraft.Nexus</code> table reference
     */
    public Nexus(String alias) {
        this(DSL.name(alias), NEXUS);
    }

    /**
     * Create an aliased <code>MussCraft.Nexus</code> table reference
     */
    public Nexus(Name alias) {
        this(alias, NEXUS);
    }

    private Nexus(Name alias, Table<NexusRecord> aliased) {
        this(alias, aliased, null);
    }

    private Nexus(Name alias, Table<NexusRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Nexus(Table<O> child, ForeignKey<O, NexusRecord> key) {
        super(child, key, NEXUS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Musscraft.MUSSCRAFT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.NEXUS_NEXUS_CREATOR_UINDEX, Indexes.NEXUS_NEXUS_UID_UINDEX, Indexes.NEXUS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<NexusRecord> getPrimaryKey() {
        return Keys.KEY_NEXUS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<NexusRecord>> getKeys() {
        return Arrays.<UniqueKey<NexusRecord>>asList(Keys.KEY_NEXUS_PRIMARY, Keys.KEY_NEXUS_NEXUS_UID_UINDEX, Keys.KEY_NEXUS_NEXUS_CREATOR_UINDEX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<NexusRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<NexusRecord, ?>>asList(Keys.NEXUS_MUSSPLAYER_UID_FK);
    }

    public Mussplayer mussplayer() {
        return new Mussplayer(this, Keys.NEXUS_MUSSPLAYER_UID_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Nexus as(String alias) {
        return new Nexus(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Nexus as(Name alias) {
        return new Nexus(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Nexus rename(String name) {
        return new Nexus(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Nexus rename(Name name) {
        return new Nexus(name, null);
    }
}