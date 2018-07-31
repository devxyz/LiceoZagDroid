package it.gov.scuolesuperioridizagarolo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_activity_splash_update2_xml;
import it.gov.scuolesuperioridizagarolo.model.AppUserType;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

/**
 * Created by stefano on 10/09/15.
 */

public class InitActivity extends AbstractActivity {
    private LayoutObjs_activity_splash_update2_xml obj;
    private boolean closed = false;

    public static AppUserType getCurrentUser(Context e) {
        return SharedPreferenceWrapper.getCommonInstance(e).getUserType();
    }

    public static void chooseUserType(final AbstractActivity e, final DialogInterface.OnClickListener onClickListener, boolean cancelable) {

        //DOCENTE("Docente"), STUDENTE("Studente"), FAMIGLIA("Genitore"), ALTRO("Visitatore");

        final AppUserType userType = SharedPreferenceWrapper.getCommonInstance(e).getUserType();
        final CharSequence[] values = {"Docente", "Studente", "Famiglia", "Personale ATA", "Dirigente"};
        CharSequence selectedValue = null;
        if (userType != null) {
            selectedValue = values[userType.ordinal()];
        }


        DialogUtil.openSingleChooseDialog(e, "Scegli il profilo piu' adatto a te.", cancelable, values, selectedValue,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        AppUserType tipo;
                        switch (which) {
                            case 0:
                                tipo = (AppUserType.DOCENTE);
                                break;
                            case 1:
                                tipo = (AppUserType.STUDENTE);
                                break;
                            case 2:
                                tipo = (AppUserType.FAMIGLIA);
                                break;
                            case 3:
                                tipo = (AppUserType.ATA);
                                break;
                            case 4:
                                tipo = (AppUserType.ADMIN);
                                break;
                        }

                        onClickListener.onClick(dialog, which);
                    }
                });


    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_update2);
        obj = new LayoutObjs_activity_splash_update2_xml(this);

        obj.txtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainMenuActivity();
            }
        });

        final SharedPreferenceWrapper e = SharedPreferenceWrapper.getCommonInstance(getApplication());
        obj.textView10.setText("Ultimo aggiornamento: " + C_DateUtil.toDDMMYYY_HHMMSS(e.getLastDataUpdate()));
        obj.textView10.setVisibility(View.INVISIBLE);

        final AppUserType userType = getSharedPreferences().getUserType();
        if (userType == null) {
            chooseUserType(this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (savedInstanceState == null) {
                        startMainMenuActivity();
                    }
                }
            }, false);
        } else {
            startMainMenuActivity();
        }
    }


    private void startMainMenuActivity() {
        //todo debug MainMenuActivity.startMainActivity(InitActivity.this);

        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);

        closed = true;
        finish();
    }
}