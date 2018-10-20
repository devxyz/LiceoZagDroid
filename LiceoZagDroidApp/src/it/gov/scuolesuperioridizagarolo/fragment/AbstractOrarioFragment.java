package it.gov.scuolesuperioridizagarolo.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
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
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.impl.StringsMenuPrincipale;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractOrarioFragment<A extends AbstractOrarioListAdapter, F extends Serializable> extends AbstractFragment {


    protected LayoutObjs_fragment_orario_classe_xml LAYOUT_OBJs;   //***************************
    protected OnlyDate giornoCorrente;
    protected F filtro;
    protected A orarioAdapter;
    protected BitOrarioGrigliaOrarioContainer containerOrari;
    protected BitOrarioGrigliaOrario orario;
    //protected OrarioFragmentBundleWrapper wrapperParameter;
    protected boolean navigateFlag = true;
    protected boolean persistFlag = true;
    protected OrarioFragmentBundleWrapper wrapperParameter = new OrarioFragmentBundleWrapper();

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
        final OrarioFragmentBundleWrapper wrapperParameter = new OrarioFragmentBundleWrapper(outState);
        wrapperParameter.setFiltro(filtro);
        wrapperParameter.setData(giornoCorrente);
        wrapperParameter.setPersistFlag(persistFlag);
        wrapperParameter.setNavigateFlag(navigateFlag);
    }

    protected abstract F[] getFilterValues();

    protected abstract void persistFiltrerValue(F filtro);

    protected abstract F retrievePersistedFiltrerValue();

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle param) {

        wrapperParameter = new OrarioFragmentBundleWrapper(param);
        final OrarioFragmentBundleWrapper wrapperState = new OrarioFragmentBundleWrapper(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_orario_classe, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_orario_classe_xml(rootView);
        //Log.e("GENERALE", "SONO QUIIIIIIIIIIIIIIII");
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
                giornoCorrente = wrapperState.getData();
            }
            if (giornoCorrente == null) {
                //scelta giorno corrente
                giornoCorrente = new OnlyDate();
            }
            updateOrarioCorrente();
            navigateFlag = wrapperParameter.getNavigateFlag();
            persistFlag = wrapperParameter.getPersistFlag();


            //================================
            //================================

            filtro = null;

            if (wrapperParameter.getFiltro() != null) {
                filtro = (F) wrapperParameter.getFiltro();
            }

            if (filtro == null) {
                filtro = (F) wrapperState.getFiltro();
            }
            if (filtro == null && wrapperParameter.getPersistFlag()) {
                filtro = retrievePersistedFiltrerValue();
            }
            if (filtro == null) {
                LAYOUT_OBJs.listView.setVisibility(View.INVISIBLE);
                openDialogChooseFilter(false);
                //filtro = getDefaultFiltrerValue();
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


        LAYOUT_OBJs.buttonPrev.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                giornoCorrente = giornoCorrente.prevDay();
                updateOrarioCorrente();
                updateView();
            }
        });

        LAYOUT_OBJs.buttonNext.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                giornoCorrente = giornoCorrente.nextDay();
                updateOrarioCorrente();
                updateView();
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

    protected abstract boolean isTimetableForTeacher();

    protected abstract boolean isTimetableForStudents();

    protected abstract boolean isTimetableForRooms();

    private void onSelectRow(int position) {
        final BitOrarioOraLezione item = orarioAdapter.getItem(position).lezione;


        final String choose_orario_docente_principale = "Orario del docente " + (item == null ? "" : item.getDocentePrincipale());
        final String choose_orario_docente_compresenza = "Orario del docente in compresenza " + (item == null ? "" : item.getDocenteCompresenza());
        final String choose_orario_classe = "Orario della classe " + (item == null ? "" : item.getClasse());
        final String choose_orario_aula = "Orario aula " + (item == null || item.getAula() == null ? "" : item.getAula());
        final String choose_informazioni_aula = "Informazioni " + (item == null || item.getAula() == null ? "sulla lezione" : "sull'aula " + item.getAula());
        final String details = orarioAdapter.getDetails(position);
        ArrayList<String> funzioni = new ArrayList<>();

        if (!this.isTimetableForTeacher() && item != null && item.getDocentePrincipale() != null && navigateFlag) {
            funzioni.add(choose_orario_docente_principale);
        }
        if (!this.isTimetableForTeacher() && item != null && item.getDocenteCompresenza() != null && navigateFlag) {
            funzioni.add(choose_orario_docente_compresenza);
        }
        if (!this.isTimetableForStudents() && item != null && item.getClasse() != null && navigateFlag) {
            funzioni.add(choose_orario_classe);
        }
        if (!this.isTimetableForRooms() && item != null && item.getAula() != null && navigateFlag) {
            funzioni.add(choose_orario_aula);
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
                        if (item != null) {
                            final DataMenuInfo orarioClassi = StringsMenuPrincipale.ORARIO_CLASSI;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getClasse());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioClassi, w.getBundle());
                        }

                    } else if (fun[which].equals(choose_orario_docente_principale)) {
                        if (item != null) {
                            final DataMenuInfo orarioClassi = StringsMenuPrincipale.ORARIO_DOCENTI;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getDocentePrincipale());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioClassi, w.getBundle());
                        }
                    } else if (fun[which].equals(choose_orario_docente_compresenza)) {
                        if (item != null) {
                            final DataMenuInfo orarioClassi = StringsMenuPrincipale.ORARIO_DOCENTI;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getDocenteCompresenza());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioClassi, w.getBundle());
                        }
                    } else if (fun[which].equals(choose_orario_aula)) {
                        if (item != null) {
                            final DataMenuInfo orarioAule = StringsMenuPrincipale.ORARIO_AULE;
                            OrarioFragmentBundleWrapper w = new OrarioFragmentBundleWrapper();
                            w.setPersistFlag(false);
                            w.setData(giornoCorrente);
                            w.setFiltro(item.getAula());
                            w.setNavigateFlag(false);
                            getMainActivity().doAction(orarioAule, w.getBundle());
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

    protected abstract String filterToLabel(F filter);

    private void openDialogChooseFilter(boolean cancellable) {
        final String[] etichette = new String[getFilterValues().length];
        if (etichette.length == 0) return;

        int i = 0;
        for (F c : getFilterValues()) {

            if (c.equals(filtro))
                etichette[i] = filterToLabel(c);
            else
                etichette[i] = filterToLabel(c);
            i++;
        }


        DialogUtil.openSingleChooseDialog(getMainActivity(), "Seleziona " + getFilterDialogLabel(), cancellable, etichette, filterToLabel(filtro),
                new OnClickListenerDialogErrorCheck(getMainActivity()) {
                    @Override
                    protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                        filtro = getFilterValues()[which];
                        if (wrapperParameter.getPersistFlag())
                            persistFiltrerValue(filtro);
                        updateView();
                        LAYOUT_OBJs.listView.setVisibility(View.VISIBLE);
                    }
                }

        );
    }

    protected abstract boolean normalizeFilterName();

    protected abstract void updateAdapter(F filter);

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

        String textFiltro = "";
        String textData = "";

        //orarioAdapter.setClasse(classeCorrente);
        updateAdapter(filtro);
        orarioAdapter.setGiorno(giornoCorrente);
        if (giornoCorrente.isToday())
            textData = ("Oggi " + giornoCorrente.getGiorno().shortName());
        else
            textData = (giornoCorrente.getGiorno().shortName() + " " + giornoCorrente.toDDMM());

        if (!normalizeFilterName()) {
            textFiltro = ((getFilterAppLabel() + " " + filtro).trim());
        } else {
            textFiltro = ((getFilterAppLabel() + " " + filterToLabel(filtro)).trim());
        }
        LAYOUT_OBJs.textViewNomeData.setText((textFiltro + ": " + textData).trim());
        visualizzaOraCorrente();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class OrarioFragmentBundleWrapper extends AbstractBundleWrapper {

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

        public Serializable getFiltro() {
            return b.getSerializable(key_filtro);
        }

        public void setFiltro(Serializable f) {
            b.putSerializable(key_filtro, f);
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
