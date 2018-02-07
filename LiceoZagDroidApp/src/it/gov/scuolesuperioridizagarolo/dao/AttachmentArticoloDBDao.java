package it.gov.scuolesuperioridizagarolo.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ATTACHMENT_ARTICOLO_DB".
*/
public class AttachmentArticoloDBDao extends AbstractDao<AttachmentArticoloDB, Long> {

    public static final String TABLENAME = "ATTACHMENT_ARTICOLO_DB";

    /**
     * Properties of entity AttachmentArticoloDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Filename = new Property(1, String.class, "filename", false, "FILENAME");
        public final static Property Url = new Property(2, String.class, "url", false, "URL");
        public final static Property Filesize = new Property(3, int.class, "filesize", false, "FILESIZE");
        public final static Property State = new Property(4, int.class, "state", false, "STATE");
        public final static Property InsertTimestamp = new Property(5, java.util.Date.class, "insertTimestamp", false, "INSERT_TIMESTAMP");
        public final static Property Filetype = new Property(6, String.class, "filetype", false, "FILETYPE");
        public final static Property Fk_articleId = new Property(7, long.class, "fk_articleId", false, "FK_ARTICLE_ID");
    }

    private DaoSession daoSession;


    public AttachmentArticoloDBDao(DaoConfig config) {
        super(config);
    }
    
    public AttachmentArticoloDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ATTACHMENT_ARTICOLO_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"FILENAME\" TEXT NOT NULL ," + // 1: filename
                "\"URL\" TEXT NOT NULL ," + // 2: url
                "\"FILESIZE\" INTEGER NOT NULL ," + // 3: filesize
                "\"STATE\" INTEGER NOT NULL ," + // 4: state
                "\"INSERT_TIMESTAMP\" INTEGER NOT NULL ," + // 5: insertTimestamp
                "\"FILETYPE\" TEXT NOT NULL ," + // 6: filetype
                "\"FK_ARTICLE_ID\" INTEGER NOT NULL );"); // 7: fk_articleId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_ATTACHMENT_ARTICOLO_DB_FK_ARTICLE_ID ON \"ATTACHMENT_ARTICOLO_DB\"" +
                " (\"FK_ARTICLE_ID\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ATTACHMENT_ARTICOLO_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AttachmentArticoloDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getFilename());
        stmt.bindString(3, entity.getUrl());
        stmt.bindLong(4, entity.getFilesize());
        stmt.bindLong(5, entity.getState());
        stmt.bindLong(6, entity.getInsertTimestamp().getTime());
        stmt.bindString(7, entity.getFiletype());
        stmt.bindLong(8, entity.getFk_articleId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AttachmentArticoloDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getFilename());
        stmt.bindString(3, entity.getUrl());
        stmt.bindLong(4, entity.getFilesize());
        stmt.bindLong(5, entity.getState());
        stmt.bindLong(6, entity.getInsertTimestamp().getTime());
        stmt.bindString(7, entity.getFiletype());
        stmt.bindLong(8, entity.getFk_articleId());
    }

    @Override
    protected final void attachEntity(AttachmentArticoloDB entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AttachmentArticoloDB readEntity(Cursor cursor, int offset) {
        AttachmentArticoloDB entity = new AttachmentArticoloDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // filename
            cursor.getString(offset + 2), // url
            cursor.getInt(offset + 3), // filesize
            cursor.getInt(offset + 4), // state
            new java.util.Date(cursor.getLong(offset + 5)), // insertTimestamp
            cursor.getString(offset + 6), // filetype
            cursor.getLong(offset + 7) // fk_articleId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AttachmentArticoloDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFilename(cursor.getString(offset + 1));
        entity.setUrl(cursor.getString(offset + 2));
        entity.setFilesize(cursor.getInt(offset + 3));
        entity.setState(cursor.getInt(offset + 4));
        entity.setInsertTimestamp(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setFiletype(cursor.getString(offset + 6));
        entity.setFk_articleId(cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AttachmentArticoloDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AttachmentArticoloDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AttachmentArticoloDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getArticoloDBDao().getAllColumns());
            builder.append(" FROM ATTACHMENT_ARTICOLO_DB T");
            builder.append(" LEFT JOIN ARTICOLO_DB T0 ON T.\"FK_ARTICLE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected AttachmentArticoloDB loadCurrentDeep(Cursor cursor, boolean lock) {
        AttachmentArticoloDB entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ArticoloDB articoloDB = loadCurrentOther(daoSession.getArticoloDBDao(), cursor, offset);
         if(articoloDB != null) {
            entity.setArticoloDB(articoloDB);
        }

        return entity;    
    }

    public AttachmentArticoloDB loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<AttachmentArticoloDB> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<AttachmentArticoloDB> list = new ArrayList<AttachmentArticoloDB>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<AttachmentArticoloDB> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<AttachmentArticoloDB> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
