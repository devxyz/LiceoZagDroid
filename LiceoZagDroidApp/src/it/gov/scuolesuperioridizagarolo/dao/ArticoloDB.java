package it.gov.scuolesuperioridizagarolo.dao;

import org.greenrobot.greendao.annotation.*;

import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ARTICOLO_DB".
 */
@Entity
public class ArticoloDB {

    @Id
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private java.util.Date pubDate;

    @NotNull
    private java.util.Date insertTimestamp;

    @Unique
    @Index
    private int remoteId;
    private int remoteCategoryId;

    @NotNull
    private String categoryTitle;

    @NotNull
    private String content;
    private boolean flagLettura;

    @NotNull
    private String url;

    @Convert(converter = it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails_DBConverter.class, columnType = String.class)
    @NotNull
    private ArticoloDetails details;

    @Convert(converter = it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloType_DBConverter.class, columnType = String.class)
    @NotNull
    private ArticoloType type;
    private java.util.Date date;

    @Generated
    public ArticoloDB() {
    }

    public ArticoloDB(Long id) {
        this.id = id;
    }

    @Generated
    public ArticoloDB(Long id, String title, java.util.Date pubDate, java.util.Date insertTimestamp, int remoteId, int remoteCategoryId, String categoryTitle, String content, boolean flagLettura, String url, ArticoloDetails details, ArticoloType type, java.util.Date date) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.insertTimestamp = insertTimestamp;
        this.remoteId = remoteId;
        this.remoteCategoryId = remoteCategoryId;
        this.categoryTitle = categoryTitle;
        this.content = content;
        this.flagLettura = flagLettura;
        this.url = url;
        this.details = details;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    @NotNull
    public java.util.Date getPubDate() {
        return pubDate;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPubDate(@NotNull java.util.Date pubDate) {
        this.pubDate = pubDate;
    }

    @NotNull
    public java.util.Date getInsertTimestamp() {
        return insertTimestamp;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setInsertTimestamp(@NotNull java.util.Date insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public int getRemoteCategoryId() {
        return remoteCategoryId;
    }

    public void setRemoteCategoryId(int remoteCategoryId) {
        this.remoteCategoryId = remoteCategoryId;
    }

    @NotNull
    public String getCategoryTitle() {
        return categoryTitle;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCategoryTitle(@NotNull String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public boolean getFlagLettura() {
        return flagLettura;
    }

    public void setFlagLettura(boolean flagLettura) {
        this.flagLettura = flagLettura;
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
    public ArticoloDetails getDetails() {
        return details;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDetails(@NotNull ArticoloDetails details) {
        this.details = details;
    }

    @NotNull
    public ArticoloType getType() {
        return type;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setType(@NotNull ArticoloType type) {
        this.type = type;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ArticoloDB{" +
                "categoryTitle='" + categoryTitle + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", pubDate=" + pubDate +
                ", insertTimestamp=" + insertTimestamp +
                ", remoteId=" + remoteId +
                ", remoteCategoryId=" + remoteCategoryId +
                ", content='" + content + '\'' +
                ", flagLettura=" + flagLettura +
                ", url='" + url + '\'' +
                ", details=" + details +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
