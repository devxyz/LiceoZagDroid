package it.gov.scuolesuperioridizagarolo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDB_Keywords;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDB_KeywordsConverter;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDB_Type;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDB_TypeConverter;

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
        public final static Property InsertTimestamp = new Property(3, java.util.Date.class, "insertTimestamp", false, "INSERT_TIMESTAMP");
        public final static Property RemoteId = new Property(4, int.class, "remoteId", false, "REMOTE_ID");
        public final static Property RemoteCategoryId = new Property(5, int.class, "remoteCategoryId", false, "REMOTE_CATEGORY_ID");
        public final static Property CategoryTitle = new Property(6, String.class, "categoryTitle", false, "CATEGORY_TITLE");
        public final static Property Content = new Property(7, String.class, "content", false, "CONTENT");
        public final static Property JsonContent = new Property(8, String.class, "jsonContent", false, "JSON_CONTENT");
        public final static Property JsonClass = new Property(9, String.class, "jsonClass", false, "JSON_CLASS");
        public final static Property FlagLettura = new Property(10, boolean.class, "flagLettura", false, "FLAG_LETTURA");
        public final static Property Words = new Property(11, String.class, "words", false, "WORDS");
        public final static Property Url = new Property(12, String.class, "url", false, "URL");
        public final static Property Keywords = new Property(13, String.class, "keywords", false, "KEYWORDS");
        public final static Property CircolareNumber = new Property(14, String.class, "circolareNumber", false, "CIRCOLARE_NUMBER");
        public final static Property Type = new Property(15, String.class, "type", false, "TYPE");
        public final static Property Date = new Property(16, java.util.Date.class, "date", false, "DATE");
    }

    private final ArticoloDB_KeywordsConverter keywordsConverter = new ArticoloDB_KeywordsConverter();
    private final ArticoloDB_TypeConverter typeConverter = new ArticoloDB_TypeConverter();

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
                "\"INSERT_TIMESTAMP\" INTEGER NOT NULL ," + // 3: insertTimestamp
                "\"REMOTE_ID\" INTEGER NOT NULL UNIQUE ," + // 4: remoteId
                "\"REMOTE_CATEGORY_ID\" INTEGER NOT NULL ," + // 5: remoteCategoryId
                "\"CATEGORY_TITLE\" TEXT NOT NULL ," + // 6: categoryTitle
                "\"CONTENT\" TEXT NOT NULL ," + // 7: content
                "\"JSON_CONTENT\" TEXT NOT NULL ," + // 8: jsonContent
                "\"JSON_CLASS\" TEXT NOT NULL ," + // 9: jsonClass
                "\"FLAG_LETTURA\" INTEGER NOT NULL ," + // 10: flagLettura
                "\"WORDS\" TEXT NOT NULL ," + // 11: words
                "\"URL\" TEXT NOT NULL ," + // 12: url
                "\"KEYWORDS\" TEXT NOT NULL ," + // 13: keywords
                "\"CIRCOLARE_NUMBER\" TEXT," + // 14: circolareNumber
                "\"TYPE\" TEXT NOT NULL ," + // 15: type
                "\"DATE\" INTEGER);"); // 16: date
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_ARTICOLO_DB_REMOTE_ID ON \"ARTICOLO_DB\"" +
                " (\"REMOTE_ID\");");
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
        stmt.bindLong(4, entity.getInsertTimestamp().getTime());
        stmt.bindLong(5, entity.getRemoteId());
        stmt.bindLong(6, entity.getRemoteCategoryId());
        stmt.bindString(7, entity.getCategoryTitle());
        stmt.bindString(8, entity.getContent());
        stmt.bindString(9, entity.getJsonContent());
        stmt.bindString(10, entity.getJsonClass());
        stmt.bindLong(11, entity.getFlagLettura() ? 1L: 0L);
        stmt.bindString(12, entity.getWords());
        stmt.bindString(13, entity.getUrl());
        stmt.bindString(14, keywordsConverter.convertToDatabaseValue(entity.getKeywords()));
 
        String circolareNumber = entity.getCircolareNumber();
        if (circolareNumber != null) {
            stmt.bindString(15, circolareNumber);
        }
        stmt.bindString(16, typeConverter.convertToDatabaseValue(entity.getType()));
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(17, date.getTime());
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
        stmt.bindLong(4, entity.getInsertTimestamp().getTime());
        stmt.bindLong(5, entity.getRemoteId());
        stmt.bindLong(6, entity.getRemoteCategoryId());
        stmt.bindString(7, entity.getCategoryTitle());
        stmt.bindString(8, entity.getContent());
        stmt.bindString(9, entity.getJsonContent());
        stmt.bindString(10, entity.getJsonClass());
        stmt.bindLong(11, entity.getFlagLettura() ? 1L: 0L);
        stmt.bindString(12, entity.getWords());
        stmt.bindString(13, entity.getUrl());
        stmt.bindString(14, keywordsConverter.convertToDatabaseValue(entity.getKeywords()));
 
        String circolareNumber = entity.getCircolareNumber();
        if (circolareNumber != null) {
            stmt.bindString(15, circolareNumber);
        }
        stmt.bindString(16, typeConverter.convertToDatabaseValue(entity.getType()));
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(17, date.getTime());
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
            new java.util.Date(cursor.getLong(offset + 3)), // insertTimestamp
            cursor.getInt(offset + 4), // remoteId
            cursor.getInt(offset + 5), // remoteCategoryId
            cursor.getString(offset + 6), // categoryTitle
            cursor.getString(offset + 7), // content
            cursor.getString(offset + 8), // jsonContent
            cursor.getString(offset + 9), // jsonClass
            cursor.getShort(offset + 10) != 0, // flagLettura
            cursor.getString(offset + 11), // words
            cursor.getString(offset + 12), // url
            keywordsConverter.convertToEntityProperty(cursor.getString(offset + 13)), // keywords
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // circolareNumber
            typeConverter.convertToEntityProperty(cursor.getString(offset + 15)), // type
            cursor.isNull(offset + 16) ? null : new java.util.Date(cursor.getLong(offset + 16)) // date
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ArticoloDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.getString(offset + 1));
        entity.setPubDate(new java.util.Date(cursor.getLong(offset + 2)));
        entity.setInsertTimestamp(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setRemoteId(cursor.getInt(offset + 4));
        entity.setRemoteCategoryId(cursor.getInt(offset + 5));
        entity.setCategoryTitle(cursor.getString(offset + 6));
        entity.setContent(cursor.getString(offset + 7));
        entity.setJsonContent(cursor.getString(offset + 8));
        entity.setJsonClass(cursor.getString(offset + 9));
        entity.setFlagLettura(cursor.getShort(offset + 10) != 0);
        entity.setWords(cursor.getString(offset + 11));
        entity.setUrl(cursor.getString(offset + 12));
        entity.setKeywords(keywordsConverter.convertToEntityProperty(cursor.getString(offset + 13)));
        entity.setCircolareNumber(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setType(typeConverter.convertToEntityProperty(cursor.getString(offset + 15)));
        entity.setDate(cursor.isNull(offset + 16) ? null : new java.util.Date(cursor.getLong(offset + 16)));
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
