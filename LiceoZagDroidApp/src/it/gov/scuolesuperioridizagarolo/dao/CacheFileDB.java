package it.gov.scuolesuperioridizagarolo.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "CACHE_FILE_DB".
 */
@Entity
public class CacheFileDB {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    @Index
    private String url;

    @NotNull
    private String filename;

    @NotNull
    private java.util.Date insertion_date;
    private boolean load_complete;

    @Generated
    public CacheFileDB() {
    }

    public CacheFileDB(Long id) {
        this.id = id;
    }

    @Generated
    public CacheFileDB(Long id, String url, String filename, java.util.Date insertion_date, boolean load_complete) {
        this.id = id;
        this.url = url;
        this.filename = filename;
        this.insertion_date = insertion_date;
        this.load_complete = load_complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUrl(@NotNull String url) {
        this.url = url;
    }

    @NotNull
    public String getFilename() {
        return filename;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setFilename(@NotNull String filename) {
        this.filename = filename;
    }

    @NotNull
    public java.util.Date getInsertion_date() {
        return insertion_date;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setInsertion_date(@NotNull java.util.Date insertion_date) {
        this.insertion_date = insertion_date;
    }

    public boolean getLoad_complete() {
        return load_complete;
    }

    public void setLoad_complete(boolean load_complete) {
        this.load_complete = load_complete;
    }

}
