package it.gov.scuolesuperioridizagarolo.dao;

import org.greenrobot.greendao.annotation.*;

import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ATTACHMENT_ARTICOLO_DB".
 */
@Entity(active = true)
public class AttachmentArticoloDB {

    @Id
    private Long id;
    private String filename;
    private String url;
    private Integer filesize;
    private Integer state;
    private java.util.Date insertTimestamp;
    private String filetype;

    @Index
    private Long fk_articleId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient AttachmentArticoloDBDao myDao;

    @ToOne(joinProperty = "fk_articleId")
    private ArticoloDB articoloDB;

    @Generated
    private transient Long articoloDB__resolvedKey;

    @Generated
    public AttachmentArticoloDB() {
    }

    public AttachmentArticoloDB(Long id) {
        this.id = id;
    }

    @Generated
    public AttachmentArticoloDB(Long id, String filename, String url, Integer filesize, Integer state, java.util.Date insertTimestamp, String filetype, Long fk_articleId) {
        this.id = id;
        this.filename = filename;
        this.url = url;
        this.filesize = filesize;
        this.state = state;
        this.insertTimestamp = insertTimestamp;
        this.filetype = filetype;
        this.fk_articleId = fk_articleId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAttachmentArticoloDBDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public java.util.Date getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(java.util.Date insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Long getFk_articleId() {
        return fk_articleId;
    }

    public void setFk_articleId(Long fk_articleId) {
        this.fk_articleId = fk_articleId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public ArticoloDB getArticoloDB() {
        Long __key = this.fk_articleId;
        if (articoloDB__resolvedKey == null || !articoloDB__resolvedKey.equals(__key)) {
            __throwIfDetached();
            ArticoloDBDao targetDao = daoSession.getArticoloDBDao();
            ArticoloDB articoloDBNew = targetDao.load(__key);
            synchronized (this) {
                articoloDB = articoloDBNew;
            	articoloDB__resolvedKey = __key;
            }
        }
        return articoloDB;
    }

    @Generated
    public void setArticoloDB(ArticoloDB articoloDB) {
        synchronized (this) {
            this.articoloDB = articoloDB;
            fk_articleId = articoloDB == null ? null : articoloDB.getId();
            articoloDB__resolvedKey = fk_articleId;
        }
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
