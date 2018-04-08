package it.gov.scuolesuperioridizagarolo.db;

import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdo;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdoContainer;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloTypeCircolare;
import it.gov.scuolesuperioridizagarolo.model.articolo.WrapperArticoloDB;
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
    public void rimoveArticoliPrecedentiAdID(int minRemoteId) {

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

    public ArticoloSdoContainer<ArticoloTypeCircolare> elencoArticoliCircolari() {
        final List<AttachmentArticoloDB> listAttachment = session.getAttachmentArticoloDBDao().queryBuilder().list();
        final List<TagArticoloDB> listTag = session.getTagArticoloDBDao().queryBuilder().list();

        //elenco circolari
        final QueryBuilder<ArticoloDB> qb = session.getArticoloDBDao().queryBuilder();
        qb.where(ArticoloDBDao.Properties.JsonClass.eq(ArticoloTypeCircolare.class.getName()));
        final List<ArticoloDB> listArticolo = qb.list();

        final Map<Long, ArticoloSdo<ArticoloTypeCircolare>> map = new TreeMap<>();

        //articolo
        for (ArticoloDB a : listArticolo) {
            ArticoloSdo<ArticoloTypeCircolare> s = new ArticoloSdo<ArticoloTypeCircolare>(new WrapperArticoloDB<>(ArticoloTypeCircolare.class, a));
            map.put(a.getId(), s);
        }

        //tag
        for (TagArticoloDB t : listTag) {
            final ArticoloSdo<ArticoloTypeCircolare> x = map.get(t.getFk_articleId());
            if (x != null)
                x.tags.add(t);
        }

        //attachment
        for (AttachmentArticoloDB t : listAttachment) {
            final ArticoloSdo<ArticoloTypeCircolare> x = map.get(t.getFk_articleId());
            if (x != null)
                x.attachments.add(t);
        }

        //articoli
        ArticoloSdoContainer<ArticoloTypeCircolare> s = new ArticoloSdoContainer<>();
        s.articoli.addAll(map.values());

        return s;
    }


}
