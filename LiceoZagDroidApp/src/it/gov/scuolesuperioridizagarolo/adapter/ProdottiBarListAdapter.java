package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_bar_prodotto_dettaglio_xml;
import it.gov.scuolesuperioridizagarolo.model.bar.ProdottoBar;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by stefano on 03/01/2018.
 */
@Deprecated
public class ProdottiBarListAdapter extends BaseAdapter {
    private final Activity a;
    private final List<ProdottoBar> prodotti;
    private ProdottiBarListAdapterListener listener;
    private ArrayList<View> views;

    public ProdottiBarListAdapter(Activity a) {
        this.a = a;
        this.prodotti = new ArrayList<>();
        views = new ArrayList<>();
    }

    public void add(List<ProdottoBar> p) {
        for (ProdottoBar prodottoBar : p) {
            _add(prodottoBar);
        }
        notifyDataSetChanged();
    }

    public void add(ProdottoBar p) {
        _add(p);
        notifyDataSetChanged();
    }

    private void _add(ProdottoBar p) {
        if (prodotti.size() == 0) {
            prodotti.add(p);
            views.add(null);
            return;
        }
        final ProdottoBar last = prodotti.get(prodotti.size() - 1);
        if (last != null && !Objects.equals(p.nomeUtente, last.nomeUtente)) {
            prodotti.add(null);//separatore per il nome
            views.add(null);
        }
        prodotti.add(p);
        views.add(null);
    }

    public void setListener(ProdottiBarListAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return prodotti.size();
    }

    @Override
    public ProdottoBar getItem(int position) {
        return prodotti.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = views.get(position);
        final LayoutObjs_listview_bar_prodotto_dettaglio_xml obj;

        final ProdottoBar item = getItem(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_bar_prodotto_dettaglio, null);
            views.set(position, convertView);
            obj = new LayoutObjs_listview_bar_prodotto_dettaglio_xml(convertView);


            obj.number_picker.setValueChangedListener(new ValueChangedListener() {
                final int index = position;


                @Override
                public void valueChanged(int value, ActionEnum action) {
                    final ProdottoBar p = getItem(position);
                    p.quantita = obj.number_picker.getValue();
                    if (ProdottiBarListAdapter.this.listener != null) {
                        ProdottiBarListAdapter.this.listener.dataChanged(prodotti, index);
                    }
                    if (p.quantita > 0) {
                        obj.textView_Titolo.setBackground(a.getResources().getDrawable(R.drawable.background_pulsante_yellow));
                    } else {
                        obj.textView_Titolo.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));
                    }
                }
            });
        } else {
            obj = new LayoutObjs_listview_bar_prodotto_dettaglio_xml(convertView);
        }


        if (item != null) {
            obj.number_picker.setValue(item.quantita);
            obj.textView_Prezzo.setText("Prezzo " + C_TextUtil.currency(item.prezzounitario));
            obj.textView_Titolo.setText(item.nomeProdotto);
        } else {
            obj.number_picker.setVisibility(View.GONE);
            obj.textView_Prezzo.setVisibility(View.GONE);
            obj.textView_Titolo.setText(getItem(position + 1).nomeUtente);
            obj.textView_Titolo.setBackground(a.getResources().getDrawable(R.drawable.background_pulsante_red));
            obj.textView_Titolo.setTextColor(a.getResources().getColor(R.color.color_white));
        }
        //final BitOrarioOraLezione item = getItem(position);

        return convertView;
    }

    public interface ProdottiBarListAdapterListener {
        void dataChanged(List<ProdottoBar> prodotti, int index);
    }
}
