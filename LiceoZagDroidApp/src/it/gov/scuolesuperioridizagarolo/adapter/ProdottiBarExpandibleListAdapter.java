package it.gov.scuolesuperioridizagarolo.adapter;

/**
 * Created by stefano on 04/01/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_bar_prodotto_dettaglio_xml;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_bar_prodotto_utente_xml;
import it.gov.scuolesuperioridizagarolo.model.ProdottoBar;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProdottiBarExpandibleListAdapter extends BaseExpandableListAdapter {
    private ProdottiBarExpandibleListAdapterListener listener;
    private Context a;
    private List<String> _nomeUtenti; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<ProdottoBar>> _prodottiPerUtente;
    private HashMap<String, List<View>> _viewPerUtente;


    public ProdottiBarExpandibleListAdapter(Context context) {
        this.a = context;
        this._nomeUtenti = new ArrayList<>();
        this._prodottiPerUtente = new HashMap<>();
        _viewPerUtente = new HashMap<>();
    }

    public List<ProdottoBar> getAllItem() {
        List<ProdottoBar> ris = new ArrayList<>();
        for (int i = 0; i < getGroupCount(); i++) {
            final String nome = getGroup(i);
            ris.addAll(_prodottiPerUtente.get(nome));
        }
        return ris;
    }

    public void updateAll(List<ProdottoBar> p) {
        _nomeUtenti.clear();
        _prodottiPerUtente.clear();
        _viewPerUtente.clear();
        add(p);
    }

    public void add(List<ProdottoBar> p) {
        for (ProdottoBar prodottoBar : p) {
            _add(prodottoBar);
        }
        notifyDataSetChanged();
        listener.dataChanged(_prodottiPerUtente, 0, 0);

    }

    public void add(ProdottoBar p) {
        _add(p);
        notifyDataSetChanged();
        listener.dataChanged(_prodottiPerUtente, 0, 0);
    }

    private void _add(ProdottoBar p) {
        String nome = p.nomeUtente;
        final List<View> viste;
        final List<ProdottoBar> prodotti;
        if (_prodottiPerUtente.containsKey(nome)) {
            prodotti = _prodottiPerUtente.get(nome);
            viste = _viewPerUtente.get(nome);
        } else {
            prodotti = new ArrayList<>();
            viste = new ArrayList<>();
            _prodottiPerUtente.put(nome, prodotti);
            _viewPerUtente.put(nome, viste);
            _nomeUtenti.add(nome);
        }
        prodotti.add(p);
        viste.add(null);
    }

    @Override
    public ProdottoBar getChild(int groupPosition, int childPosititon) {
        final List<ProdottoBar> prodottoBars = this._prodottiPerUtente.get(this._nomeUtenti.get(groupPosition));
        return prodottoBars.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String nomeUtente = _nomeUtenti.get(groupPosition);

        final List<View> views = _viewPerUtente.get(nomeUtente);

        convertView = views.get(childPosition);
        final LayoutObjs_listview_bar_prodotto_dettaglio_xml obj;

        final ProdottoBar item = getChild(groupPosition, childPosition);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_bar_prodotto_dettaglio, null);
            views.set(childPosition, convertView);
            obj = new LayoutObjs_listview_bar_prodotto_dettaglio_xml(convertView);


            obj.number_picker.setValueChangedListener(new ValueChangedListener() {
                final int indexGroup = groupPosition;
                final int indexChild = childPosition;


                @Override
                public void valueChanged(int value, ActionEnum action) {
                    final ProdottoBar p = getChild(groupPosition, childPosition);
                    p.quantita = obj.number_picker.getValue();
                    if (ProdottiBarExpandibleListAdapter.this.listener != null) {
                        ProdottiBarExpandibleListAdapter.this.listener.dataChanged(_prodottiPerUtente, groupPosition, childPosition);
                    }

                    _controlloSelezionato(item, obj);

                }
            });
        } else {
            obj = new LayoutObjs_listview_bar_prodotto_dettaglio_xml(convertView);
        }


        obj.number_picker.setValue(item.quantita);
        obj.textView_Prezzo.setText("Prezzo " + C_TextUtil.currency(item.prezzounitario));
        obj.textView_Titolo.setText(item.nomeProdotto);

        _controlloSelezionato(item, obj);


        return convertView;
    }

    private void _controlloSelezionato(ProdottoBar item, LayoutObjs_listview_bar_prodotto_dettaglio_xml obj) {
        if (item.quantita > 0) {
            obj.layout.setBackground(a.getResources().getDrawable(R.drawable.background_pulsante_yellow));
        } else {
            obj.layout.setBackgroundColor(a.getResources().getColor(R.color.color_transparent));
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._prodottiPerUtente.get(this._nomeUtenti.get(groupPosition))
                .size();
    }

    @Override
    public String getGroup(int groupPosition) {
        final String s = this._nomeUtenti.get(groupPosition);
        return s;
    }

    @Override
    public int getGroupCount() {
        return this._nomeUtenti.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String nomeUtente = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listview_bar_prodotto_utente, null);
        }
        LayoutObjs_listview_bar_prodotto_utente_xml obj = new LayoutObjs_listview_bar_prodotto_utente_xml(convertView);

        obj.textView_Utente.setText(nomeUtente);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setListener(ProdottiBarExpandibleListAdapterListener listener) {
        this.listener = listener;
        listener.dataChanged(_prodottiPerUtente, 0, 0);
    }

    public static interface ProdottiBarExpandibleListAdapterListener {
        public void dataChanged(HashMap<String, List<ProdottoBar>> _prodottiPerUtente, int indexGroup, int indexChild);
    }
}

