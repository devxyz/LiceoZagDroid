package it.gov.scuolesuperioridizagarolo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.db.ManagerTimetables;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_activity_login_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.AppUserType;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.services.UpdateService;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 27/06/2018.
 */
public class LoginActivity extends AbstractActivity {
    LayoutObjs_activity_login_xml obj;
    private BroadcastReceiver receiver;
    private BitOrarioGrigliaOrarioContainer containerOrari;

    private BitOrarioGrigliaOrarioContainer _loadData() {
        if (containerOrari != null && containerOrari.size() > 0) {
            return containerOrari;
        }

        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(this);
        containerOrari = new BitOrarioGrigliaOrarioContainer();
        try {
            containerOrari = db.runInTransaction(new ScuolaAppDbHelperCallable<BitOrarioGrigliaOrarioContainer>() {
                @Override
                public BitOrarioGrigliaOrarioContainer call(DaoSession session, Context ctx) throws Throwable {
                    return new ManagerTimetables(session).loadBitOrarioGrigliaOrarioContainer();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        db.close();
        return containerOrari;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        obj = new LayoutObjs_activity_login_xml(this);

        List<String> list = new ArrayList<String>();
        for (AppUserType userType : AppUserType.values()) {
            list.add(userType.getDescrizione());
        }


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


            }
        };
        registerReceiver(receiver, new IntentFilter(UpdateService.RECEIVER_DATA_UPDATE));

        ArrayAdapter<String> appUserDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        appUserDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        obj.login_spinner_tipo_profilo.setAdapter(appUserDataAdapter);

        obj.login_spinner_tipo_profilo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppUserType type = getAppUserType();

                switch (type) {
                    case ADMIN: {
                        _svuotaDocenteClasse();
                        break;
                    }
                    case ATA: {
                        _svuotaDocenteClasse();
                        break;
                    }
                    case DOCENTE: {

                        final ArrayList<String> docenti = new ArrayList<>();
                        BitOrarioGrigliaOrarioContainer containerOrari = _loadData();
                        final BitOrarioGrigliaOrario orario = containerOrari.getOrario(new OnlyDate());
                        if (orario != null) {
                            docenti.addAll(orario.getDocenti());
                        }

                        obj.login_spinner_docente_classe.setAdapter(new ArrayAdapter<String>(LoginActivity.this,
                                android.R.layout.simple_spinner_item, docenti));
                        obj.login_spinner_docente_classe.setEnabled(true);
                        obj.login_label_classe_docente.setEnabled(true);

                        break;
                    }
                    case STUDENTE: {

                        final ArrayList<String> classi = new ArrayList<>();
                        BitOrarioGrigliaOrarioContainer containerOrari = _loadData();
                        final BitOrarioGrigliaOrario orario = containerOrari.getOrario(new OnlyDate());
                        if (orario != null) {
                            classi.addAll(orario.getClassi());
                        }

                        obj.login_spinner_docente_classe.setAdapter(new ArrayAdapter<String>(LoginActivity.this,
                                android.R.layout.simple_spinner_item, classi));
                        obj.login_spinner_docente_classe.setEnabled(true);
                        obj.login_label_classe_docente.setEnabled(true);

                        break;
                    }
                    case FAMIGLIA: {
                        _svuotaDocenteClasse();
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Errore tipo utente non riconosciuto");

                    }
                }

                //controlla se abilitare la password
                if (type.getPassword("xxx") != null) {
                    obj.login_edittext_password.setEnabled(true);
                    obj.login_label_password.setEnabled(true);
                } else {
                    obj.login_edittext_password.setEnabled(false);
                    obj.login_label_password.setEnabled(false);
                }

                obj.login_button_accedi.setEnabled(true);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                _svuotaDocenteClasse();
                obj.login_button_accedi.setEnabled(false);
            }
        });

        obj.login_button_accedi.setOnClickListener(new OnClickListenerViewErrorCheck(getActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                final AppUserType user = getAppUserType();
                if (user == null) {
                    DialogUtil.openInfoDialog(getActivity(), "Errore di accesso", "Tipo utente non selezionato");
                    return;
                }

                if (user == AppUserType.DOCENTE && obj.login_spinner_docente_classe.getSelectedItemPosition() < 0) {
                    DialogUtil.openInfoDialog(getActivity(), "Errore di accesso", "Scegliere il docente corrispondete");
                    return;
                }

                if (user == AppUserType.STUDENTE && obj.login_spinner_docente_classe.getSelectedItemPosition() < 0) {
                    DialogUtil.openInfoDialog(getActivity(), "Errore di accesso", "Scegliere la classe");
                    return;
                }

                final String selectedItem_docente_or_classe = (String) obj.login_spinner_docente_classe.getSelectedItem();

                final String typedPassword = obj.login_edittext_password.getText().toString();
                final String password = user.getPassword(selectedItem_docente_or_classe);
                if (password != null && !password.equals(typedPassword)) {
                    DialogUtil.openInfoDialog(getActivity(), "Errore di accesso", "Password errata");
                    return;
                }


                SharedPreferenceWrapper.getCommonInstance(getActivity()).login(user, selectedItem_docente_or_classe);


            }
        });
    }

    private AppUserType getAppUserType() {
        final int o = obj.login_spinner_tipo_profilo.getSelectedItemPosition();
        if (o < 0) return null;
        return AppUserType.values()[o];
    }

    private void _svuotaDocenteClasse() {
        obj.login_spinner_docente_classe.setSelection(-1);
        obj.login_spinner_docente_classe.setAdapter(new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_spinner_item, new ArrayList<String>()));
        obj.login_spinner_docente_classe.setEnabled(false);
    }


}