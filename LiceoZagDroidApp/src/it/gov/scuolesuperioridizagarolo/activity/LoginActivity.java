package it.gov.scuolesuperioridizagarolo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_activity_login_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.AppUserType;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;
import it.gov.scuolesuperioridizagarolo.util.ZXingIntentIntegration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 27/06/2018.
 */
public class LoginActivity extends AbstractActivity {
    LayoutObjs_activity_login_xml obj;

    public static void startLoginActivity(Context ctx) {
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateWithPermissions(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        obj = new LayoutObjs_activity_login_xml(this);

        List<String> list = new ArrayList<String>();
        list.add("-- Selezionare --");
        for (AppUserType userType : AppUserType.values()) {
            list.add(userType.getDescrizione());
        }

        ArrayAdapter<String> appUserDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        appUserDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        obj.login_spinner_tipo_profilo.setAdapter(appUserDataAdapter);

        obj.login_button_accedi.setEnabled(false);
        obj.login_spinner_tipo_profilo.setSelection(0);
        obj.login_spinner_tipo_profilo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppUserType type = getAppUserType();
                if (type == null) {
                    obj.login_button_accedi.setEnabled(false);
                    obj.login_button_accedi.setText("Accedi");
                    obj.activity_login_textViewMsg.setText("Selezionare il proprio profilo prima di continuare");
                    return;
                }

                switch (type) {
                    case ADMIN: {
                        obj.login_button_accedi.setText("Accedi con Password");
                        obj.login_button_accedi.setEnabled(true);
                        obj.activity_login_textViewMsg.setText("Per continuare premere il pulsante Accedi.\nVerra' richiesta una password di accesso.");
                        break;
                    }
                    case ATA: {
                        obj.login_button_accedi.setText("Accedi con Password");
                        obj.login_button_accedi.setEnabled(true);
                        obj.activity_login_textViewMsg.setText("Per continuare premere il pulsante Accedi.\nVerra' richiesta una password di accesso.");
                        break;
                    }
                    case DOCENTE: {

                        obj.login_button_accedi.setText("Accedi con QRCODE");
                        obj.login_button_accedi.setEnabled(true);
                        obj.activity_login_textViewMsg.setText("Per continuare premere il pulsante Accedi.\nVerra' richiesto di scansionare il codice di controllo QRCODE disponibile nell'area riservata del sito d'Istituto.");

                        break;
                    }
                    case STUDENTE: {

                        obj.login_button_accedi.setText("Accedi");
                        obj.login_button_accedi.setEnabled(true);
                        obj.activity_login_textViewMsg.setText("Per continuare premere il pulsante Accedi.");
                        break;
                    }
                    case FAMIGLIA: {
                        obj.login_button_accedi.setText("Accedi");
                        obj.login_button_accedi.setEnabled(true);
                        obj.activity_login_textViewMsg.setText("Per continuare premere il pulsante Accedi.");
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Errore tipo utente non riconosciuto");

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                obj.login_button_accedi.setText("Accedi");
                obj.login_button_accedi.setEnabled(false);
                obj.activity_login_textViewMsg.setText("");
            }
        });
        obj.login_button_esci.setOnClickListener(new OnClickListenerViewErrorCheck(getActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                LoginActivity.this.finish();
            }
        });

        obj.login_button_accedi.setOnClickListener(new OnClickListenerViewErrorCheck(getActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {

                final AppUserType type = getAppUserType();
                if (type == null) {
                    obj.login_button_accedi.setEnabled(false);
                    obj.login_button_accedi.setText("Accedi");
                    obj.activity_login_textViewMsg.setText("Selezionare il proprio profilo prima di continuare");
                    return;
                }
                SharedPreferenceWrapper.getCommonInstance(getActivity()).setUserType(null);
                switch (type) {
                    case ADMIN:
                    case ATA: {
                        DialogUtil.openPasswordDialog(getActivity(), "Password d'Accesso", "Inserire la password per il profilo specificato",
                                new DialogUtil.InputDialogResult() {
                                    @Override
                                    public void onResult(String s) {
                                        if (type.verifyPassword(s)) {
                                            SharedPreferenceWrapper.getCommonInstance(getActivity()).setUserType(type);
                                            startApplication();
                                        } else {
                                            SharedPreferenceWrapper.getCommonInstance(getActivity()).setUserType(null);
                                            DialogUtil.openInfoDialog(getActivity(), "Errore", "Password errata");
                                        }
                                    }

                                    @Override
                                    public void onCancel() {
                                        obj.activity_login_textViewMsg.setText("Inserire la password di accesso.");
                                    }
                                }
                        );
                        break;
                    }
                    case DOCENTE: {
                        final ZXingIntentIntegration ii = new ZXingIntentIntegration(LoginActivity.this);
                        ii.initiateScan();
                        break;
                    }
                    case STUDENTE: {
                        SharedPreferenceWrapper.getCommonInstance(getActivity()).setUserType(type);
                        startApplication();
                        break;
                    }
                    case FAMIGLIA: {
                        SharedPreferenceWrapper.getCommonInstance(getActivity()).setUserType(type);
                        startApplication();
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Errore tipo utente non riconosciuto");

                    }
                }

            }
        });


        final AppUserType prevUser = SharedPreferenceWrapper.getCommonInstance(getActivity()).getUserType();
        if (prevUser != null) {
            startApplication();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == IntentIntegrator.REQUEST_CODE) {
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                String ewmString = result.getContents();
                final AppUserType appUserType = getAppUserType();
                if (appUserType != null && appUserType.verifyPassword(ewmString)) {
                    SharedPreferenceWrapper.getCommonInstance(getActivity()).setUserType(appUserType);
                    startApplication();
                    //DialogUtil.openInfoDialog(getActivity(), "Info", "Codice corretto.");
                } else {
                    DialogUtil.openInfoDialog(getActivity(), "Info", "Codice errato o scaduo.\n" + ewmString + "\nIl QRCode e' disponibile nell'area riservata del sito d'Istituto");
                }
            }
        }

    }

    private void startApplication() {
        MainMenuActivity.startMainActivity(getActivity());
        LoginActivity.this.finish();
    }

    private AppUserType getAppUserType() {
        final int o = obj.login_spinner_tipo_profilo.getSelectedItemPosition();
        if (o <= 0) return null;
        return AppUserType.values()[o - 1];
    }


}