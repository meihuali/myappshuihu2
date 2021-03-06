package com.example.anonymous.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.xiao.myappshuihu.entity.NZYData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NZYDATA".
*/
public class NZYDataDao extends AbstractDao<NZYData, Long> {

    public static final String TABLENAME = "NZYDATA";

    /**
     * Properties of entity NZYData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NZ_ID = new Property(1, String.class, "NZ_ID", false, "NZ__ID");
        public final static Property MACHINE_ID = new Property(2, String.class, "MACHINE_ID", false, "MACHINE__ID");
        public final static Property NZ_NAME = new Property(3, String.class, "NZ_NAME", false, "NZ__NAME");
        public final static Property NZ_TX1 = new Property(4, int.class, "NZ_TX1", false, "NZ__TX1");
        public final static Property NZ_SW = new Property(5, String.class, "NZ_SW", false, "NZ__SW");
        public final static Property NZ_TIME = new Property(6, String.class, "NZ_TIME", false, "NZ__TIME");
        public final static Property NZ_MS = new Property(7, String.class, "NZ_MS", false, "NZ__MS");
        public final static Property NZ_ISOPEN = new Property(8, int.class, "NZ_ISOPEN", false, "NZ__ISOPEN");
        public final static Property NZ_START = new Property(9, int.class, "NZ_START", false, "NZ__START");
        public final static Property NZ_END = new Property(10, int.class, "NZ_END", false, "NZ__END");
        public final static Property NZ_LELVER_OPEN = new Property(11, int.class, "NZ_LELVER_OPEN", false, "NZ__LELVER__OPEN");
        public final static Property NZ_LELVER = new Property(12, String.class, "NZ_LELVER", false, "NZ__LELVER");
        public final static Property NZ_ZDYID = new Property(13, String.class, "NZ_ZDYID", false, "NZ__ZDYID");
        public final static Property ORDER_ID = new Property(14, String.class, "ORDER_ID", false, "ORDER__ID");
    }


    public NZYDataDao(DaoConfig config) {
        super(config);
    }
    
    public NZYDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NZYDATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NZ__ID\" TEXT," + // 1: NZ_ID
                "\"MACHINE__ID\" TEXT," + // 2: MACHINE_ID
                "\"NZ__NAME\" TEXT," + // 3: NZ_NAME
                "\"NZ__TX1\" INTEGER NOT NULL ," + // 4: NZ_TX1
                "\"NZ__SW\" TEXT," + // 5: NZ_SW
                "\"NZ__TIME\" TEXT," + // 6: NZ_TIME
                "\"NZ__MS\" TEXT," + // 7: NZ_MS
                "\"NZ__ISOPEN\" INTEGER NOT NULL ," + // 8: NZ_ISOPEN
                "\"NZ__START\" INTEGER NOT NULL ," + // 9: NZ_START
                "\"NZ__END\" INTEGER NOT NULL ," + // 10: NZ_END
                "\"NZ__LELVER__OPEN\" INTEGER NOT NULL ," + // 11: NZ_LELVER_OPEN
                "\"NZ__LELVER\" TEXT," + // 12: NZ_LELVER
                "\"NZ__ZDYID\" TEXT," + // 13: NZ_ZDYID
                "\"ORDER__ID\" TEXT);"); // 14: ORDER_ID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NZYDATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NZYData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String NZ_ID = entity.getNZ_ID();
        if (NZ_ID != null) {
            stmt.bindString(2, NZ_ID);
        }
 
        String MACHINE_ID = entity.getMACHINE_ID();
        if (MACHINE_ID != null) {
            stmt.bindString(3, MACHINE_ID);
        }
 
        String NZ_NAME = entity.getNZ_NAME();
        if (NZ_NAME != null) {
            stmt.bindString(4, NZ_NAME);
        }
        stmt.bindLong(5, entity.getNZ_TX1());
 
        String NZ_SW = entity.getNZ_SW();
        if (NZ_SW != null) {
            stmt.bindString(6, NZ_SW);
        }
 
        String NZ_TIME = entity.getNZ_TIME();
        if (NZ_TIME != null) {
            stmt.bindString(7, NZ_TIME);
        }
 
        String NZ_MS = entity.getNZ_MS();
        if (NZ_MS != null) {
            stmt.bindString(8, NZ_MS);
        }
        stmt.bindLong(9, entity.getNZ_ISOPEN());
        stmt.bindLong(10, entity.getNZ_START());
        stmt.bindLong(11, entity.getNZ_END());
        stmt.bindLong(12, entity.getNZ_LELVER_OPEN());
 
        String NZ_LELVER = entity.getNZ_LELVER();
        if (NZ_LELVER != null) {
            stmt.bindString(13, NZ_LELVER);
        }
 
        String NZ_ZDYID = entity.getNZ_ZDYID();
        if (NZ_ZDYID != null) {
            stmt.bindString(14, NZ_ZDYID);
        }
 
        String ORDER_ID = entity.getORDER_ID();
        if (ORDER_ID != null) {
            stmt.bindString(15, ORDER_ID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NZYData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String NZ_ID = entity.getNZ_ID();
        if (NZ_ID != null) {
            stmt.bindString(2, NZ_ID);
        }
 
        String MACHINE_ID = entity.getMACHINE_ID();
        if (MACHINE_ID != null) {
            stmt.bindString(3, MACHINE_ID);
        }
 
        String NZ_NAME = entity.getNZ_NAME();
        if (NZ_NAME != null) {
            stmt.bindString(4, NZ_NAME);
        }
        stmt.bindLong(5, entity.getNZ_TX1());
 
        String NZ_SW = entity.getNZ_SW();
        if (NZ_SW != null) {
            stmt.bindString(6, NZ_SW);
        }
 
        String NZ_TIME = entity.getNZ_TIME();
        if (NZ_TIME != null) {
            stmt.bindString(7, NZ_TIME);
        }
 
        String NZ_MS = entity.getNZ_MS();
        if (NZ_MS != null) {
            stmt.bindString(8, NZ_MS);
        }
        stmt.bindLong(9, entity.getNZ_ISOPEN());
        stmt.bindLong(10, entity.getNZ_START());
        stmt.bindLong(11, entity.getNZ_END());
        stmt.bindLong(12, entity.getNZ_LELVER_OPEN());
 
        String NZ_LELVER = entity.getNZ_LELVER();
        if (NZ_LELVER != null) {
            stmt.bindString(13, NZ_LELVER);
        }
 
        String NZ_ZDYID = entity.getNZ_ZDYID();
        if (NZ_ZDYID != null) {
            stmt.bindString(14, NZ_ZDYID);
        }
 
        String ORDER_ID = entity.getORDER_ID();
        if (ORDER_ID != null) {
            stmt.bindString(15, ORDER_ID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NZYData readEntity(Cursor cursor, int offset) {
        NZYData entity = new NZYData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // NZ_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // MACHINE_ID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // NZ_NAME
            cursor.getInt(offset + 4), // NZ_TX1
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // NZ_SW
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // NZ_TIME
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // NZ_MS
            cursor.getInt(offset + 8), // NZ_ISOPEN
            cursor.getInt(offset + 9), // NZ_START
            cursor.getInt(offset + 10), // NZ_END
            cursor.getInt(offset + 11), // NZ_LELVER_OPEN
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // NZ_LELVER
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // NZ_ZDYID
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14) // ORDER_ID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NZYData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNZ_ID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMACHINE_ID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNZ_NAME(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNZ_TX1(cursor.getInt(offset + 4));
        entity.setNZ_SW(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setNZ_TIME(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNZ_MS(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNZ_ISOPEN(cursor.getInt(offset + 8));
        entity.setNZ_START(cursor.getInt(offset + 9));
        entity.setNZ_END(cursor.getInt(offset + 10));
        entity.setNZ_LELVER_OPEN(cursor.getInt(offset + 11));
        entity.setNZ_LELVER(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setNZ_ZDYID(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setORDER_ID(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NZYData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NZYData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NZYData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
