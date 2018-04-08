package it.gov.scuolesuperioridizagarolo.fragment;

import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.ProdottiBarExpandibleListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.dialog.DialogProdottiBarQrCode;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_prodotti_bar_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.bar.ProdottoBar;
import it.gov.scuolesuperioridizagarolo.model.bar.ProdottoBarContainer;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by stefano on 03/01/2018.
 */
public class ProdottiBarFragment extends AbstractFragment {
    private static final String KEY_BUNDLE_PRODOTTI = "KEY_BUNDLE_PRODOTTI";
    private static final String KEY_FLAG_FILTER_PRODOTTI = "KEY_FLAG_FILTER_PRODOTTI";
    private LayoutObjs_fragment_prodotti_bar_xml LAYOUT_OBJs;
    private ProdottiBarExpandibleListAdapter a;
    private boolean flagFilter = false;
    private ProdottoBarContainer prodotti = null;

    @Override
    public void onSaveInstanceStateImpl(Bundle outState) {

        outState.putSerializable(KEY_BUNDLE_PRODOTTI, prodotti);
        outState.putBoolean(KEY_FLAG_FILTER_PRODOTTI, flagFilter);
    }

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {

        if (savedInstanceState != null) {
            prodotti = (ProdottoBarContainer) savedInstanceState.getSerializable(KEY_BUNDLE_PRODOTTI);
            flagFilter = savedInstanceState.getBoolean(KEY_FLAG_FILTER_PRODOTTI, false);
        }

        if (prodotti == null) {
            prodotti = new ProdottoBarContainer();
            prodotti.add(ProdottoBarContainer.demoProdotti("La mia ordinazione"));
        }


        View rootView = inflater.inflate(R.layout.fragment_prodotti_bar, container, false);

        LAYOUT_OBJs = new LayoutObjs_fragment_prodotti_bar_xml(rootView);

        a = new ProdottiBarExpandibleListAdapter(getMainActivity());
        a.setFlagHideEmpty(flagFilter);

        //==============================================================================================================

        final ProdottiBarExpandibleListAdapter.ProdottiBarExpandibleListAdapterListener listener = new ProdottiBarExpandibleListAdapter.ProdottiBarExpandibleListAdapterListener() {
            @Override
            public void dataChanged(HashMap<String, List<ProdottoBar>> _prodottiPerUtente, int indexGroup, int indexChild) {
                LAYOUT_OBJs.textView_titolo.setText("N.: " + prodotti.getNumeroProdotti() + ", tot: " + C_TextUtil.currency(prodotti.getPrezzoTotale()) + " ");
            }

        };
        a.setListener(listener);

        //==============================================================================================================
        a.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                LAYOUT_OBJs.textViewNumeroUtenti.setText("(" + a.getGroupCount() + ")");
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        });

        //==============================================================================================================
        LAYOUT_OBJs.listView_ordini.setAdapter(a);
        LAYOUT_OBJs.imageButton_Filter.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                flagFilter = !flagFilter;
                setGuiForButton_Filter();
                a.setFlagHideEmpty(flagFilter);
                if (flagFilter) {
                    expandAll();
                }
            }
        });
        setGuiForButton_Filter();

        //==============================================================================================================
        // Listview Group expanded listener
        LAYOUT_OBJs.listView_ordini.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < a.getGroupCount(); i++) {
                    if (i != groupPosition)
                        LAYOUT_OBJs.listView_ordini.collapseGroup(i);
                }

            }
        });

        //==============================================================================================================
        LAYOUT_OBJs.imageButton_plus.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                DialogUtil.openInputDialog(getMainActivity(), "Nuovo utente", "Inserire il nome del nuovo studente", new DialogUtil.InputDialogResult() {
                    @Override
                    public void onResult(String s) {
                        s = s.trim();
                        if (s.length() < 4 || prodotti.getNomiUtenti().contains(s)) {
                            Toast.makeText(ProdottiBarFragment.this.getMainActivity(), "Il nome è gia' presente oppure non valido", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        prodotti.add(ProdottoBarContainer.demoProdotti(s));
                        a.updateAll(prodotti.getLista());
                        collapseAll();
                        LAYOUT_OBJs.listView_ordini.expandGroup(a.getGroupCount() - 1);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });

        //==============================================================================================================
        LAYOUT_OBJs.imageButton_qrcodegenerator.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                DialogProdottiBarQrCode d = new DialogProdottiBarQrCode(getMainActivity(), prodotti.toQrCodeString());
            }
        });


        //==============================================================================================================
        LAYOUT_OBJs.imageButton_Minus.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {

                final CharSequence[] values = prodotti.getNomiUtentiArray();
                DialogUtil.openSingleChooseDialog(getMainActivity(), "Cancella ordini per utente", true, values, null,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, final int which) {
                                if (which <= 0) {
                                    Toast.makeText(getMainActivity(), "Impossibile cancellare il proprio ordine", Toast.LENGTH_LONG).show();
                                } else {
                                    prodotti.remove(new ProdottoBarContainer.ProdottoBarContainerFilter() {
                                        @Override
                                        public boolean accept(ProdottoBar b) {
                                            return b.nomeUtente.equals(values[which]);
                                        }
                                    });
                                    a.updateAll(prodotti.getLista());

                                }
                            }
                        });
            }
        });

        //==============================================================================================================
        LAYOUT_OBJs.imageButton_people.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                LinkedHashMap<String, String> mappa = new LinkedHashMap<String, String>();
                for (String u : prodotti.getNomiUtentiArray()) {

                    StringBuilder sb = new StringBuilder();
                    final List<ProdottoBar> lista = prodotti.getLista(u);
                    for (ProdottoBar p : lista) {
                        if (p.quantita > 0)
                            sb.append(String.format("%s: %d x %s <br>", p.nomeProdotto, p.quantita, C_TextUtil.currency(p.prezzounitario)));
                    }


                    mappa.put(u + " " + C_TextUtil.currency(prodotti.getPrezzoTotale(u)), sb.toString());
                }

                DialogUtil.openInfoDialog(getMainActivity(), "Dettaglio ordini", mappa);
            }
        });


        //==============================================================================================================
        a.add(prodotti.getLista());
        if (a.getGroupCount() == 1) {
            LAYOUT_OBJs.listView_ordini.expandGroup(0);
        }

        return rootView;
    }

    private void setGuiForButton_Filter() {
        if (flagFilter) {
            LAYOUT_OBJs.imageButton_Filter.setBackground(getMainActivity().getResources().getDrawable(R.drawable.background_pulsante_yellow));
        } else {
            LAYOUT_OBJs.imageButton_Filter.setBackgroundColor(getMainActivity().getResources().getColor(R.color.color_transparent));
        }
    }

    private void collapseAll() {
        for (int i = 0; i < a.getGroupCount(); i++) {
            LAYOUT_OBJs.listView_ordini.collapseGroup(i);
        }
    }

    private void expandAll() {
        for (int i = 0; i < a.getGroupCount(); i++) {
            LAYOUT_OBJs.listView_ordini.expandGroup(i);
        }
    }

}
