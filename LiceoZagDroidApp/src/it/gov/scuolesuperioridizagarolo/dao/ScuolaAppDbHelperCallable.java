package it.gov.scuolesuperioridizagarolo.dao;

import android.content.Context;

/**
 * Created by stefano on 09/06/15.
 */
public interface ScuolaAppDbHelperCallable<T> {

    T call(DaoSession session, Context ctx) throws Throwable;
}
