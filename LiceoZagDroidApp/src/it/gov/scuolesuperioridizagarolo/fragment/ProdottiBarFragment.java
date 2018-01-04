package it.gov.scuolesuperioridizagarolo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.adapter.ProdottiBarExpandibleListAdapter;
import it.gov.scuolesuperioridizagarolo.adapter.ProdottiBarListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_prodotti_bar_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.ProdottoBar;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 03/01/2018.
 */
public class ProdottiBarFragment extends AbstractFragment {
    private LayoutObjs_fragment_prodotti_bar_xml LAYOUT_OBJs;
    private ProdottiBarExpandibleListAdapter a;
    private boolean flagFilter = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prodotti_bar, container, false);

        LAYOUT_OBJs = new LayoutObjs_fragment_prodotti_bar_xml(rootView);


        final ProdottiBarListAdapter.ProdottiBarListAdapterListener listener = new ProdottiBarListAdapter.ProdottiBarListAdapterListener() {
            @Override
            public void dataChanged(List<ProdottoBar> prodotti, int index) {
                double costo = 0;
                int count = 0;
                for (ProdottoBar x : prodotti) {
                    if (x != null) {
                        costo += x.prezzounitario * x.quantita;
                        count += x.quantita;
                    }
                }

                LAYOUT_OBJs.textView_titolo.setText("N.: " + count + ", tot: " + C_TextUtil.currency(costo) + " ");
            }
        };
        a = new ProdottiBarExpandibleListAdapter(getMainActivity());
        //a.setListener(listener);
        a.add(demoData("me"));
        a.add(demoData("paperino"));

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
