package it.gov.scuolesuperioridizagarolo.fragment.api;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezione;
import dada.bitorario.data.classes.ClassesAndRoomContainer;
import dada.bitorario.data.classes.RoomData;
import dada.bitorario.data.enum_values.EGiorno;
import dada.bitorario.data.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.api.AbstractOrarioListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelperCallable;
import it.gov.scuolesuperioridizagarolo.db.ManagerTimetables;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_orario_classe_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerDialogErrorCheck;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

public abstract class AbstractOrarioFragment<A extends AbstractOrarioListAdapter> extends AbstractFragment {


    protected static final String KEY_FILTRO = "KEY_FILTRO";
    protected static final String KEY_GIORNO = "KEY_ULTIM_GIORNO";
    protected LayoutObjs_fragment_orario_classe_xml LAYOUT_OBJs;   //***************************
    protected EGiorno giornoCorrente;
    protected String filtro;
    protected A orarioAdapter;
    protected BitOrarioGrigliaOrario orario;

    public AbstractOrarioFragment() {
    }

    protected abstract A createAbstractOrarioListAdapter();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FILTRO, filtro);
        outState.putString(KEY_GIORNO, giornoCorrente.name());
    }

    protected abstract String[] getFilterValues();

    protected abstract void saveFiltrerValue(String filtro);

    protected abstract String getSavedFiltrerValue();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_orario_classe, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_orario_classe_xml(rootView);
        //**************************
        //**************************

        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
        orario = new BitOrarioGrigliaOrario("no orario");
        try {
            orario = db.runInTransaction(new ScuolaAppDbHelperCallable<BitOrarioGrigliaOrario>() {
                @Override
                public BitOrarioGrigliaOrario call(DaoSession session, Context ctx) throws Throwable {
                    return new ManagerTimetables(session).getActiveTimetable();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        db.close();

        //**************************
        //caricamento dati
        //**************************
        {
            filtro = null;


            final SharedPreferenceWrapper p = SharedPreferenceWrapper.getCommonInstance(getMainActivity());

            if (filtro == null) {
                if (savedInstanceState != null && savedInstanceState.containsKey(KEY_FILTRO)) {
                    filtro = savedInstanceState.getString(KEY_FILTRO);
                }
            }
            if (filtro == null) {
                filtro = getSavedFiltrerValue();
            }
            if (filtro == null) {
                openDialogChooseFilter(false);
                //filtro = getDefaultFiltrerValue();
            }

            if (filtro == null) {
                filtro = "";
            }

            //================================
            giornoCorrente = null;
            if (giornoCorrente == null) {
                if (savedInstanceState != null && savedInstanceState.containsKey(KEY_GIORNO)) {
                    giornoCorrente = EGiorno.valueOf(savedInstanceState.getString(KEY_GIORNO));
                }
            }
            if (giornoCorrente == null) {
                giornoCorrente = calcolaGiornoCorrente();
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
                final BitOrarioOraLezione item = orarioAdapter.getItem(position);

                if (item == null) return;
                if (item.getNomeAula() == null) return;

                final RoomData room = ClassesAndRoomContainer.getRoom(item.getNomeAula());
                if (room != null) {
                    StringBuilder info = new StringBuilder();
                    info.append("Tipologia: ").append(room.flagSpecial() ? "Aula Attrezzata" : "Aula didattica");
                    info.append("\nLIM: ").append(room.flagLIM ? "SI" : "NO");
                    info.append("\n\n").append(room.location == null ? "-" : room.location.description);


                    DialogUtil.openInfoDialog(getMainActivity(), "Aula " + item.getNomeAula().split("_")[0], info.toString());
                }
            }
        });


        final OnClickListenerViewErrorCheck clickFiltro = new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                final boolean cancellable = true;

                openDialogChooseFilter(cancellable);
            }
        };
        LAYOUT_OBJs.textViewFiltro.setOnClickListener(clickFiltro);
        LAYOUT_OBJs.imageViewFiltro.setOnClickListener(clickFiltro);

        final OnClickListenerViewErrorCheck clickGiorno = new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                final EGiorno[] values = EGiorno.values();
                String[] giorni = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    EGiorno value = values[i];
                    if (value.isToday())
                        giorni[i] = value.getNome() + " **";
                    else
                        giorni[i] = value.getNome();
                }


                DialogUtil.openChooseDialog(getMainActivity(), "Giorno", true, giorni,
                        new OnClickListenerDialogErrorCheck(getMainActivity()) {
                            @Override
                            protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                                giornoCorrente = EGiorno.values()[which];
                                updateView();
                            }
                        },
                        new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_BACK) {
                                }
                                return true;

                            }
                        }
                );
            }
        };
        LAYOUT_OBJs.textViewGiorni.setOnClickListener(clickGiorno);
        LAYOUT_OBJs.imageViewGiorno.setOnClickListener(clickGiorno);


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
                    giornoCorrente = giornoCorrente.next();
                    updateView();
                    System.err.println("SINISTRA");
                    return true;
                }

                //verso destra
                if (e2.getX() - e1.getX() > 100) {
                    giornoCorrente = giornoCorrente.prev();
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

        updateView();

        return rootView;
    }

    private void visualizzaOraCorrente() {
        if (giornoCorrente.isToday()) {
            for (EOra v : EOra.values()) {
                if (v.isActive()) {
                    LAYOUT_OBJs.listView.smoothScrollToPosition(v.ordinal());
                }
            }
        }
    }

    @Override
    public void updateUI() {
        Toast.makeText(getMainActivity(), "Aggiornamento dati ORARIO", Toast.LENGTH_LONG).show();
        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
        try {
            final BitOrarioGrigliaOrario bitOrarioGrigliaOrario = db.runInTransaction(new ScuolaAppDbHelperCallable<BitOrarioGrigliaOrario>() {
                @Override
                public BitOrarioGrigliaOrario call(DaoSession session, Context ctx) throws Throwable {
                    ManagerTimetables m = new ManagerTimetables(session);
                    return m.getActiveTimetable();
                }
            });
            this.orario = bitOrarioGrigliaOrario;
            this.orarioAdapter.updateOrario(bitOrarioGrigliaOrario);
            this.orarioAdapter.notifyDataSetChanged();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        db.close();

        updateView();
    }


    private void openDialogChooseFilter(boolean cancellable) {
        final String[] etichette = new String[getFilterValues().length];
        if (etichette.length==0)return;

        int i = 0;
        for (String c : getFilterValues()) {

            if (c.equals(filtro))
                etichette[i] = c + " **";
            else
                etichette[i] = c;
            i++;
        }


        DialogUtil.openChooseDialog(getMainActivity(), "Seleziona " + getFilterDialogLabel(), cancellable, etichette,
                new OnClickListenerDialogErrorCheck(getMainActivity()) {
                    @Override
                    protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                        filtro = getFilterValues()[which];
                        saveFiltrerValue(filtro);
                        updateView();
                    }
                },
                new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                        }
                        return true;

                    }
                }
        );
    }

    private EGiorno calcolaGiornoCorrente() {
        //scelta giorno corrente
        if (EGiorno.getToday() == null) {
            return EGiorno.LUNEDI;
        } else {
            return EGiorno.getToday();
        }
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

        LAYOUT_OBJs.textViewGiorni.setText(giornoCorrente.getNome());

        if (!normalizeFilterName()) {
            LAYOUT_OBJs.textViewFiltro.setText((getFilterAppLabel() + " " + filtro).trim());
        } else {
            LAYOUT_OBJs.textViewFiltro.setText((getFilterAppLabel() + " " + C_TextUtil.capitalize(filtro)).trim());
        }

        visualizzaOraCorrente();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
