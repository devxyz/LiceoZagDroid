package it.gov.scuolesuperioridizagarolo.action.api;

import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 06/04/15.
 */
public interface ActivityAction {
    public void doTask(MainMenuActivity activity, DataMenuInfo item);
}
