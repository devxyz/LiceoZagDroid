package it.gov.scuolesuperioridizagarolo.db;

import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.model.ArticoloSdoContainer;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public ArticoloSdoContainer allArticle() {
        final List<AttachmentArticoloDB> listAttachment = session.getAttachmentArticoloDBDao().queryBuilder().list();
        final List<TagArticoloDB> listTag = session.getTagArticoloDBDao().queryBuilder().list();
        final List<ArticoloDB> listArticolo = session.getArticoloDBDao().queryBuilder().list();

        final Map<Long, ArticoloSdoContainer.ArticoloSdo> map = new TreeMap<>();

        //articolo
        for (ArticoloDB a : listArticolo) {
            ArticoloSdoContainer.ArticoloSdo s = new ArticoloSdoContainer.ArticoloSdo(a);
            map.put(a.getId(), s);
        }

        //tag
        for (TagArticoloDB t : listTag) {
            map.get(t.getFk_articleId()).tags.add(t);
        }

        //attachment
        for (AttachmentArticoloDB t : listAttachment) {
            map.get(t.getFk_articleId()).attachments.add(t);
        }

        //articoli
        ArticoloSdoContainer s = new ArticoloSdoContainer();
        s.articoli.addAll(map.values());

        return s;
    }


}
