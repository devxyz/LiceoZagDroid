package it.gov.scuolesuperioridizagarolo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TIMETABLE_DB".
*/
public class TimetableDBDao extends AbstractDao<TimetableDB, Long> {

    public static final String TABLENAME = "TIMETABLE_DB";

    /**
     * Properties of entity TimetableDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", false, "URL");
        public final static Property RemoteId = new Property(2, long.class, "remoteId", false, "REMOTE_ID");
        public final static Property CreateDate = new Property(3, java.util.Date.class, "createDate", false, "CREATE_DATE");
        public final static Property Filename = new Property(4, String.class, "filename", false, "FILENAME");
        public final static Property StartDate = new Property(5, java.util.Date.class, "startDate", false, "START_DATE");
        public final static Property EndDate = new Property(6, java.util.Date.class, "endDate", false, "END_DATE");
        public final static Property Data = new Property(7, byte[].class, "data", false, "DATA");
    }


    public TimetableDBDao(DaoConfig config) {
        super(config);
    }
    
    public TimetableDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TIMETABLE_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"URL\" TEXT NOT NULL UNIQUE ," + // 1: url
                "\"REMOTE_ID\" INTEGER NOT NULL UNIQUE ," + // 2: remoteId
                "\"CREATE_DATE\" INTEGER NOT NULL ," + // 3: createDate
                "\"FILENAME\" TEXT NOT NULL UNIQUE ," + // 4: filename
                "\"START_DATE\" INTEGER NOT NULL ," + // 5: startDate
                "\"END_DATE\" INTEGER NOT NULL ," + // 6: endDate
                "\"DATA\" BLOB NOT NULL );"); // 7: data
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_TIMETABLE_DB_URL ON \"TIMETABLE_DB\"" +
                " (\"URL\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TIMETABLE_DB_REMOTE_ID ON \"TIMETABLE_DB\"" +
                " (\"REMOTE_ID\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TIMETABLE_DB_CREATE_DATE ON \"TIMETABLE_DB\"" +
                " (\"CREATE_DATE\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_TIMETABLE_DB_FILENAME ON \"TIMETABLE_DB\"" +
                " (\"FILENAME\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TIMETABLE_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TimetableDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUrl());
        stmt.bindLong(3, entity.getRemoteId());
        stmt.bindLong(4, entity.getCreateDate().getTime());
        stmt.bindString(5, entity.getFilename());
        stmt.bindLong(6, entity.getStartDate().getTime());
        stmt.bindLong(7, entity.getEndDate().getTime());
        stmt.bindBlob(8, entity.getData());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TimetableDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUrl());
        stmt.bindLong(3, entity.getRemoteId());
        stmt.bindLong(4, entity.getCreateDate().getTime());
        stmt.bindString(5, entity.getFilename());
        stmt.bindLong(6, entity.getStartDate().getTime());
        stmt.bindLong(7, entity.getEndDate().getTime());
        stmt.bindBlob(8, entity.getData());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TimetableDB readEntity(Cursor cursor, int offset) {
        TimetableDB entity = new TimetableDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // url
            cursor.getLong(offset + 2), // remoteId
            new java.util.Date(cursor.getLong(offset + 3)), // createDate
            cursor.getString(offset + 4), // filename
            new java.util.Date(cursor.getLong(offset + 5)), // startDate
            new java.util.Date(cursor.getLong(offset + 6)), // endDate
            cursor.getBlob(offset + 7) // data
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TimetableDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.getString(offset + 1));
        entity.setRemoteId(cursor.getLong(offset + 2));
        entity.setCreateDate(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setFilename(cursor.getString(offset + 4));
        entity.setStartDate(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setEndDate(new java.util.Date(cursor.getLong(offset + 6)));
        entity.setData(cursor.getBlob(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TimetableDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TimetableDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TimetableDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
