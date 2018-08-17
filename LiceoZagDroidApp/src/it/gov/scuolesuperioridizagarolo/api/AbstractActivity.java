package it.gov.scuolesuperioridizagarolo.api;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import it.gov.scuolesuperioridizagarolo.cache.UrlFileCache;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;
import it.gov.scuolesuperioridizagarolo.util.ThreadUtil;

/**
 * Created by stefano on 27/05/15.
 */
public abstract class AbstractActivity extends Activity {
    private UrlFileCache cache;
    private ScuolaAppDbHelper database;

    public AbstractActivity() {
    }

    public ScuolaAppDbHelper getDatabase() {
        return database;
    }

    public final SharedPreferenceWrapper getSharedPreferences() {
        return SharedPreferenceWrapper.getCommonInstance(getActivity());
    }

    public final Activity getActivity() {
        return this;
    }

    public final UrlFileCache getCache() {
        return cache;
    }

    public final void runOnUiThreadBlocking(Runnable r) {
        ThreadUtil.runOnUiThreadAndWait(this, r);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cache = new UrlFileCache(this);
        database = new ScuolaAppDbHelper(getActivity());

    }
}
