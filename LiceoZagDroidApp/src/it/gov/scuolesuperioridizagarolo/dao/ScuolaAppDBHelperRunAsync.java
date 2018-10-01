package it.gov.scuolesuperioridizagarolo.dao;

import android.content.Context;

/**
 * Created by stefano on 09/06/15.
 */
public interface ScuolaAppDBHelperRunAsync {
    void run(DaoSession session, Context ctx) throws Throwable;

    void onPostExecuteRunUI_OnError(Throwable e);

    void onPostExecuteRunUI_OnSuccess();

    void onCancelledRunUI();
}
