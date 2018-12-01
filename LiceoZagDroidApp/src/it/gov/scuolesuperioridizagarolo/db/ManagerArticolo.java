package it.gov.scuolesuperioridizagarolo.db;

import android.util.Log;
import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.ArticoloDBDao;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetailsCircolare;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloType;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdo;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdoContainer;
import it.gov.scuolesuperioridizagarolo.util.DebugUtil;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
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

    public Date getModifiedRemoteDate() {
        final ArticoloDB unique = session.getArticoloDBDao().queryBuilder().orderDesc(ArticoloDBDao.Properties.ModifiedDate).limit(1).unique();
        if (unique == null) {
            return null;
        }
        return unique.getModifiedDate();
    }

    //rimuove tutti gli articoli con remoteid minore di quello specificato
    public void removeArticoliPrecedentiAdID(int minRemoteId) {

        final QueryBuilder<ArticoloDB> elencoArticoliDaRimuovere = session.getArticoloDBDao().queryBuilder();
        elencoArticoliDaRimuovere.where(ArticoloDBDao.Properties.RemoteId.lt(minRemoteId));


        final List<ArticoloDB> list = elencoArticoliDaRimuovere.list();
        if (list.size() == 0) return;

        //=========================================================
        final DeleteQuery<ArticoloDB> queryDeleteArticleDB = elencoArticoliDaRimuovere.buildDelete();
        queryDeleteArticleDB.executeDeleteWithoutDetachingEntities();
    }

    public ArticoloSdoContainer<ArticoloDetailsCircolare> elencoArticoliCircolari() {
        if (DebugUtil.DEBUG__ManagerArticolo) {
            Log.w("ManagerArticolo", "START elencoArticoliCircolari");
        }


        //elenco circolari
        final QueryBuilder<ArticoloDB> qb = session.getArticoloDBDao().queryBuilder();
        qb.where(ArticoloDBDao.Properties.Type.eq(ArticoloType.CIRCOLARE.name()));
        final List<ArticoloDB> listArticolo = qb.list();
        if (DebugUtil.DEBUG__ManagerArticolo) {
            Log.w("ManagerArticolo", "ARTICOLI TOTALI:" + listArticolo.size());
        }


        if (DebugUtil.DEBUG__ManagerArticolo) {
            Log.w("ManagerArticolo", "concatenate elencoArticoliCircolari");
        }

        final Map<Long, ArticoloSdo<ArticoloDetailsCircolare>> map = new TreeMap<>();

        //articolo
        for (ArticoloDB a : listArticolo) {
            final ArticoloDetails details = a.getDetails();
            if (details == null) {
                throw new NullPointerException("Dettagli nulli per articolo " + a);
            }
            ArticoloSdo<ArticoloDetailsCircolare> s = new ArticoloSdo<>(a, ArticoloDetailsCircolare.class);
            map.put(a.getId(), s);
        }

        //articoli
        ArticoloSdoContainer<ArticoloDetailsCircolare> s = new ArticoloSdoContainer<>();
        s.articoli.addAll(map.values());
        if (DebugUtil.DEBUG__ManagerArticolo) {
            Log.w("ManagerArticolo", "END elencoArticoliCircolari");
        }

        return s;
    }


}
