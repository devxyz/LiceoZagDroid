package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 06/04/15.
 */
public class CloseAction implements ActivityAction {

    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        activity.finish();

    }
}
