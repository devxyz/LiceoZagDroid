package it.gov.scuolesuperioridizagarolo.dao;

import android.content.Context;

/**
 * Created by stefano on 09/06/15.
 */
public interface ScuolaAppDBHelperRun {
    public void run(DaoSession session, Context ctx) throws Throwable;
}
