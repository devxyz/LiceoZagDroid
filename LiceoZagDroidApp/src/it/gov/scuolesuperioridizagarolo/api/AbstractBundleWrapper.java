package it.gov.scuolesuperioridizagarolo.api;

import android.os.Bundle;

/**
 * Created by stefano on 11/01/2018.
 */
public class AbstractBundleWrapper {
    protected final Bundle b;

    public AbstractBundleWrapper(Bundle b) {
        if (b == null) {
            b = new Bundle();
        }
        this.b = b;
    }

    public Bundle getBundle() {
        return b;
    }


}
