package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.LoginActivity;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

/**
 * Created by stefano on 06/04/15.
 */
public class AppUserChooserAction implements ActivityAction {
    @Override
    public void doTask(final MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        DialogUtil.openAskDialog(activity, "Conferma", "Vuoi modificare il profilo di accesso?",
                new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferenceWrapper.getCommonInstance(activity).setUserType(null);
                        activity.finish();
                        LoginActivity.startLoginActivity(activity);
                    }
                },
                new Runnable() {
                    @Override
                    public void run() {

                    }
                });
    }
}
