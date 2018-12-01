package it.gov.scuolesuperioridizagarolo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails_DBConverter;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloType;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloType_DBConverter;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ARTICOLO_DB".
*/
public class ArticoloDBDao extends AbstractDao<ArticoloDB, Long> {

    public static final String TABLENAME = "ARTICOLO_DB";

    /**
     * Properties of entity ArticoloDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property PubDate = new Property(2, java.util.Date.class, "pubDate", false, "PUB_DATE");
        public final static Property ModifiedDate = new Property(3, java.util.Date.class, "modifiedDate", false, "MODIFIED_DATE");
        public final static Property InsertTimestamp = new Property(4, java.util.Date.class, "insertTimestamp", false, "INSERT_TIMESTAMP");
        public final static Property RemoteId = new Property(5, int.class, "remoteId", false, "REMOTE_ID");
        public final static Property RemoteCategoryId = new Property(6, int.class, "remoteCategoryId", false, "REMOTE_CATEGORY_ID");
        public final static Property CategoryTitle = new Property(7, String.class, "categoryTitle", false, "CATEGORY_TITLE");
        public final static Property Content = new Property(8, String.class, "content", false, "CONTENT");
        public final static Property FlagLettura = new Property(9, boolean.class, "flagLettura", false, "FLAG_LETTURA");
        public final static Property Url = new Property(10, String.class, "url", false, "URL");
        public final static Property Details = new Property(11, String.class, "details", false, "DETAILS");
        public final static Property Type = new Property(12, String.class, "type", false, "TYPE");
        public final static Property Date = new Property(13, java.util.Date.class, "date", false, "DATE");
    }

    private final ArticoloDetails_DBConverter detailsConverter = new ArticoloDetails_DBConverter();
    private final ArticoloType_DBConverter typeConverter = new ArticoloType_DBConverter();

    public ArticoloDBDao(DaoConfig config) {
        super(config);
    }
    
    public ArticoloDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ARTICOLO_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TITLE\" TEXT NOT NULL ," + // 1: title
                "\"PUB_DATE\" INTEGER NOT NULL ," + // 2: pubDate
                "\"MODIFIED_DATE\" INTEGER NOT NULL ," + // 3: modifiedDate
                "\"INSERT_TIMESTAMP\" INTEGER NOT NULL ," + // 4: insertTimestamp
                "\"REMOTE_ID\" INTEGER NOT NULL UNIQUE ," + // 5: remoteId
                "\"REMOTE_CATEGORY_ID\" INTEGER NOT NULL ," + // 6: remoteCategoryId
                "\"CATEGORY_TITLE\" TEXT NOT NULL ," + // 7: categoryTitle
                "\"CONTENT\" TEXT NOT NULL ," + // 8: content
                "\"FLAG_LETTURA\" INTEGER NOT NULL ," + // 9: flagLettura
                "\"URL\" TEXT NOT NULL ," + // 10: url
                "\"DETAILS\" TEXT NOT NULL ," + // 11: details
                "\"TYPE\" TEXT NOT NULL ," + // 12: type
                "\"DATE\" INTEGER);"); // 13: date
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_ARTICOLO_DB_REMOTE_ID ON \"ARTICOLO_DB\"" +
                " (\"REMOTE_ID\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_ARTICOLO_DB_TYPE ON \"ARTICOLO_DB\"" +
                " (\"TYPE\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ARTICOLO_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ArticoloDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTitle());
        stmt.bindLong(3, entity.getPubDate().getTime());
        stmt.bindLong(4, entity.getModifiedDate().getTime());
        stmt.bindLong(5, entity.getInsertTimestamp().getTime());
        stmt.bindLong(6, entity.getRemoteId());
        stmt.bindLong(7, entity.getRemoteCategoryId());
        stmt.bindString(8, entity.getCategoryTitle());
        stmt.bindString(9, entity.getContent());
        stmt.bindLong(10, entity.getFlagLettura() ? 1L: 0L);
        stmt.bindString(11, entity.getUrl());
        stmt.bindString(12, detailsConverter.convertToDatabaseValue(entity.getDetails()));
        stmt.bindString(13, typeConverter.convertToDatabaseValue(entity.getType()));
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(14, date.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ArticoloDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTitle());
        stmt.bindLong(3, entity.getPubDate().getTime());
        stmt.bindLong(4, entity.getModifiedDate().getTime());
        stmt.bindLong(5, entity.getInsertTimestamp().getTime());
        stmt.bindLong(6, entity.getRemoteId());
        stmt.bindLong(7, entity.getRemoteCategoryId());
        stmt.bindString(8, entity.getCategoryTitle());
        stmt.bindString(9, entity.getContent());
        stmt.bindLong(10, entity.getFlagLettura() ? 1L: 0L);
        stmt.bindString(11, entity.getUrl());
        stmt.bindString(12, detailsConverter.convertToDatabaseValue(entity.getDetails()));
        stmt.bindString(13, typeConverter.convertToDatabaseValue(entity.getType()));
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(14, date.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ArticoloDB readEntity(Cursor cursor, int offset) {
        ArticoloDB entity = new ArticoloDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // title
            new java.util.Date(cursor.getLong(offset + 2)), // pubDate
            new java.util.Date(cursor.getLong(offset + 3)), // modifiedDate
            new java.util.Date(cursor.getLong(offset + 4)), // insertTimestamp
            cursor.getInt(offset + 5), // remoteId
            cursor.getInt(offset + 6), // remoteCategoryId
            cursor.getString(offset + 7), // categoryTitle
            cursor.getString(offset + 8), // content
            cursor.getShort(offset + 9) != 0, // flagLettura
            cursor.getString(offset + 10), // url
            detailsConverter.convertToEntityProperty(cursor.getString(offset + 11)), // details
            typeConverter.convertToEntityProperty(cursor.getString(offset + 12)), // type
            cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)) // date
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ArticoloDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.getString(offset + 1));
        entity.setPubDate(new java.util.Date(cursor.getLong(offset + 2)));
        entity.setModifiedDate(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setInsertTimestamp(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setRemoteId(cursor.getInt(offset + 5));
        entity.setRemoteCategoryId(cursor.getInt(offset + 6));
        entity.setCategoryTitle(cursor.getString(offset + 7));
        entity.setContent(cursor.getString(offset + 8));
        entity.setFlagLettura(cursor.getShort(offset + 9) != 0);
        entity.setUrl(cursor.getString(offset + 10));
        entity.setDetails(detailsConverter.convertToEntityProperty(cursor.getString(offset + 11)));
        entity.setType(typeConverter.convertToEntityProperty(cursor.getString(offset + 12)));
        entity.setDate(cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ArticoloDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ArticoloDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ArticoloDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
