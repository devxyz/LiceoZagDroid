package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

/**
 * Created by stefano on 06/04/15.
 */
public class OpenMenuAction implements ActivityAction {

    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        activity.openMenu();

    }
}
