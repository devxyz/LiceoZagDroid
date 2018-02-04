package it.gov.scuolesuperioridizagarolo.db;

import it.gov.scuolesuperioridizagarolo.dao.*;
import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by stefano on 02/02/2018.
 */
public class ManagerArticolo {
    private final DaoSession session;

    public ManagerArticolo(DaoSession session) {
        this.session = session;
    }

    /**
     * ritorna il massimo id
     *
     * @return
     */
    public int getMaxRemoteId() {
        final ArticoloDB unique = session.getArticoloDBDao().queryBuilder().orderAsc(ArticoloDBDao.Properties.RemoteId).limit(1).unique();
        if (unique == null) return -1;
        return unique.getRemoteId();
    }


    //rimuove tutti gli articoli con remoteid minore di quello specificato
    public void removeAllLess(int minRemoteId) {
        final QueryBuilder<AttachmentArticoloDB> q1 = session.getAttachmentArticoloDBDao().queryBuilder();


        QueryBuilder.LOG_SQL=true;
        final Join<AttachmentArticoloDB, ArticoloDB> q2 = q1.join(ArticoloDB.class, AttachmentArticoloDBDao.Properties.Fk_articleId);
        q2.where(ArticoloDBDao.Properties.RemoteId.lt(minRemoteId));
        final Query<AttachmentArticoloDB> build = q1.build();
        build.list();


    }
}
