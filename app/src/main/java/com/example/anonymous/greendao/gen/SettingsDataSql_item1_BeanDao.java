package com.example.anonymous.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.xiao.myappshuihu.entity.SettingsDataSql_item1_Bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SETTINGS_DATA_SQL_ITEM1__BEAN".
*/
public class SettingsDataSql_item1_BeanDao extends AbstractDao<SettingsDataSql_item1_Bean, Long> {

    public static final String TABLENAME = "SETTINGS_DATA_SQL_ITEM1__BEAN";

    /**
     * Properties of entity SettingsDataSql_item1_Bean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Wendu = new Property(2, String.class, "wendu", false, "WENDU");
        public final static Property Status = new Property(3, boolean.class, "status", false, "STATUS");
    };


    public SettingsDataSql_item1_BeanDao(DaoConfig config) {
        super(config);
    }
    
    public SettingsDataSql_item1_BeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SETTINGS_DATA_SQL_ITEM1__BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"WENDU\" TEXT," + // 2: wendu
                "\"STATUS\" INTEGER NOT NULL );"); // 3: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SETTINGS_DATA_SQL_ITEM1__BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SettingsDataSql_item1_Bean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String wendu = entity.getWendu();
        if (wendu != null) {
            stmt.bindString(3, wendu);
        }
        stmt.bindLong(4, entity.getStatus() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SettingsDataSql_item1_Bean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String wendu = entity.getWendu();
        if (wendu != null) {
            stmt.bindString(3, wendu);
        }
        stmt.bindLong(4, entity.getStatus() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SettingsDataSql_item1_Bean readEntity(Cursor cursor, int offset) {
        SettingsDataSql_item1_Bean entity = new SettingsDataSql_item1_Bean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // wendu
            cursor.getShort(offset + 3) != 0 // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SettingsDataSql_item1_Bean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setWendu(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStatus(cursor.getShort(offset + 3) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SettingsDataSql_item1_Bean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SettingsDataSql_item1_Bean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}