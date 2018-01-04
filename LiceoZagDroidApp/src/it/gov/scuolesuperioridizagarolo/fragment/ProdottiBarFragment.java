package it.gov.scuolesuperioridizagarolo.fragment;

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
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_prodotti_bar_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.ProdottoBar;
import it.gov.scuolesuperioridizagarolo.model.ProdottoBarContainer;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stefano on 03/01/2018.
 */
public class ProdottiBarFragment extends AbstractFragment {
    private static final String KEY_BUNDLE_PRODOTTI = "KEY_BUNDLE_PRODOTTI";
    private LayoutObjs_fragment_prodotti_bar_xml LAYOUT_OBJs;
    private ProdottiBarExpandibleListAdapter a;
    private boolean flagFilter = false;
    private ProdottoBarContainer prodotti = null;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_BUNDLE_PRODOTTI, prodotti);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            prodotti = (ProdottoBarContainer) savedInstanceState.getSerializable(KEY_BUNDLE_PRODOTTI);
        }

        if (prodotti == null) {
            prodotti = new ProdottoBarContainer();
            prodotti.add(demoData("La mia ordinazione"));
        }


        View rootView = inflater.inflate(R.layout.fragment_prodotti_bar, container, false);

        LAYOUT_OBJs = new LayoutObjs_fragment_prodotti_bar_xml(rootView);


        final ProdottiBarExpandibleListAdapter.ProdottiBarExpandibleListAdapterListener listener = new ProdottiBarExpandibleListAdapter.ProdottiBarExpandibleListAdapterListener() {
            @Override
            public void dataChanged(HashMap<String, List<ProdottoBar>> _prodottiPerUtente, int indexGroup, int indexChild) {
                double costo = 0;
                int count = 0;
                for (Map.Entry<String, List<ProdottoBar>> e : _prodottiPerUtente.entrySet()) {
                    for (ProdottoBar x : e.getValue()) {
                        if (x != null) {
                            costo += x.prezzounitario * x.quantita;
                            count += x.quantita;
                        }
                    }
                }
                LAYOUT_OBJs.textView_titolo.setText("N.: " + count + ", tot: " + C_TextUtil.currency(costo) + " ");

            }

        };


        a = new ProdottiBarExpandibleListAdapter(getMainActivity());
        a.setListener(listener);
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


        LAYOUT_OBJs.listView_ordini.setAdapter(a);
        LAYOUT_OBJs.imageButton_Filter.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                flagFilter = !flagFilter;
                if (flagFilter) {
                    LAYOUT_OBJs.imageButton_Filter.setBackground(getMainActivity().getResources().getDrawable(R.drawable.background_pulsante_yellow));
                } else {
                    LAYOUT_OBJs.imageButton_Filter.setBackgroundColor(getMainActivity().getResources().getColor(R.color.color_transparent));
                }
            }
        });


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

                        a.add(demoData(s));
                        for (int i = 0; i < a.getGroupCount(); i++) {
                            LAYOUT_OBJs.listView_ordini.collapseGroup(i);
                        }
                        LAYOUT_OBJs.listView_ordini.expandGroup(a.getGroupCount() - 1);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });

        a.add(prodotti.getLista());

        if (a.getGroupCount() == 1) {
            LAYOUT_OBJs.listView_ordini.expandGroup(0);
        }

        return rootView;
    }

    private ArrayList<ProdottoBar> demoData(String nomeUtente) {
        final ArrayList<ProdottoBar> objects = new ArrayList<>();
        int i = 0;

        objects.add(new ProdottoBar(i++, nomeUtente, "panino mortadella", 1));
        objects.add(new ProdottoBar(i++, nomeUtente, "cappuccino", 1.5));
        objects.add(new ProdottoBar(i++, nomeUtente, "mocaccino", 0.5));
        objects.add(new ProdottoBar(i++, nomeUtente, "panino salame", 0.8));
        objects.add(new ProdottoBar(i++, nomeUtente, "cappuccino chiaro", 1.3));
        objects.add(new ProdottoBar(i++, nomeUtente, "latte macchiato", 0.7));
        objects.add(new ProdottoBar(i++, nomeUtente, "tamezzino salame", 1));
        objects.add(new ProdottoBar(i++, nomeUtente, "tramezzino mortadella", 1));
        return objects;
    }
}
