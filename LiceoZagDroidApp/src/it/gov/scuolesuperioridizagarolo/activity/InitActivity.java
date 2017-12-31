package it.gov.scuolesuperioridizagarolo.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
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

    public static void chooseUserType(final AbstractActivity e, final DialogInterface.OnClickListener onClickListener) {


        DialogUtil.openChooseDialog(e, "Scegli il profilo piu' adatto a te.", false, new CharSequence[]
                        {"Sono uno studente", "Sono un docente", "Sono un genitore", "Visitatore"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                e.getSharedPreferences().setUserType(AppUserType.STUDENTE);
                                break;
                            case 1:
                                e.getSharedPreferences().setUserType(AppUserType.DOCENTE);
                                break;
                            case 2:
                                e.getSharedPreferences().setUserType(AppUserType.FAMIGLIA);
                                break;
                            case 3:
                                e.getSharedPreferences().setUserType(AppUserType.ALTRO);
                                break;
                        }

                        onClickListener.onClick(dialog, which);
                    }
                }, new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                        }
                        return true;
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
            });
        } else {
            startMainMenuActivity();

            /*
            //attende 15 secondi
            final int SECONDS = 10;
            obj.progressBar2.setIndeterminate(false);
            obj.progressBar2.setMax(SECONDS);


            Thread t = new Thread() {
                @Override
                public void run() {

                    for (int i = 0; i <= SECONDS && !closed; i = i + 1) {
                        final int finalI = i;
                        ThreadUtil.runOnUiThreadAndWait(SplashUpdateActivity.this, new Runnable() {
                            @Override
                            public void run() {
                                obj.txtInfo.setText("Continua (" + (SECONDS - finalI) + ") >>");
                                obj.progressBar2.setProgress(finalI);
                            }
                        });
                        ThreadUtil.sleep(1000);
                    }
                    if (closed) return;
                    startMainMenuActivity();
                }
            };


            t.start();*/
        }
    }


    private void startMainMenuActivity() {
        MainMenuActivity.startMainActivity(InitActivity.this);
        closed = true;
        finish();
    }
}