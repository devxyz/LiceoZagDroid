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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.adapter.ArticoliCircolariListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.cache.UrlFileCache;
import it.gov.scuolesuperioridizagarolo.dao.ArticoloDBDao;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDBHelperRun;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.db.ManagerArticolo;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_articoli_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdo;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdoContainer;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetailsCircolare;
import it.gov.scuolesuperioridizagarolo.util.DebugUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.util.TreeSet;


public class ArticoloCircolariFragment extends AbstractFragment {
    private LayoutObjs_fragment_articoli_xml LAYOUT_OBJs;   //***************************
    private UrlFileCache cache = null;
    private ArticoliCircolariListAdapter a;
    private ArrayAdapter<String> multiTextViewAdapter;
    private ArticoloSdoContainer<ArticoloDetailsCircolare> articoliCircolari = new ArticoloSdoContainer<>();

    public ArticoloCircolariFragment() {
    }

    public static void updateCircolare_impostaFlagLettura(MainMenuActivity m, final ArticoliCircolariListAdapter a, final boolean flagLettura) {
        //aggiorna il database
        ScuolaAppDbHelper db = new ScuolaAppDbHelper(m);
        try {
            db.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    final ArticoloSdoContainer<ArticoloDetailsCircolare> circolari = a.getArticoliCircolari();
                    final ArticoloDBDao circolareDBDao = session.getArticoloDBDao();
                    for (ArticoloSdo c : circolari.articoli) {
                        c.wrapperArticolo.articolo.setFlagLettura(flagLettura);
                        circolareDBDao.update(c.wrapperArticolo.articolo);
                    }
                }
            });
        } catch (Throwable throwable) {

        } finally {
            db.close();
        }
        a.notifyDataSetChanged();
    }

    public static void updateCircolare_impostaFlagLettura(MainMenuActivity m, final ArticoliCircolariListAdapter a, final ArticoloSdo c, final boolean flagLettura) {
        //aggiorna il database
        ScuolaAppDbHelper db = new ScuolaAppDbHelper(m);
        try {
            db.runInTransaction(new ScuolaAppDBHelperRun() {
                @Override
                public void run(DaoSession session, Context ctx) throws Throwable {
                    final ArticoloDBDao circolareDBDao = session.getArticoloDBDao();
                    c.wrapperArticolo.articolo.setFlagLettura(flagLettura);
                    circolareDBDao.update(c.wrapperArticolo.articolo);

                }
            });
        } catch (Throwable throwable) {

        } finally {
            db.close();
        }
        a.notifyDataSetChanged();
    }


    public static TreeSet<String> listaParole_Circolare(MainMenuActivity m, ArticoliCircolariListAdapter a, final ArticoloSdo<ArticoloDetailsCircolare> c) {
        final TreeSet<String> ris = new TreeSet<>();
        ris.addAll(c.parole());

        /*
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
        */
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
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {
        View rootView = inflater.inflate(R.layout.fragment_articoli, container, false);
        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_articoli_xml(rootView);
        //**************************


        cache = (getMainActivity()).getCache();
        LAYOUT_OBJs.searchTextView.addTextChangedListener(new ListenerModificaTestoMultiText());
        LAYOUT_OBJs.imageButtonSearch.setOnClickListener(new __ClickButtonCerca());
        a = new ArticoliCircolariListAdapter(getMainActivity(), articoliCircolari);
        LAYOUT_OBJs.listView.setAdapter(a);

        LAYOUT_OBJs.listView.setLongClickable(true);
        LAYOUT_OBJs.listView.setOnItemClickListener(new __ClickApriDialogCircolare(a, this));
        LAYOUT_OBJs.listView.setOnItemLongClickListener(new MyOnItemLongClickListener(a, this));

        /*LAYOUT_OBJs.imageButtonPlus.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                String[] valori = classi.toArray(new String[classi.size()]);


                DialogUtil.openSingleChooseDialog(activity, "Scegli la classe", false, valori, null, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });*/

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
                        final ManagerArticolo managerCircolare = new ManagerArticolo(session);

                        //update termin
                        final ArticoloSdoContainer<ArticoloDetailsCircolare> articoli = managerCircolare.elencoArticoliCircolari();
                        multiTextViewAdapter.clear();
                        multiTextViewAdapter.addAll(articoli.parole());


                        multiTextViewAdapter.notifyDataSetChanged();
                        if (DebugUtil.DEBUG__CircolariSearchFragment)
                            Log.d("CERCA_CIRCOLARE_FRAG", "UPDATE LISTA TERMINI " + articoli.articoli.size());


                        articoli.sortBy(ArticoloSdoContainer.getArticoloTypeCircolareComparator());

                        a.update(articoliCircolari);


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


    public static class __ClickApriDialogCircolare implements AdapterView.OnItemClickListener {
        private final ArticoliCircolariListAdapter a;
        private AbstractFragment fragment;
        private MainMenuActivity activity;

        public __ClickApriDialogCircolare(ArticoliCircolariListAdapter a, AbstractFragment fragment) {
            this.a = a;
            this.fragment = fragment;
            this.activity = fragment.getMainActivity();
        }

        private MainMenuActivity getMainActivity() {
            return activity;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            Toast.makeText(getMainActivity(), "DA IMPLEMENTARE", Toast.LENGTH_LONG).show();
        }
    }

    public static class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        private final ArticoliCircolariListAdapter a;
        private final AbstractFragment fragment;

        public MyOnItemLongClickListener(ArticoliCircolariListAdapter a, AbstractFragment fragment) {
            this.a = a;
            this.fragment = fragment;
        }


        private MainMenuActivity getMainActivity() {
            return fragment.getMainActivity();
        }

        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {

            final ArticoloSdo<ArticoloDetailsCircolare> c = a.getArticoliCircolari().articoli.get(position);

            DialogUtil.openSingleChooseDialog(fragment.getActivity(), "Quale azione vuoi svolgere?", true, new CharSequence[]{
                            "Segna tutte da leggere",
                            "Segna tutte lette",
                            "Segna circolare corrente da leggere",
                            "Segna circolare corrente letta",
                            "Informazioni"
                    }, null, new DialogInterface.OnClickListener() {
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
            );

            return true;
        }
    }

    /**
     * click bottone
     */
    private class __ClickButtonCerca extends OnClickListenerViewErrorCheck {
        public __ClickButtonCerca() {
            super(ArticoloCircolariFragment.this.getMainActivity());
        }

        @Override
        protected void onClickImpl(View v) throws Throwable {
            Toast.makeText(getMainActivity(), "DA IMPLEMENTARE", Toast.LENGTH_LONG).show();
            /*final ScuolaAppDbHelper db = new ScuolaAppDbHelper(getMainActivity());
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


                        List<ArticoloSdo> circolariTrovate;
                        if (termini.size() == 0) {
                            if (DebugUtil.DEBUG__CircolariSearchFragment)
                                Log.d("CERCA_CIRCOLARE_FRAG", "ELENCO TOTALE");
                            circolariTrovate = new ArrayList<>(m.listAllCircolari());
                        } else {
                            if (DebugUtil.DEBUG__CircolariSearchFragment)
                                Log.d("CERCA_CIRCOLARE_FRAG", "SELECT CIRCOLARI BY RADICE");
                            circolariTrovate = new ArrayList<ArticoloSdo>(m.selectCircolariByTerms(termini));
                        }

                        //ordina
                        ManagerCircolare.sortLastToFirst(circolariTrovate);
                        a.update(circolariTrovate);

                    }
                });

            } finally {
                db.close();
            }*/
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


            /*
            final String s1 = LAYOUT_OBJs.searchTextView.getText().toString();
            if (s1.trim().endsWith(",")) {
                LAYOUT_OBJs.imageButtonSearch.performClick();
                return;
            }


            final ListAdapter adapter = LAYOUT_OBJs.searchTextView.getAdapter();
            if (adapter.getCount() > 0) {
                a.update(new ArrayList<ArticoloSdo>());
            }*/
        }
    }
}

