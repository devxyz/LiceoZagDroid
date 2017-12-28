package it.gov.scuolesuperioridizagarolo.action;

import android.content.Intent;
import android.net.Uri;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 06/04/15.
 */
public class ReStudentiAction implements ActivityAction {
    @Override

    public void doTask(MainMenuActivity activity, DataMenuInfo item) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(activity.getString(R.string.url_re_famiglie)));
        activity.startActivity(i);
    }
}
