package it.gov.scuolesuperioridizagarolo.action;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 06/04/15.
 */
public class LibriTestoLiceoAction implements ActivityAction {
    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(activity.getString(R.string.url_libri_testo_liceo)));
        activity.startActivity(i);
    }
}
