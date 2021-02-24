package it.gov.scuolesuperioridizagarolo.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.ConsigliDiClasseListAdapter;
import it.gov.scuolesuperioridizagarolo.adapter.DisposizioniDocentiExpandibleListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.db.BitOrrioGrigliaOrarioContainerSingleton;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_consigli_di_classe_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerDialogErrorCheck;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.util.ArrayList;

/**
 * Created by stefano on 08/01/2018.
 */
public class ConsigliDiClasseFragment extends AbstractFragment {
    protected static final String KEY_CLASSE = "KEY_CLASSE";
    protected BitOrarioGrigliaOrarioContainer containerOrari;
    protected LayoutObjs_fragment_consigli_di_classe_xml LAYOUT_OBJs;   //***************************
    protected ClassData classe;
    protected BitOrarioGrigliaOrario orario;
    private ConsigliDiClasseListAdapter listAdapter;

    @Override
    public void showDetails(boolean show) {
        /*if (show) {
            LAYOUT_OBJs.xLayout7x.setVisibility(View.VISIBLE);

        } else {
            LAYOUT_OBJs.xLayout7x.setVisibility(View.GONE);
        }*/
    }


    @Override
    public void onSaveInstanceStateImpl(Bundle outState) {
        outState.putSerializable(KEY_CLASSE, classe);
    }


    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle param) {


        View rootView = inflater.inflate(R.layout.fragment_consigli_di_classe, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_consigli_di_classe_xml(rootView);
        //**************************
        //**************************

        _loadData();

        //**************************
        //caricamento dati
        //**************************
        {
            //================================
            classe = null;
            if (classe == null) {
                if (savedInstanceState != null && savedInstanceState.containsKey(KEY_CLASSE)) {
                    classe = (ClassData) savedInstanceState.getSerializable(KEY_CLASSE);
                }
            }
            if (classe == null) {
                //scelta giorno corrente
                classe = ClassData.values()[0];
            }

        }

        //consiglio ad oggi
        listAdapter = new ConsigliDiClasseListAdapter(getMainActivity(), containerOrari.getOrario(new OnlyDate()).getConsiglioDiClasse(classe));
        final ExpandableListView gridView = LAYOUT_OBJs.listView;


        gridView.setAdapter(listAdapter);


        final OnClickListenerViewErrorCheck clickGiorno = new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                final ArrayList<String> classi = new ArrayList<>();
                for (ClassData c : ClassData.values()) {
                    classi.add(c.className);
                }
                String[] values = classi.toArray(new String[classi.size()]);
                DialogUtil.openSingleChooseDialog(getMainActivity(), "Seleziona la classe", true, values, classe.className,
                        new OnClickListenerDialogErrorCheck(getMainActivity()) {
                            @Override
                            protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                                classe = ClassData.search(classi.get(which));
                                updateView();
                                LAYOUT_OBJs.listView.setVisibility(View.VISIBLE);
                            }
                        }

                );
            }
        };


        LAYOUT_OBJs.button_classe.setOnClickListener(clickGiorno);
        updateView();
        return rootView;
    }


    private void _loadData() {
        containerOrari = BitOrrioGrigliaOrarioContainerSingleton.getInstance(getMainActivity());
    }


    @Override
    public void updateUI() {
        //Toast.makeText(getMainActivity(), "Aggiornamento dati ORARIO", Toast.LENGTH_LONG).show();
        try {
            _loadData();
            this.listAdapter.update(orario.getConsiglioDiClasse(classe));
            this.listAdapter.notifyDataSetChanged();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        updateView();
    }


    private void updateView() {
        /*if (giornoCorrente == EGiorno.getToday()) {
            LAYOUT_OBJs.textViewGiorni.setBackgroundColor(getResources().getColor(R.color.color_red));
            LAYOUT_OBJs.textViewClasse.setBackgroundColor(getResources().getColor(R.color.color_red));
        } else {
            LAYOUT_OBJs.textViewGiorni.setBackgroundColor(getResources().getColor(R.color.color_blue));
            LAYOUT_OBJs.textViewClasse.setBackgroundColor(getResources().getColor(R.color.color_blue));
        }*/
        this.listAdapter.update(orario.getConsiglioDiClasse(classe));
        this.listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
