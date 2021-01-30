package it.gov.scuolesuperioridizagarolo.services;

import android.content.Intent;
import android.util.Log;
import it.gov.scuolesuperioridizagarolo.db.BitOrrioGrigliaOrarioContainerSingleton;
import it.gov.scuolesuperioridizagarolo.util.ThreadUtil;

/**
 * Created by stefano on 04/11/2016.
 */
class UpdateThreadService implements Runnable {

    private UpdateService updateService;


    public UpdateThreadService(UpdateService updateService) {
        this.updateService = updateService;
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


            final int num = UpdateThreadTimetablesUtil.updateTimetables(updateService);

            //n.cancel(updateService);

            if (num > 0) {
                //notifica_nuove_notizie nuove circolari aggiunte
                //notifica_nuove_notizie(data);
                //final NotificationMessage notificationMessage = NotificationUtil.newDataAvailableMessage(data.circolariDaAggiungereAggiornare.size() + data.newsDaAggiungereAggiornare.size());

                //aggiorna l'interfaccia grafica, se possibile
                sendMessageToMainActivity("Aggiornamento completato: " + num + " file scaricati", true);
                //cancella ram e ricarica dati
                BitOrrioGrigliaOrarioContainerSingleton.forceReloadData();

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
        Intent x = new Intent(UpdateService.RECEIVER_DATA_UPDATE);
        x.putExtra(UpdateService.KEY_MESSAGGIO_UPDATE, value);
        x.putExtra(UpdateService.KEY_SHOULD_UPDATE, shouldUpdateUI);
        updateService.sendBroadcast(x);
    }
}
