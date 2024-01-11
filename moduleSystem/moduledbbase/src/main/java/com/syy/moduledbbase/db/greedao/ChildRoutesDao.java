package com.syy.moduledbbase.db.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.syy.moduledbbase.db.entity.moduleuav.plantask.line.ChildRoutes;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHILD_ROUTES".
*/
public class ChildRoutesDao extends AbstractDao<ChildRoutes, Long> {

    public static final String TABLENAME = "CHILD_ROUTES";

    /**
     * Properties of entity ChildRoutes.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property NId = new Property(0, Long.class, "nId", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Airlinetype = new Property(3, String.class, "airlinetype", false, "AIRLINETYPE");
        public final static Property Fromtype = new Property(4, int.class, "fromtype", false, "FROMTYPE");
    }


    public ChildRoutesDao(DaoConfig config) {
        super(config);
    }
    
    public ChildRoutesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHILD_ROUTES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: nId
                "\"ID\" TEXT," + // 1: id
                "\"NAME\" TEXT," + // 2: name
                "\"AIRLINETYPE\" TEXT," + // 3: airlinetype
                "\"FROMTYPE\" INTEGER NOT NULL );"); // 4: fromtype
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHILD_ROUTES\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChildRoutes entity) {
        stmt.clearBindings();
 
        Long nId = entity.getNId();
        if (nId != null) {
            stmt.bindLong(1, nId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String airlinetype = entity.getAirlinetype();
        if (airlinetype != null) {
            stmt.bindString(4, airlinetype);
        }
        stmt.bindLong(5, entity.getFromtype());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChildRoutes entity) {
        stmt.clearBindings();
 
        Long nId = entity.getNId();
        if (nId != null) {
            stmt.bindLong(1, nId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String airlinetype = entity.getAirlinetype();
        if (airlinetype != null) {
            stmt.bindString(4, airlinetype);
        }
        stmt.bindLong(5, entity.getFromtype());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChildRoutes readEntity(Cursor cursor, int offset) {
        ChildRoutes entity = new ChildRoutes( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // nId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // airlinetype
            cursor.getInt(offset + 4) // fromtype
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChildRoutes entity, int offset) {
        entity.setNId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAirlinetype(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFromtype(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChildRoutes entity, long rowId) {
        entity.setNId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChildRoutes entity) {
        if(entity != null) {
            return entity.getNId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChildRoutes entity) {
        return entity.getNId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}