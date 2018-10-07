package it.gov.scuolesuperioridizagarolo.dao.customType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by stefano on 05/10/2018.
 */
public class ArticoloAttachmentDetails implements Serializable {
    private Long id;
    private String filename;
    private String url;
    private int filesize;
    private int state;
    private java.util.Date insertTimestamp;
    private String filetype;

    public ArticoloAttachmentDetails() {

    }

    public ArticoloAttachmentDetails(String filename, int filesize, String filetype, Long id, Date insertTimestamp, int state, String url) {

        this.filename = filename;
        this.filesize = filesize;
        this.filetype = filetype;
        this.id = id;
        this.insertTimestamp = insertTimestamp;
        this.state = state;
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(Date insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
