package it.gov.scuolesuperioridizagarolo.action;

import android.content.DialogInterface;
import android.os.Bundle;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.InitActivity;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerDialogErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 06/04/15.
 */
public class AppUserChooserAction implements ActivityAction {
    @Override
    public void doTask(final MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        InitActivity.chooseUserType(activity, new OnClickListenerDialogErrorCheck(activity) {
            @Override
            protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                activity.reInitUser();
                activity.doAction(0, null);
            }
        }, true);
    }
}
