package it.gov.scuolesuperioridizagarolo.util;

import android.app.Activity;
import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by stefano on 20/08/2018.
 */
public class ZXingIntentIntegration extends IntentIntegrator {
    public ZXingIntentIntegration(Activity activity) {
        super(activity);

        setTitle("Installa Barcode Scanner?");
        setMessage("Per continuare e' necessaria l'APP Barcode Scanner disponibile sul Google Play. Vuoi installarla?");
        setButtonNo("Annulla");
        setButtonYes("Installa (consigliato)");

    }


}
