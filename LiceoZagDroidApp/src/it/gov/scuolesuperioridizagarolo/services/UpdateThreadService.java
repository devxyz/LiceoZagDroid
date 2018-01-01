package it.gov.scuolesuperioridizagarolo.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.dao.TimetableDB;
import it.gov.scuolesuperioridizagarolo.db.ManagerTimetables;
import it.gov.scuolesuperioridizagarolo.util.StreamAndroidUtil;
import it.gov.scuolesuperioridizagarolo.util.ThreadUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefano on 04/11/2016.
 */
class UpdateThreadService implements Runnable {

    private UpdateService updateService;


    public UpdateThreadService(UpdateService updateService) {
        this.updateService = updateService;
    }

    private int updateTimetables() throws IOException {

        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(updateService.getApplicationContext());

        final ArrayList<String> remoteNameFile = getRemoteUrls();

        Log.e(getClass().getSimpleName(), "Elenco nomi remoti: " + remoteNameFile);

        Set<String> localUrls = new HashSet<>();
        {
            Log.e(getClass().getSimpleName(), "UPDATE 1");
            try {
                localUrls = db.runInTransaction(new ScuolaAppDbHelperCallable<Set<String>>() {
                    @Override
                    public Set<String> call(DaoSession session, Context ctx) throws Throwable {
                        ManagerTimetables m = new ManagerTimetables(session);
                        return m.getAllUrls();
                    }
                });

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        Log.e(getClass().getSimpleName(), "Elenco url locali: " + localUrls);

        int count = 0;

        //carica i soli orari non ancora nel database
        for (final String nomeFile : remoteNameFile) {
            if (!localUrls.contains(nomeFile)) {
                final String fullUrl = updateService.getString(R.string.url_timetable_prefix) + nomeFile;
                final byte[] remoteData = getRemoteData(fullUrl);
                Log.e(getClass().getSimpleName(), "UPDATE 2 " + fullUrl);

                try {
                    final TimetableDB timetableDB = db.runInTransaction(new ScuolaAppDbHelperCallable<TimetableDB>() {
                        @Override
                        public TimetableDB call(DaoSession session, Context ctx) throws Throwable {
                            ManagerTimetables m = new ManagerTimetables(session);
                            return m.createNew(fullUrl, nomeFile, remoteData);
                        }
                    });
                    count++;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        //rimuove orai non piu' validi
        try {
            count += db.runInTransaction(new ScuolaAppDbHelperCallable<Integer>() {
                @Override
                public Integer call(DaoSession session, Context ctx) throws Throwable {
                    return new ManagerTimetables(session).removeTimetablesWithNameNotInCollection(remoteNameFile);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        db.close();


        return count;
    }

    private byte[] getRemoteData(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        return StreamAndroidUtil.loadFileContentByteArray(con.getInputStream());
    }

    private ArrayList<String> getRemoteUrls() throws IOException {
        final String urlTimetableIndex = updateService.getApplicationContext().getString(R.string.url_timetable_index);

        URL obj = new URL(urlTimetableIndex);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        ArrayList<String> url = new ArrayList<>();
        BufferedReader wr = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = wr.readLine()) != null) {
            line = line.trim();
            if (line.length() > 0)
                url.add(line);
        }
        wr.close();
        return url;
    }

    @Override
    public void run() {


        sendMessageToMainActivity("Aggiornamento dati in corso", false);


        try {


            Log.e(getClass().getSimpleName(), "UPDATE");

            //attende che la rete dati sia disponibile
            int i = 0;
            while (!UpdateService.isNetworkAvailable(updateService) && i < 10) {
                ThreadUtil.sleep(10000);
                i++;
            }


            //final NotificationMessage n = NotificationUtil.updateProcessMessage();
            //n.show(updateService);


            final int num = updateTimetables();

            //n.cancel(updateService);

            if (num > 0) {
                //notifica_nuove_notizie nuove circolari aggiunte
                //notifica_nuove_notizie(data);
                //final NotificationMessage notificationMessage = NotificationUtil.newDataAvailableMessage(data.circolariDaAggiungereAggiornare.size() + data.newsDaAggiungereAggiornare.size());

                //aggiorna l'interfaccia grafica, se possibile
                sendMessageToMainActivity("Aggiornamento completato: " + num + " file scaricati", true);
            } else {
                sendMessageToMainActivity("Nessun nuovo aggiornamento trovato", true);
            }

        } catch (Throwable throwable) {
            //notifica errore
            //NotificationUtil.updateProcessMessage().cancel(updateService);
            //NotificationUtil.errorMessage(throwable).cancel(updateService);
            throwable.printStackTrace();
            sendMessageToMainActivity("Errore durante l'aggiornamento", false);
        }


        //set lastuupdate timestamp
        UpdateService.updateDone(updateService);


    }

    private void sendMessageToMainActivity(String value, boolean shouldUpdateUI) {
        Intent x = new Intent(MainMenuActivity.RECEIVER_ACTION_UPDATE);
        x.putExtra(UpdateService.KEY_MESSAGGIO_UPDATE, value);
        x.putExtra(UpdateService.KEY_SHOULD_UPDATE, shouldUpdateUI);
        updateService.sendBroadcast(x);
    }
}
