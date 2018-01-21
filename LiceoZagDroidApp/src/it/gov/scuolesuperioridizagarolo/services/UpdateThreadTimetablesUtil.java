package it.gov.scuolesuperioridizagarolo.services;

import android.content.Context;
import android.util.Log;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.dao.TimetableDB;
import it.gov.scuolesuperioridizagarolo.db.ManagerTimetables;
import it.gov.scuolesuperioridizagarolo.util.StreamAndroidUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefano on 21/01/2018.
 */
public class UpdateThreadTimetablesUtil {
    /**
     * restituisce gli url degli orari pubblicati (ciascun file e' zip)
     *
     * @param urlTimetableIndex
     * @return
     * @throws IOException
     */
    private static ArrayList<String> getRemoteTimetablesUrlFiles(String urlTimetableIndex) throws IOException {
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

    /**
     * scarica la lista degli orari disponibili e aggiorna il db locale
     *
     * @param updateService
     * @return
     * @throws IOException
     */
    public static int updateTimetables(UpdateService updateService) throws IOException {

        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(updateService.getApplicationContext());

        final String urlTimetableIndex = updateService.getApplicationContext().getString(R.string.url_timetable_index);
        final ArrayList<String> remoteNameFile = getRemoteTimetablesUrlFiles(urlTimetableIndex);

        Log.e(UpdateThreadTimetablesUtil.class.getSimpleName(), "Elenco nomi remoti: " + remoteNameFile);

        Set<String> localUrls = new HashSet<>();
        {
            Log.e(UpdateThreadTimetablesUtil.class.getSimpleName(), "UPDATE 1");
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

        Log.e(UpdateThreadTimetablesUtil.class.getSimpleName(), "Elenco url locali: " + localUrls);

        int count = 0;

        //carica i soli orari non ancora nel database
        for (final String nomeFile : remoteNameFile) {
            if (!localUrls.contains(nomeFile)) {
                final String fullUrl = updateService.getString(R.string.url_timetable_prefix) + nomeFile;
                //carica il file dati dell'orario (ZIP)
                URL obj = new URL(fullUrl);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                final byte[] bytes = StreamAndroidUtil.loadFileContentByteArray(con.getInputStream());
                con.disconnect();
                final byte[] remoteZipData = bytes;
                Log.e(UpdateThreadTimetablesUtil.class.getSimpleName(), "UPDATE 2 " + fullUrl);

                try {
                    db.runInTransaction(new ScuolaAppDbHelperCallable<TimetableDB>() {
                        @Override
                        public TimetableDB call(DaoSession session, Context ctx) throws Throwable {
                            ManagerTimetables m = new ManagerTimetables(session);
                            return m.createNew(fullUrl, nomeFile, remoteZipData);
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

    public static void main(String[] args) {
        System.out.println("Prova");
    }

}
