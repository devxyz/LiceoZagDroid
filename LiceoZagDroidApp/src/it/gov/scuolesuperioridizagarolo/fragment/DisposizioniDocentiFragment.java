package it.gov.scuolesuperioridizagarolo.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.AuleVuoteExpandibleListAdapter;
import it.gov.scuolesuperioridizagarolo.adapter.DisposizioniDocentiExpandibleListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.db.ManagerTimetables;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_classi_vuote_xml;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_disposizioni_docenti_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 08/01/2018.
 */
public class DisposizioniDocentiFragment extends AbstractFragment {
    protected static final String KEY_DATA = "KEY_DATA";
    protected BitOrarioGrigliaOrarioContainer containerOrari;
    protected LayoutObjs_fragment_disposizioni_docenti_xml LAYOUT_OBJs;   //***************************
    protected OnlyDate giornoCorrente;
    protected BitOrarioGrigliaOrario orario;
    private DisposizioniDocentiExpandibleListAdapter orarioAdapter;

    @Override
    public void showDetails(boolean show) {
        if (show) {
            LAYOUT_OBJs.linearLayout7.setVisibility(View.VISIBLE);

        } else {
            LAYOUT_OBJs.linearLayout7.setVisibility(View.GONE);
        }
    }


    private void collapseAll() {
        for (int i = 0; i < orarioAdapter.getGroupCount(); i++) {
            LAYOUT_OBJs.listView.collapseGroup(i);
        }
    }

    private void expandAll() {
        for (int i = 0; i < orarioAdapter.getGroupCount(); i++) {
            LAYOUT_OBJs.listView.expandGroup(i);
        }
    }

    @Override
    public void onSaveInstanceStateImpl(Bundle outState) {


        outState.putLong(KEY_DATA, giornoCorrente.getTime());
    }


    protected void updateOrarioCorrente() {
        orario = containerOrari.getOrario(giornoCorrente);
    }


    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle param) {


        View rootView = inflater.inflate(R.layout.fragment_disposizioni_docenti, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_disposizioni_docenti_xml(rootView);
        //**************************
        //**************************

        _loadData();

        //**************************
        //caricamento dati
        //**************************
        {
            //================================
            giornoCorrente = null;
            if (giornoCorrente == null) {
                if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DATA)) {
                    giornoCorrente = new OnlyDate(savedInstanceState.getLong(KEY_DATA));
                }
            }
            if (giornoCorrente == null) {
                //scelta giorno corrente
                giornoCorrente = new OnlyDate();
            }
            updateOrarioCorrente();
        }

        orarioAdapter = new DisposizioniDocentiExpandibleListAdapter(getMainActivity(), containerOrari, giornoCorrente);
        final ExpandableListView gridView = LAYOUT_OBJs.listView;


        gridView.setAdapter(orarioAdapter);

        final OnClickListenerViewErrorCheck clickGiorno = new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {

                final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        giornoCorrente = new OnlyDate(dayOfMonth, monthOfYear, year);
                        updateOrarioCorrente();
                        updateView();
                    }
                }, giornoCorrente.getAAAA(), giornoCorrente.getMM(), giornoCorrente.getGG());

                datePickerDialog.show();

            }
        };
        LAYOUT_OBJs.button_giorno.setOnClickListener(clickGiorno);


        LAYOUT_OBJs.listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < orarioAdapter.getGroupCount(); i++) {
                    if (i != groupPosition)
                        LAYOUT_OBJs.listView.collapseGroup(i);
                }

            }
        });


        //controlla le gesture della listview
        final GestureDetector g = new GestureDetector(getMainActivity(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //System.err.println("FL " + e1 + " -- " + e2);
                //se cambia la y
                if (Math.abs(e1.getY() - e2.getY()) > 150)
                    return true;

                //verso sinistra, torna indietro nei giorni
                if (e1.getX() - e2.getX() > 100) {
                    giornoCorrente = giornoCorrente.nextDay();
                    updateOrarioCorrente();
                    updateView();
                    System.err.println("SINISTRA");
                    return true;
                }

                //verso destra
                if (e2.getX() - e1.getX() > 100) {
                    giornoCorrente = giornoCorrente.prevDay();
                    updateOrarioCorrente();
                    updateView();
                    System.err.println("DESTRA");
                    return true;
                }

                return true;
            }
        });

        LAYOUT_OBJs.button_ora.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                collapseAll();
                final EOra[] values = EOra.values();
                int i = 0;
                for (EOra c : values) {
                    if (!c.flagOraDiLezione())
                        continue;

                    if (c.isNowHour()) {
                        LAYOUT_OBJs.listView.expandGroup(i);
                    }
                    i++;
                }
            }
        });

        LAYOUT_OBJs.listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                g.onTouchEvent(event);
                //  Toast.makeText(getMainActivity(), "ciao "+event, Toast.LENGTH_SHORT).show();
                // System.err.println(event);

                return false;
            }
        });

        updateView();


        return rootView;
    }


    private void _loadData() {
        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
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
    }

    private void visualizzaOraCorrente() {
        if (giornoCorrente.isToday()) {
            for (EOra v : EOra.values()) {
                if (v.isNowHour()) {
                    LAYOUT_OBJs.listView.smoothScrollToPosition(v.ordinal());
                }
            }
        }
    }

    @Override
    public void updateUI() {
        //Toast.makeText(getMainActivity(), "Aggiornamento dati ORARIO", Toast.LENGTH_LONG).show();
        try {
            _loadData();
            this.orarioAdapter.updateOrario(containerOrari);
            this.orarioAdapter.notifyDataSetChanged();
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


        //orarioAdapter.setClasse(classeCorrente);
        orarioAdapter.setGiorno(giornoCorrente);
        if (giornoCorrente.isToday())
            LAYOUT_OBJs.button_giorno.setText("Oggi\n" + giornoCorrente.getGiorno().name());
        else
            LAYOUT_OBJs.button_giorno.setText(giornoCorrente.getGiorno().name() + "\n" + giornoCorrente.toDDMMYY());

        visualizzaOraCorrente();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}