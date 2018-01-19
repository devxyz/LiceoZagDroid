package it.gov.scuolesuperioridizagarolo.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.AbstractOrarioListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractBundleWrapper;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.db.ManagerTimetables;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_orario_classe_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerDialogErrorCheck;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.impl.StringsMenuPrincipale;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.util.ArrayList;

public abstract class AbstractOrarioFragment<A extends AbstractOrarioListAdapter> extends AbstractFragment {


    protected static final String KEY_FILTRO = "KEY_FILTRO";
    protected static final String KEY_DATA = "KEY_DATA";
    protected LayoutObjs_fragment_orario_classe_xml LAYOUT_OBJs;   //***************************
    protected OnlyDate giornoCorrente;
    protected String filtro;
    protected A orarioAdapter;
    protected BitOrarioGrigliaOrarioContainer containerOrari;
    protected BitOrarioGrigliaOrario orario;
    protected OrarioFragmentBundleWrapper wrapperParameter;

    public AbstractOrarioFragment() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orario corrente: " + orario.getTitolo()).append("\n");
        sb.append("Data corrente: " + giornoCorrente).append("\n");


        final BitOrarioGrigliaOrarioContainer.BitOrarioGrigliaOrarioItem details = containerOrari.getOrarioDetails(giornoCorrente);
        sb.append("ID: " + details.remoteId).append("\n");
        sb.append("Inizio: " + details.startDate).append("\n");
        sb.append("Fine: " + details.endDate).append("\n");
        sb.append("Collezione: " + containerOrari.toString()).append("\n");
        return sb.toString();
    }

    protected void updateOrarioCorrente() {
        orario = containerOrari.getOrario(giornoCorrente);
    }

    protected abstract A createAbstractOrarioListAdapter();

    @Override
    public void onSaveInstanceStateImpl(Bundle outState) {

        outState.putString(KEY_FILTRO, filtro);
        outState.putLong(KEY_DATA, giornoCorrente.getTime());
    }

    protected abstract String[] getFilterValues();

    protected abstract void saveFiltrerValue(String filtro);

    protected abstract String getSavedFiltrerValue();

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle param) {

        wrapperParameter = new OrarioFragmentBundleWrapper(param);

        View rootView = inflater.inflate(R.layout.fragment_orario_classe, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_orario_classe_xml(rootView);
        //**************************
        //**************************

        _loadData();

        //**************************
        //caricamento dati
        //**************************
        {


            //================================
            giornoCorrente = null;

            if (wrapperParameter.getData() != null) {
                giornoCorrente = wrapperParameter.getData();
            }


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


            //================================
            //================================

            filtro = null;

            if (wrapperParameter.getFiltro() != null) {
                filtro = wrapperParameter.getFiltro();
            }

            if (filtro == null) {
                if (savedInstanceState != null && savedInstanceState.containsKey(KEY_FILTRO)) {
                    filtro = savedInstanceState.getString(KEY_FILTRO);
                }
            }
            if (filtro == null && wrapperParameter.getPersistFlag()) {
                filtro = getSavedFiltrerValue();
            }
            if (filtro == null) {
                openDialogChooseFilter(false);
                //filtro = getDefaultFiltrerValue();
            }

            if (filtro == null) {
                filtro = "";
            }
        }
        //**************************
        //**************************


        final ListView gridView = LAYOUT_OBJs.listView;


        orarioAdapter = createAbstractOrarioListAdapter(); //new OrarioClasseListAdapter(this.getMainActivity(), classeCorrente, orario, giornoCorrente);
        gridView.setAdapter(orarioAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                onSelectRow(position);
            }
        });


        final OnClickListenerViewErrorCheck clickFiltro = new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                final boolean cancellable = true;

                openDialogChooseFilter(cancellable);
            }
        };
        LAYOUT_OBJs.button_filtro.setOnClickListener(clickFiltro);

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
        /*
        LAYOUT_OBJs.textViewGiorni.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                giornoCorrente = new OnlyDate();
                updateOrarioCorrente();
                updateView();
            }
        });*/
        LAYOUT_OBJs.button_giorno.setOnClickListener(clickGiorno);


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
                if (!wrapperParameter.getNavigateFlag())
                    return true;

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

        LAYOUT_OBJs.listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                g.onTouchEvent(event);
                //  Toast.makeText(getMainActivity(), "ciao "+event, Toast.LENGTH_SHORT).show();
                // System.err.println(event);

                return false;
            }
        });

        if (!wrapperParameter.getNavigateFlag()) {
            LAYOUT_OBJs.button_filtro.setEnabled(false);
            LAYOUT_OBJs.button_giorno.setEnabled(false);
        }

        updateView();

        return rootView;
    }

    private void onSelectRow(int position) {
        final BitOrarioOraLezione item = orarioAdapter.getItem(position);


        final String choose_orario_docente_principale = "Orario del docente " + (item == null ? "" : item.getDocentePrincipale());
        final String choose_orario_docente_compresenza = "Orario del docente in compresenza " + (item == null ? "" : item.getDocenteCompresenza());
        final String choose_orario_classe = "Orario della classe " + (item == null ? "" : item.getClasse());
        final String choose_informazioni_aula = "Informazioni " + (item == null || item.getNomeAula() == null ? "sulla lezione" : "sull'aula " + item.getNomeAula());
        final String details = orarioAdapter.getDetails(position);
        ArrayList<String> funzioni = new ArrayList<>();

        if (!this.getClass().equals(OrarioDocentePersistenteFragment.class) && item != null && item.getDocentePrincipale() != null && wrapperParameter.getNavigateFlag()) {
            funzioni.add(choose_orario_docente_principale);
        }
        if (!this.getClass().equals(OrarioDocentePersistenteFragment.class) && item != null && item.getDocenteCompresenza() != null && wrapperParameter.getNavigateFlag()) {
            funzioni.add(choose_orario_docente_compresenza);
        }
        if (!this.getClass().equals(OrarioClassePersistenteFragment.class) && item != null && item.getClasse() != null && wrapperParameter.getNavigateFlag()) {
            funzioni.add(choose_orario_classe);
        }
        if (details != null) {
            funzioni.add(choose_informazioni_aula);
        }

        final String[] fun = funzioni.toArray(new String[funzioni.size()]);

        if (funzioni.size() > 0) {

            DialogUtil.openChooseDialog(getMainActivity(), "Dettagli", true, fun, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (fun[which].equals(choose_informazioni_aula)) {
                        DialogUtil.openInfoDialog(getMainActivity(), "Informazioni", details);

                    } else if (fun[which].equals(choose_orario_classe)) {
                        if (item != null ) {
                            final DataMenuInfo orarioClassi = StringsMenuPrincipale.ORARIO_CLASSI;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getClasse());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioClassi, w.getBundle());
                        }

                    } else if (fun[which].equals(choose_orario_docente_principale)) {
                        if (item != null ) {
                            final DataMenuInfo orarioClassi = StringsMenuPrincipale.ORARIO_DOCENTI;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getDocentePrincipale());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioClassi, w.getBundle());
                        }
                    } else if (fun[which].equals(choose_orario_docente_compresenza)) {
                        if (item != null ) {
                            final DataMenuInfo orarioClassi = StringsMenuPrincipale.ORARIO_DOCENTI;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getDocenteCompresenza());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioClassi, w.getBundle());
                        }
                    } else {
                        dialog.dismiss();
                    }
                }
            });
        }


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

    private void openDialogChooseFilter(boolean cancellable) {
        final String[] etichette = new String[getFilterValues().length];
        if (etichette.length == 0) return;

        int i = 0;
        for (String c : getFilterValues()) {

            if (c.equals(filtro))
                etichette[i] = c;
            else
                etichette[i] = c;
            i++;
        }


        DialogUtil.openSingleChooseDialog(getMainActivity(), "Seleziona " + getFilterDialogLabel(), cancellable, etichette, filtro,
                new OnClickListenerDialogErrorCheck(getMainActivity()) {
                    @Override
                    protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                        filtro = getFilterValues()[which];
                        if (wrapperParameter.getPersistFlag())
                            saveFiltrerValue(filtro);
                        updateView();
                    }
                }

        );
    }

    protected abstract boolean normalizeFilterName();

    protected abstract void updateAdapter(String filter);

    protected abstract String getFilterDialogLabel();

    protected abstract String getFilterAppLabel();

    private void updateView() {
        /*if (giornoCorrente == EGiorno.getToday()) {
            LAYOUT_OBJs.textViewGiorni.setBackgroundColor(getResources().getColor(R.color.color_red));
            LAYOUT_OBJs.textViewClasse.setBackgroundColor(getResources().getColor(R.color.color_red));
        } else {
            LAYOUT_OBJs.textViewGiorni.setBackgroundColor(getResources().getColor(R.color.color_blue));
            LAYOUT_OBJs.textViewClasse.setBackgroundColor(getResources().getColor(R.color.color_blue));
        }*/


        //orarioAdapter.setClasse(classeCorrente);
        updateAdapter(filtro);
        orarioAdapter.setGiorno(giornoCorrente);
        if (giornoCorrente.isToday())
            LAYOUT_OBJs.button_giorno.setText("Oggi\n" + giornoCorrente.getGiorno().shortName());
        else
            LAYOUT_OBJs.button_giorno.setText(giornoCorrente.getGiorno().shortName() + "\n" + giornoCorrente.toDDMM());

        if (!normalizeFilterName()) {
            LAYOUT_OBJs.button_filtro.setText((getFilterAppLabel() + " " + filtro).trim());
        } else {
            LAYOUT_OBJs.button_filtro.setText((getFilterAppLabel() + " " + C_TextUtil.capitalize(filtro)).trim());
        }

        visualizzaOraCorrente();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static class OrarioFragmentBundleWrapper extends AbstractBundleWrapper {

        private final static String key_filtro = "KEY_FILTRO";
        private final static String key_save_filter = "KEY_SAVE_FILTER";
        private final static String key_data = "KEY_DATA";
        private final static String key_navigate = "KEY_NAVIGATE";

        public OrarioFragmentBundleWrapper(Bundle b) {
            super(b);
        }

        public OrarioFragmentBundleWrapper() {
            this(null);
        }

        public String getFiltro() {
            return b.getString(key_filtro);
        }

        public void setFiltro(String f) {
            b.putString(key_filtro, f);
        }

        //true se si vuole utilizzare la persistenza dei dati
        public boolean getPersistFlag() {
            return b.getBoolean(key_save_filter, true);
        }

        public void setPersistFlag(boolean f) {
            b.putBoolean(key_save_filter, f);
        }

        //true se si vuole navigare nell'orario (cambiare pulsanti ecc)
        public boolean getNavigateFlag() {
            return b.getBoolean(key_navigate, true);
        }

        public void setNavigateFlag(boolean f) {
            b.putBoolean(key_navigate, f);
        }

        public OnlyDate getData() {
            return (OnlyDate) b.getSerializable(key_data);
        }

        public void setData(OnlyDate f) {
            b.putSerializable(key_data, f);
        }
    }
}
