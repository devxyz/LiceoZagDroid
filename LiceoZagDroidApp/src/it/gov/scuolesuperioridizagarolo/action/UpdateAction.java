package it.gov.scuolesuperioridizagarolo.action;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.services.UpdateService;

/**
 * Created by stefano on 31/12/2017.
 */
public class UpdateAction implements ActivityAction {
    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {

        Intent serviceIntent = new Intent(activity, UpdateService.class);
        activity.startService(serviceIntent);

    }
}
