package it.gov.scuolesuperioridizagarolo.dao.customType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by stefano on 05/10/2018.
 */
public class ArticoloTagDetails implements Serializable, Comparable<ArticoloTagDetails> {
    private String tag;

    private int remoteTagId;

    private java.util.Date insertTimestamp;

    public ArticoloTagDetails() {
    }

    public ArticoloTagDetails(String tag, int remoteTagId, Date insertTimestamp) {
        this.tag = tag.toLowerCase();
        this.remoteTagId = remoteTagId;
        this.insertTimestamp = insertTimestamp;
    }

    public Date getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(Date insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public int getRemoteTagId() {
        return remoteTagId;
    }

    public void setRemoteTagId(int remoteTagId) {
        this.remoteTagId = remoteTagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticoloTagDetails that = (ArticoloTagDetails) o;

        return remoteTagId == that.remoteTagId;

    }

    @Override
    public int hashCode() {
        return remoteTagId;
    }

    @Override
    public int compareTo(ArticoloTagDetails another) {
        return Integer.valueOf(remoteTagId).compareTo(another.getRemoteTagId());
    }
}
