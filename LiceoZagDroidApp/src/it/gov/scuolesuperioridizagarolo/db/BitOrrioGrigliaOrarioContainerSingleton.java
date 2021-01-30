package it.gov.scuolesuperioridizagarolo.db;

import android.content.Context;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;

import java.util.Date;

/**
 * gestione container singleton ottimizzato(evita caricamento del container nel caso di richieste multiple)
 */
public class BitOrrioGrigliaOrarioContainerSingleton {
    private static BitOrarioGrigliaOrarioContainer containerOrari = null;
    //data/ora in cui caricati i dati
    private static C_MyDate dataCaricamento = null;

    public static synchronized void forceReloadData(){
        dataCaricamento = null;
        containerOrari = null;
    }

    public static synchronized BitOrarioGrigliaOrarioContainer getInstance(MainMenuActivity ctx) {

        Date lastDataUpdate = ctx.getSharedPreferences().getLastDataUpdate();
        //se manca update download orario
        if (containerOrari == null || dataCaricamento == null) {
            containerOrari = null;
            dataCaricamento = null;
        } else if (lastDataUpdate == null) {
            containerOrari = null;
            dataCaricamento = null;
        } else {
            C_MyDate downloadDate = new C_MyDate(lastDataUpdate);
            //se data caricsamento in ram e' inferiore ad aggiornamento, ricarico
            if (dataCaricamento.compareTo(downloadDate) < 0) {
                containerOrari = null;
                dataCaricamento = null;

            }
        }

        if (containerOrari != null)
            return containerOrari;

        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(ctx);
        containerOrari = new BitOrarioGrigliaOrarioContainer();
        try {
            containerOrari = db.runInTransaction(new ScuolaAppDbHelperCallable<BitOrarioGrigliaOrarioContainer>() {
                @Override
                public BitOrarioGrigliaOrarioContainer call(DaoSession session, Context ctx) throws Throwable {
                    return new ManagerTimetables(session).loadBitOrarioGrigliaOrarioContainer();
                }
            });
        } catch (Throwable throwable) {
            throw new IllegalArgumentException(throwable);
        }
        //data aggiornamento
        dataCaricamento = new C_MyDate(new Date());
        db.close();
        return containerOrari;
    }
}
