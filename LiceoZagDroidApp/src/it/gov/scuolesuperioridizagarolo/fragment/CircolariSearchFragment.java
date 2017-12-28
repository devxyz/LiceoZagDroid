package it.gov.scuolesuperioridizagarolo.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.adapter.CircolariListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.cache.UrlFileCache;
import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.db.ManagerCircolare;
import it.gov.scuolesuperioridizagarolo.dialog.CircolariDetailsDialog;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_cerca_circolari_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.util.DebugUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.util.*;
@Deprecated
public class CircolariSearchFragment extends AbstractFragment {


    private static final int EXPIRE_TIME_DAYS = 15;
    private LayoutObjs_fragment_cerca_circolari_xml LAYOUT_OBJs;   //***************************
    private UrlFileCache cache = null;
    private CircolariListAdapter a;
    private ArrayAdapter<String> multiTextViewAdapter;
    private List<String> classi = new ArrayList<>();

    public CircolariSearchFragment() {
    }

    public static void updateCircolare_impostaFlagLettura(MainMenuActivity m, final CircolariListAdapter a, final boolean flagLettura) {
        //aggiorna il database
        ScuolaAppDbHelper db = new ScuolaAppDbHelper(m);
        try {
            db.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    final List<CircolareDB> circolari = a.getCircolari();
                    final CircolareDBDao circolareDBDao = session.getCircolareDBDao();
                    for (CircolareDB c : circolari) {
                        c.setFlagContenutoLetto(flagLettura);
                        circolareDBDao.update(c);
                    }
                }
            });
        } catch (Throwable throwable) {

        } finally {
            db.close();
        }
        a.notifyDataSetChanged();
    }

    public static void updateCircolare_impostaFlagLettura(MainMenuActivity m, CircolariListAdapter a, final CircolareDB c, boolean flagLettura) {
        //controlla se segnare la lettura
        if (c.getFlagContenutoLetto() != flagLettura) {
            c.setFlagContenutoLetto(flagLettura);

            //aggiorna il database
            ScuolaAppDbHelper db = new ScuolaAppDbHelper(m);
            try {
                db.runInTransaction(new ScuolaAppDBHelperRun() {
                    @Override
                    public void run(DaoSession session, Context ctx) throws Throwable {
                        final CircolareDBDao circolareDBDao = session.getCircolareDBDao();
                        circolareDBDao.update(c);

                    }
                });
            } catch (Throwable throwable) {

            } finally {
                db.close();
            }
            a.notifyDataSetChanged();
        }
    }

    public static TreeSet<String> listaParole_Circolare(MainMenuActivity m, CircolariListAdapter a, final CircolareDB c) {
        final TreeSet<String> ris = new TreeSet<>();

        //aggiorna il database
        ScuolaAppDbHelper db = new ScuolaAppDbHelper(m);
        try {
            db.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {

                    final CircolareContieneTermineDBDao x = session.getCircolareContieneTermineDBDao();
                    final List<CircolareContieneTermineDB> list = x.queryBuilder().where(CircolareContieneTermineDBDao.Properties._id_circolare.eq(c.getId())).list();
                    for (CircolareContieneTermineDB xy : list) {
                        ris.add(xy.getTermineDB().getTermine());
                    }

                }
            });
        } catch (Throwable throwable) {

        } finally {
            db.close();
        }
        return ris;
    }

    @Override
    public void updateUI() {
        Toast.makeText(getMainActivity(), "Aggiornamento dati avvenuto con successo (circolari)", Toast.LENGTH_SHORT).show();
        aggiornaViewCircolariAndTerminiDalDB();

    }

    @Override
    protected Integer getHelpScreen() {
        return R.drawable.help_cerca_circolari;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (cache != null) {
            cache.cancelAll();
            cache = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cerca_circolari, container, false);
        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_cerca_circolari_xml(rootView);
        //**************************


        cache = (getMainActivity()).getCache();
        LAYOUT_OBJs.searchTextView.addTextChangedListener(new ListenerModificaTestoMultiText());
        LAYOUT_OBJs.imageButtonSearch.setOnClickListener(new __ClickButtonCerca());
        a = new CircolariListAdapter(getMainActivity(), new ArrayList<CircolareDB>());
        LAYOUT_OBJs.listView.setAdapter(a);
        LAYOUT_OBJs.listView.setEmptyView(LAYOUT_OBJs.textViewListaVuota);

        LAYOUT_OBJs.listView.setLongClickable(true);
        LAYOUT_OBJs.listView.setOnItemClickListener(new __ClickApriDialogCircolare(a, this));
        LAYOUT_OBJs.listView.setOnItemLongClickListener(new MyOnItemLongClickListener(a, this));

        LAYOUT_OBJs.imageButtonPlus.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                String[] valori = classi.toArray(new String[classi.size()]);


                DialogUtil.openChooseDialog(activity, "Scegli la classe", false, valori, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }, null);
            }
        });

        multiTextViewAdapter = new ArrayAdapter<String>(
                getMainActivity(),
                android.R.layout.simple_dropdown_item_1line);

        LAYOUT_OBJs.searchTextView.setAdapter(multiTextViewAdapter);
        LAYOUT_OBJs.searchTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        //visualizza le circolari iniziali, prima del download (legge dal DB)
        aggiornaViewCircolariAndTerminiDalDB();

        return rootView;
    }


    /**
     * aggiorna la lista degli elementi della autocomplete list
     */
    private void aggiornaViewCircolariAndTerminiDalDB() {
        try {
            ScuolaAppDbHelper.runOneTransactionSync(getMainActivity(), new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    try {
                        if (DebugUtil.DEBUG__CircolariSearchFragment) {
                            Log.d("CERCA_CIRCOLARE_FRAG", "START UPDATE aggiornaViewCircolariAndTerminiDalDB");
                        }
                        final ManagerCircolare managerCircolare = new ManagerCircolare(session);

                        //update termin
                        final Set<String> termini = managerCircolare.listAllTermini();
                        multiTextViewAdapter.clear();
                        multiTextViewAdapter.addAll(termini);
                        classi.clear();
                        for (String t : termini) {
                            if (t.contains("(#CLASSE)")) {
                                classi.add(t);
                            }
                        }

                        multiTextViewAdapter.notifyDataSetChanged();
                        if (DebugUtil.DEBUG__CircolariSearchFragment)
                            Log.d("CERCA_CIRCOLARE_FRAG", "UPDATE LISTA TERMINI " + termini.size());

                        //update circolari
                        final List<CircolareDB> cc = managerCircolare.listAllCircolari();
                        ArrayList<CircolareDB> ultimeCircolari = new ArrayList<>(cc);


                        ManagerCircolare.sortLastToFirst(ultimeCircolari);
                        a.update(ultimeCircolari);


                        if (DebugUtil.DEBUG__CircolariSearchFragment) {
                            Log.d("CERCA_CIRCOLARE_FRAG", "END UPDATE aggiornaViewCircolariAndTerminiDalDB");
                        }
                    } catch (Throwable ex) {
                    }

                }
            });
        } catch (Throwable ex) {
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public static class __ClickApriDialogCircolare implements AdapterView.OnItemClickListener {
        private final CircolariListAdapter a;
        private AbstractFragment fragment;
        private MainMenuActivity activity;

        public __ClickApriDialogCircolare(CircolariListAdapter a, AbstractFragment fragment) {
            this.a = a;
            this.fragment = fragment;
            this.activity = fragment.getMainActivity();
        }

        private MainMenuActivity getMainActivity() {
            return activity;
        }

        private void updateLetturaCircolare(final CircolareDB c) {
            //controlla se segnare la lettura
            if (!c.getFlagContenutoLetto() && c.getTesto() != null) {
                c.setFlagContenutoLetto(true);

                //aggiorna il database
                ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
                try {
                    db.runInTransaction(new ScuolaAppDBHelperRun() {
                        @Override
                        public void run(DaoSession session, Context ctx) throws Throwable {
                            final CircolareDBDao circolareDBDao = session.getCircolareDBDao();
                            circolareDBDao.update(c);
                        }
                    });
                } catch (Throwable throwable) {

                } finally {
                    db.close();
                }
                a.notifyDataSetChanged();

            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


            final CircolareDB c = a.getCircolari().get(position);
            CircolariDetailsDialog d = new CircolariDetailsDialog(fragment, c);
            updateLetturaCircolare(c);
            d.show();

        }
    }

    public static class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        private final CircolariListAdapter a;
        private final AbstractFragment fragment;

        public MyOnItemLongClickListener(CircolariListAdapter a, AbstractFragment fragment) {
            this.a = a;
            this.fragment = fragment;
        }


        private MainMenuActivity getMainActivity() {
            return fragment.getMainActivity();
        }

        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {

            final CircolareDB c = a.getCircolari().get(position);

            DialogUtil.openChooseDialog(fragment.getActivity(), "Quale azione vuoi svolgere?", true, new CharSequence[]{
                            "Segna tutte da leggere",
                            "Segna tutte lette",
                            "Segna circolare corrente da leggere",
                            "Segna circolare corrente letta",
                            "Informazioni"
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {
                                case 0:
                                    updateCircolare_impostaFlagLettura(getMainActivity(), a, false);
                                    break;
                                case 1:
                                    updateCircolare_impostaFlagLettura(getMainActivity(), a, true);
                                    break;
                                case 2:
                                    updateCircolare_impostaFlagLettura(getMainActivity(), a, c, false);
                                    break;
                                case 3:
                                    updateCircolare_impostaFlagLettura(getMainActivity(), a, c, true);
                                    break;
                                case 4:
                                    final TreeSet<String> strings = listaParole_Circolare(getMainActivity(), a, c);

                                    DialogUtil.openAlertDialog(getMainActivity(), "Info", strings.toString());

                                    break;
                                default:
                                    throw new IllegalArgumentException("code:" + which + "");

                            }


                        }
                    }
                    , null);

            return true;
        }
    }

    /**
     * click bottone
     */
    private class __ClickButtonCerca extends OnClickListenerViewErrorCheck {
        public __ClickButtonCerca() {
            super(CircolariSearchFragment.this.getMainActivity());
        }

        @Override
        protected void onClickImpl(View v) throws Throwable {
            final ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
            try {
                db.runInTransaction(new ScuolaAppDBHelperRun() {
                    @Override
                    public void run(DaoSession session, Context ctx) throws Throwable {
                        final ManagerCircolare m = new ManagerCircolare(session);

                        if (DebugUtil.DEBUG__CircolariSearchFragment)
                            Log.d("CERCA_CIRCOLARE_FRAG", "LIST CIRCOLARI");

                        final String s = LAYOUT_OBJs.searchTextView.getText().toString();

                        final TreeSet<String> termini = new TreeSet<String>(Arrays.asList(s.split("[, \t\n]+")));
                        termini.remove("");
                        if (DebugUtil.DEBUG__CircolariSearchFragment)
                            Log.d("CERCA_CIRCOLARE_FRAG", "LIST CIRCOLARI (Termini: " + termini.size() + ", " + (termini) + " " + ")");


                        List<CircolareDB> circolariTrovate;
                        if (termini.size() == 0) {
                            if (DebugUtil.DEBUG__CircolariSearchFragment)
                                Log.d("CERCA_CIRCOLARE_FRAG", "ELENCO TOTALE");
                            circolariTrovate = new ArrayList<>(m.listAllCircolari());
                        } else {
                            if (DebugUtil.DEBUG__CircolariSearchFragment)
                                Log.d("CERCA_CIRCOLARE_FRAG", "SELECT CIRCOLARI BY RADICE");
                            circolariTrovate = new ArrayList<CircolareDB>(m.selectCircolariByTerms(termini));
                        }

                        //ordina
                        ManagerCircolare.sortLastToFirst(circolariTrovate);
                        a.update(circolariTrovate);

                    }
                });

            } finally {
                db.close();
            }
        }
    }

    private class ListenerModificaTestoMultiText implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            final String s1 = LAYOUT_OBJs.searchTextView.getText().toString();
            if (s1.trim().endsWith(",")) {
                LAYOUT_OBJs.imageButtonSearch.performClick();
                return;
            }


            final ListAdapter adapter = LAYOUT_OBJs.searchTextView.getAdapter();
            if (adapter.getCount() > 0) {
                a.update(new ArrayList<CircolareDB>());
            }
        }
    }
}

