package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 06/04/15.
 */
public class OpenMenuAction implements ActivityAction {

    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        showPopup(activity);

    }

    public void showPopup(final MainMenuActivity activity) {
        PopupMenu popup = new PopupMenu(activity, activity.getCurrentFragment().getView());
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return activity.onOptionsItemSelected(item);
            }
        });
        popup.show();
    }

}
