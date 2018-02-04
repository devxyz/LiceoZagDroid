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
 * DAO for table "TAG_ARTICOLO_DB".
*/
public class TagArticoloDBDao extends AbstractDao<TagArticoloDB, Long> {

    public static final String TABLENAME = "TAG_ARTICOLO_DB";

    /**
     * Properties of entity TagArticoloDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property RemoteId = new Property(2, Integer.class, "remoteId", false, "REMOTE_ID");
        public final static Property InsertTimestamp = new Property(3, java.util.Date.class, "insertTimestamp", false, "INSERT_TIMESTAMP");
        public final static Property Fk_articleId = new Property(4, Long.class, "fk_articleId", false, "FK_ARTICLE_ID");
    }

    private DaoSession daoSession;


    public TagArticoloDBDao(DaoConfig config) {
        super(config);
    }
    
    public TagArticoloDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TAG_ARTICOLO_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"REMOTE_ID\" INTEGER UNIQUE ," + // 2: remoteId
                "\"INSERT_TIMESTAMP\" INTEGER," + // 3: insertTimestamp
                "\"FK_ARTICLE_ID\" INTEGER);"); // 4: fk_articleId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_TAG_ARTICOLO_DB_FK_ARTICLE_ID ON \"TAG_ARTICOLO_DB\"" +
                " (\"FK_ARTICLE_ID\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TAG_ARTICOLO_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TagArticoloDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        Integer remoteId = entity.getRemoteId();
        if (remoteId != null) {
            stmt.bindLong(3, remoteId);
        }
 
        java.util.Date insertTimestamp = entity.getInsertTimestamp();
        if (insertTimestamp != null) {
            stmt.bindLong(4, insertTimestamp.getTime());
        }
 
        Long fk_articleId = entity.getFk_articleId();
        if (fk_articleId != null) {
            stmt.bindLong(5, fk_articleId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TagArticoloDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        Integer remoteId = entity.getRemoteId();
        if (remoteId != null) {
            stmt.bindLong(3, remoteId);
        }
 
        java.util.Date insertTimestamp = entity.getInsertTimestamp();
        if (insertTimestamp != null) {
            stmt.bindLong(4, insertTimestamp.getTime());
        }
 
        Long fk_articleId = entity.getFk_articleId();
        if (fk_articleId != null) {
            stmt.bindLong(5, fk_articleId);
        }
    }

    @Override
    protected final void attachEntity(TagArticoloDB entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TagArticoloDB readEntity(Cursor cursor, int offset) {
        TagArticoloDB entity = new TagArticoloDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // remoteId
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // insertTimestamp
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // fk_articleId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TagArticoloDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRemoteId(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setInsertTimestamp(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setFk_articleId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TagArticoloDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TagArticoloDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TagArticoloDB entity) {
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
            builder.append(" FROM TAG_ARTICOLO_DB T");
            builder.append(" LEFT JOIN ARTICOLO_DB T0 ON T.\"FK_ARTICLE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected TagArticoloDB loadCurrentDeep(Cursor cursor, boolean lock) {
        TagArticoloDB entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ArticoloDB articoloDB = loadCurrentOther(daoSession.getArticoloDBDao(), cursor, offset);
        entity.setArticoloDB(articoloDB);

        return entity;    
    }

    public TagArticoloDB loadDeep(Long key) {
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
    public List<TagArticoloDB> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<TagArticoloDB> list = new ArrayList<TagArticoloDB>(count);
        
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
    
    protected List<TagArticoloDB> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<TagArticoloDB> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
