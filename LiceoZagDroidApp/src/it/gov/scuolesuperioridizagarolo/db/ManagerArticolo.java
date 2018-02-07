package it.gov.scuolesuperioridizagarolo.db;

import it.gov.scuolesuperioridizagarolo.dao.*;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

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

        final QueryBuilder<ArticoloDB> elencoArticoliDaRimuovere = session.getArticoloDBDao().queryBuilder();
        elencoArticoliDaRimuovere.where(ArticoloDBDao.Properties.RemoteId.lt(minRemoteId));


        final List<ArticoloDB> list = elencoArticoliDaRimuovere.list();
        if (list.size() == 0) return;

        List<Long> idArticlesToBeRemoved = new ArrayList<>(list.size());
        for (ArticoloDB articoloDB : list) {
            idArticlesToBeRemoved.add(articoloDB.getId());
        }

        //=========================================================
        final DeleteQuery<AttachmentArticoloDB> queryDeleteAttachmentArticoloDB = session.getAttachmentArticoloDBDao().
                queryBuilder().where(AttachmentArticoloDBDao.Properties.Fk_articleId.in(idArticlesToBeRemoved)).buildDelete();
        queryDeleteAttachmentArticoloDB.executeDeleteWithoutDetachingEntities();


        //=========================================================
        final DeleteQuery<TagArticoloDB> queryDeleteTagArticoloDB = session.getTagArticoloDBDao().
                queryBuilder().where(TagArticoloDBDao.Properties.Fk_articleId.in(idArticlesToBeRemoved)).buildDelete();
        queryDeleteTagArticoloDB.executeDeleteWithoutDetachingEntities();

        //=========================================================
        final DeleteQuery<ArticoloDB> queryDeleteArticleDB = elencoArticoliDaRimuovere.buildDelete();
        queryDeleteArticleDB.executeDeleteWithoutDetachingEntities();


    }
}
