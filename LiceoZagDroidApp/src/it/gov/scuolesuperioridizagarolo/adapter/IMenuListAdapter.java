package it.gov.scuolesuperioridizagarolo.adapter;

import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

/**
 * Created by stefano on 22/07/15.
 */
public interface IMenuListAdapter {
    DataMenuInfo searchByMenuID(int menuID);

    DataMenuInfo getDataMenuInfo(int pos);

    int positionByMenu(DataMenuInfo d);
}
