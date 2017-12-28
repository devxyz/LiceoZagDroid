package it.gov.scuolesuperioridizagarolo.cache;

import android.content.Context;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDBHelperRun;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.model.C_NormalizedURL;

import java.io.File;

/**
 * Created by stefano on 26/05/15.
 */
public abstract class AsyncUrlLoaderCallbackDB implements AsyncUrlLoaderCallback {
    private volatile boolean flagCancel = false;

    protected final AbstractActivity ctx;

    public AsyncUrlLoaderCallbackDB(AbstractActivity ctx) {
        this.ctx = ctx;
    }


    @Override
    public final void onCancelled(final C_NormalizedURL url) {
        onCancelImpl(url);
    }

    @Override
    public boolean isCancelled() {
        return flagCancel;
    }

    protected abstract void onLoadFinishedImpl(C_NormalizedURL url, File f, DaoSession session) throws Throwable;

    protected abstract void onLoadErrorImpl(C_NormalizedURL url, Throwable error);

    protected abstract void onQueueForDownloadImpl(C_NormalizedURL url);

    protected abstract void onCancelImpl(C_NormalizedURL url);

    @Override
    public final void onLoadFinished(final C_NormalizedURL url, final File f) {


        ScuolaAppDbHelper db = new ScuolaAppDbHelper(ctx.getActivity());
        try {
            db.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    onLoadFinishedImpl(url, f, session);
                }
            });

        } catch (Throwable ex) {
            db.close();
            onLoadError(url, ex);
        } finally {
            db.close();
        }
    }

    @Override
    public final void onLoadError(final C_NormalizedURL url, final Throwable error) {
        onLoadErrorImpl(url, error);
    }

    @Override
    public final void onQueueForDownload(final C_NormalizedURL url) {
        onQueueForDownloadImpl(url);
    }

}
