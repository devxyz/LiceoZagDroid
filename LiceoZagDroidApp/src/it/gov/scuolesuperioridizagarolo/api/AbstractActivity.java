package it.gov.scuolesuperioridizagarolo.api;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import it.gov.scuolesuperioridizagarolo.cache.UrlFileCache;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerDialogErrorCheck;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;
import it.gov.scuolesuperioridizagarolo.util.ThreadUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stefano on 27/05/15.
 */
public abstract class AbstractActivity extends AppCompatActivity {
    private static final int ALL_PERMISSIONS_REQUEST = 111;
    private UrlFileCache cache;
    private ScuolaAppDbHelper database;
    private Bundle onCreate_savedInstanceState;

    public AbstractActivity() {
    }

    public static String[] retrievePermissions(Context context) {
        try {
            return context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS)
                    .requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("This should have never happened.", e);
        }
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
        onCreate_savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        cache = new UrlFileCache(this);
        database = new ScuolaAppDbHelper(getActivity());
        checkPermissionsAndStart();
    }

    private String[] retrieveAllAppPermissions() {
        return retrievePermissions(getActivity());
    }

    private String[] permissionNotEnabled() {
        ArrayList<String> ris = new ArrayList<>();
        for (String p : retrieveAllAppPermissions()) {
            if (ContextCompat.checkSelfPermission(this, p)
                    != PackageManager.PERMISSION_GRANTED) {
                ris.add(p);
            }
        }
        return ris.toArray(new String[ris.size()]);
    }

    private void test() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        ALL_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

    }

    private void checkPermissionsAndStart() {
        final String[] pp = permissionNotEnabled();
        if (pp.length > 0) {
            DialogUtil.openInfoDialog(getActivity(), "Richiesta permessi", "L'applicazione ha bisogno di accedere ad alcune risorse del dispositivo per poter funzionare.\n" +
                            "Consentire l'accesso a tutte le risorse richieste per poter utilizzare l'APP. In caso contrario l'applicazione verrà chiusa",
                    new OnClickListenerDialogErrorCheck(AbstractActivity.this) {
                        @Override
                        protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                            //chiede i permessi e aspetta poi l'esecuzione del metodo di callback onRequestPermissionsResult
                            ActivityCompat.requestPermissions(AbstractActivity.this, pp, ALL_PERMISSIONS_REQUEST);
                        }
                    });
        } else {
            onCreateWithPermissions(onCreate_savedInstanceState);
        }
    }

    /**
     * versione oncreate invocata quando tutti i permessi sono disponibili
     *
     * @param savedInstanceState
     */
    protected abstract void onCreateWithPermissions(Bundle savedInstanceState);


    @Override
    public final void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALL_PERMISSIONS_REQUEST) {
            final String[] pp = permissionNotEnabled();
            if (pp.length > 0) {
                DialogUtil.openInfoDialog(getActivity(), "Richiesta permessi", "Alcuni permessi non sono stati consentiti. L'APP verrà terminata: " + Arrays.asList(pp),
                        new OnClickListenerDialogErrorCheck(AbstractActivity.this) {
                            @Override
                            protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                                //chiude l'activity
                                AbstractActivity.this.finish();
                            }
                        });
            }
            onCreateWithPermissions(onCreate_savedInstanceState);
        } else {
            onCreateWithPermissions(onCreate_savedInstanceState);
        }
    }
}
