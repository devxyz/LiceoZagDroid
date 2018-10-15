package it.gov.scuolesuperioridizagarolo.adapter;

/**
 * Created by stefano on 04/01/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_disposizionidocenti_detail_xml;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_disposizionidocenti_header_xml;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DisposizioniDocentiExpandibleListAdapter extends BaseExpandableListAdapter {


    protected BitOrarioGrigliaOrarioContainer containerOrari;
    protected OnlyDate giorno;
    private BitOrarioGrigliaOrario orario;
    private EOra[] ore;

    private Context a;

    public DisposizioniDocentiExpandibleListAdapter(Context context, BitOrarioGrigliaOrarioContainer containerOrari, OnlyDate giorno) {
        this.a = context;
        this.containerOrari = containerOrari;
        this.orario = containerOrari.getOrario(giorno);
        this.giorno = giorno;
        ore = EOra.valuesOreDiLezione();
    }

    public void updateOrario(BitOrarioGrigliaOrarioContainer o) {
        this.containerOrari = o;
        orario = containerOrari.getOrario(giorno);
        notifyDataSetChanged();
    }

    public final void setGiorno(OnlyDate g) {
        giorno = g;
        orario = containerOrari.getOrario(g);
        super.notifyDataSetChanged();
    }

    @Override
    public String getChild(int groupPosition, int childPosititon) {
        final EOra ora = getGroup(groupPosition);
        Set<String> docentiDispo = orario.getDocentiDisposizione().get(new BitOrarioGrigliaOrario.GiornoOra(giorno.getGiorno(), ora));
        if (docentiDispo == null) {
            docentiDispo = new TreeSet<>();
        }


        final ArrayList<String> docenti = new ArrayList<>(docentiDispo);
        return docenti.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String nomeDocente = getChild(groupPosition, childPosition);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_disposizionidocenti_detail, parent, false);
        }
        LayoutObjs_listview_disposizionidocenti_detail_xml obj = new LayoutObjs_listview_disposizionidocenti_detail_xml(convertView);
        obj.textView_aula.setText("DISP");
        obj.textView_docente_disp.setText(nomeDocente);
        obj.textView5.setText("Disposizione");
        obj.textView_aula.setTextColor(a.getResources().getColor(R.color.color_yellow));
        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        //ok
        final EOra ora = getGroup(groupPosition);
        Set<String> docentiDispo = orario.getDocentiDisposizione().get(new BitOrarioGrigliaOrario.GiornoOra(giorno.getGiorno(), ora));
        if (docentiDispo == null) {
            return 0;
        }
        return docentiDispo.size();
    }

    @Override
    public EOra getGroup(int groupPosition) {
        //ok
        return ore[groupPosition];
    }

    @Override
    public int getGroupCount() {
        //ok
        return this.ore.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        //ok
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //ok
        final EOra ora = ore[groupPosition];


        Set<String> docentiDispo = orario.getDocentiDisposizione().get(new BitOrarioGrigliaOrario.GiornoOra(giorno.getGiorno(), ora));
        if (docentiDispo == null) {
            docentiDispo = new TreeSet<>();
        }

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_disposizionidocenti_header, parent, false);
        }

        LayoutObjs_listview_disposizionidocenti_header_xml obj = new LayoutObjs_listview_disposizionidocenti_header_xml(convertView);
        obj.textView_ora.setText(ora.getProgressivOra() + "°ora");
        obj.textView_fascia.setText(ora.fascia());
        obj.textView5.setText(docentiDispo.size() + " docenti");
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        //ok
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //ok
        return true;
    }

}

