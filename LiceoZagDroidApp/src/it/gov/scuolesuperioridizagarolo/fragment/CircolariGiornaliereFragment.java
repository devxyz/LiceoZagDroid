package it.gov.scuolesuperioridizagarolo.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.CircolariListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.cache.UrlFileCache;
import it.gov.scuolesuperioridizagarolo.dao.CircolareDB;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDBHelperRun;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.db.ManagerCircolare;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_cerca_circolari_by_date_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;
import it.gov.scuolesuperioridizagarolo.model.CircolariContainerByDate;
import it.gov.scuolesuperioridizagarolo.util.DebugUtil;

import java.util.*;
@Deprecated
public class CircolariGiornaliereFragment extends AbstractFragment {
    private Date currentDate;
    private LayoutObjs_fragment_cerca_circolari_by_date_xml LAYOUT_OBJs;   //***************************
    private CircolariContainerByDate circolari;
    private UrlFileCache cache = null;
    private CircolariListAdapter a;

    public CircolariGiornaliereFragment() {
    }

    @Override
    public void updateUI() {
        Toast.makeText(getMainActivity(), "Aggiornamento dati avvenuto con successo (circolari)", Toast.LENGTH_SHORT).show();
        __aggiornaMappaCircolariDalDB();
    }

    @Override
    protected Integer getHelpScreen() {
        return R.drawable.help_circolari_giornaliere;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (cache != null) {
            cache.cancelAll();
            cache = null;
        }
    }

    private void __giornoSuccessivo() {
        final Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DAY_OF_YEAR, 1);
        currentDate = c.getTime();

        __aggiornaVistaCircolariDelGiorno();
    }

    private void __giornoPrecedente() {
        final Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DAY_OF_YEAR, -1);
        currentDate = c.getTime();

        __aggiornaVistaCircolariDelGiorno();
    }


    private void __assegnaData(Date d) {
        currentDate = d;

        __aggiornaVistaCircolariDelGiorno();
    }

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {
        currentDate = new Date();

        View rootView = inflater.inflate(R.layout.fragment_cerca_circolari_by_date, container, false);
        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_cerca_circolari_by_date_xml(rootView);
        //**************************


        cache = (getMainActivity()).getCache();
        //LAYOUT_OBJs.textViewAvviso.setText("Controllo aggiornamenti");
        a = new CircolariListAdapter(getMainActivity(), new ArrayList<CircolareDB>());
        LAYOUT_OBJs.listView.setAdapter(a);
        LAYOUT_OBJs.listView.setEmptyView(LAYOUT_OBJs.textViewListaVuota);

        LAYOUT_OBJs.imageButton3.setOnClickListener(new __ClickButtonCalendario());
        LAYOUT_OBJs.date.setOnClickListener(new __ClickButtonCalendarioOggi());

        LAYOUT_OBJs.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(getMainActivity(), "Click checkbox: " + isChecked + ", state: " + LAYOUT_OBJs.checkBox.isChecked(), Toast.LENGTH_SHORT).show();
                __aggiornaVistaCircolariDelGiorno();

            }
        });

        //LAYOUT_OBJs.listView.setOnItemClickListener(new CircolariSearchFragment.__ClickApriPdfCircolare(cache, a, this));
        LAYOUT_OBJs.listView.setOnItemClickListener(new CircolariSearchFragment.__ClickApriDialogCircolare(a, this));
        LAYOUT_OBJs.listView.setLongClickable(true);
        LAYOUT_OBJs.listView.setOnItemLongClickListener(new CircolariSearchFragment.MyOnItemLongClickListener(a, this));

        LAYOUT_OBJs.imageButton_left.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                __giornoPrecedente();
            }
        });

        LAYOUT_OBJs.imageButton_right.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                __giornoSuccessivo();
            }
        });


        //visualizza le circolari iniziali, prima del download
        __aggiornaMappaCircolariDalDB();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    /**
     * aggiorna la mappa delle circolari
     *
     * @throws Throwable
     */
    private void __aggiornaMappaCircolariDalDB() {


        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
        try {
            db.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    circolari = new ManagerCircolare(session).circolariByDate();


                    if (DebugUtil.DEBUG__CircolariGiornaliereFragment) {
                        Log.d("CERCA_CIRCOLARE_FRAG", "LIST CIRCOLARI ");
                        for (Map.Entry<C_MyDate, Set<CircolareDB>> e : circolari.getCircolariByDataApplicazione().entrySet()) {
                            for (CircolareDB c : e.getValue()) {
                                Log.d("CERCA_CIRCOLARE_FRAG", e.getKey() + " " + c.getNumero() + " " + c.getTitolo());
                            }
                        }
                    }

                    __aggiornaVistaCircolariDelGiorno();
                }
            });

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * aggiorna la mappa delle circolari
     *
     * @throws Throwable
     */
    private void __aggiornaMappaCircolariDalDB(DaoSession session) {
        circolari = new ManagerCircolare(session).circolariByDate();
        if (DebugUtil.DEBUG__CircolariGiornaliereFragment) {
            Log.d("CERCA_CIRCOLARE_FRAG", "LIST CIRCOLARI ");
            for (Map.Entry<C_MyDate, Set<CircolareDB>> e : circolari.getCircolariByDataApplicazione().entrySet()) {
                for (CircolareDB c : e.getValue()) {
                    Log.d("CERCA_CIRCOLARE_FRAG", e.getKey() + " " + c.getNumero() + " " + c.getTitolo());
                }
            }
        }
        __aggiornaVistaCircolariDelGiorno();
    }

    private void __aggiornaVistaCircolariDelGiorno() {

        getMainActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final C_MyDate myDate = new C_MyDate(currentDate);
                LAYOUT_OBJs.date.setText(myDate.giornoSettimana() + " " + myDate.toDDMMYYYY());
                a.update(getCircolariDataCorrente());
            }
        });

    }

    private List<CircolareDB> getCircolariDataCorrente() {
        final TreeSet<CircolareDB> ris = new TreeSet<>(ManagerCircolare.getCircolareDBComparator());
        final Set<CircolareDB> circolareDtos = circolari.getCircolariByDataApplicazione().get(new C_MyDate(currentDate));


        if (circolareDtos == null) {
            if (DebugUtil.DEBUG__CircolariGiornaliereFragment) {
                Log.d("CIRCOLARI GIORNALIERE ", "NO CIRCOLARI PER LA DATA " + new C_MyDate(currentDate) + ". Date totali: " + circolari.getCircolariByDataApplicazione().keySet().size());
            }

        } else {
            if (DebugUtil.DEBUG__CircolariGiornaliereFragment) {
                Log.d("CIRCOLARI GIORNALIERE ", "TROVATE " + circolareDtos.size() + " CIRCOLARI PER LA DATA " + new C_MyDate(currentDate) + ". Date totali: " + circolari.getCircolariByDataApplicazione().keySet().size());
            }
            ris.addAll(circolareDtos);
        }

        if (LAYOUT_OBJs.checkBox.isChecked()) {
            if (DebugUtil.DEBUG__CircolariGiornaliereFragment) {
                Log.d("CIRCOLARI GIORNALIERE ", "AGGIUNGI PUBBLICATE");
            }
            final Set<CircolareDB> circolareInfoWebs1 = circolari.getCircolariByDataPubblicazione().get(new C_MyDate(currentDate));
            if (circolareInfoWebs1 != null)
                ris.addAll(circolareInfoWebs1);
        }
        ArrayList<CircolareDB> ultimeCircolari = new ArrayList<>(ris);
        ManagerCircolare.sortLastToFirst(ultimeCircolari);

        return ultimeCircolari;
    }


    /**
     * click bottone
     */

    private class __ClickButtonCalendario extends OnClickListenerViewErrorCheck {
        public __ClickButtonCalendario() {
            super(CircolariGiornaliereFragment.this.getMainActivity());
        }

        @Override
        protected void onClickImpl(View v) throws Throwable {
            C_MyDate dd = new C_MyDate(currentDate);

            DatePickerDialog d = new DatePickerDialog(getMainActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    currentDate = new C_MyDate(dayOfMonth, monthOfYear + 1, year).getDate();
                    __aggiornaVistaCircolariDelGiorno();
                }
            }, dd.year(), dd.month() - 1, dd.day());
            d.setTitle("Data");
            d.show();
        }
    }


    private class __ClickButtonCalendarioOggi extends OnClickListenerViewErrorCheck {
        public __ClickButtonCalendarioOggi() {
            super(CircolariGiornaliereFragment.this.getMainActivity());
        }

        @Override
        protected void onClickImpl(View v) throws Throwable {
            currentDate = new Date();//oggi
            __aggiornaVistaCircolariDelGiorno();
        }
    }


}

