package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.services.UpdateThreadArticoliUtil;

/**
 * Created by stefano on 02/02/2018.
 */
public class DownloadArticlesAction implements ActivityAction {
    @Override
    public void doTask(final MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {


        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    UpdateThreadArticoliUtil.updateAndSave(activity);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
        };
        Thread t = new Thread(r);
        t.start();
    }

}
