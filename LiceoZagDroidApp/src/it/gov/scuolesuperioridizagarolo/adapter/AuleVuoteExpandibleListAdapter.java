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
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_classivuote_detail_xml;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_classivuote_header_xml;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.OnlyDate;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.TreeSet;

public class AuleVuoteExpandibleListAdapter extends BaseExpandableListAdapter {
    protected BitOrarioGrigliaOrarioContainer containerOrari;
    private boolean includeDDI;
    protected OnlyDate giorno;
    private BitOrarioGrigliaOrario orario;
    private EOra[] ore;

    private Context a;

    public AuleVuoteExpandibleListAdapter(Context context, BitOrarioGrigliaOrarioContainer containerOrari, OnlyDate giorno, boolean includeDDI) {
        this.a = context;
        this.containerOrari = containerOrari;
        this.includeDDI = includeDDI;
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
    public RoomData getChild(int groupPosition, int childPosititon) {
        final EOra group = getGroup(groupPosition);

        final ArrayList<RoomData> auleVuote = new ArrayList<>(getAuleVuote(group, giorno.getGiorno()));
        return auleVuote.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final RoomData item = getChild(groupPosition, childPosition);


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_classivuote_detail, parent, false);
        }
        LayoutObjs_listview_classivuote_detail_xml obj = new LayoutObjs_listview_classivuote_detail_xml(convertView);
        obj.textView_aula.setText(item.simpleName());
        obj.textView_location.setText(item.location.description + (item.flagLIM ? " con LIM" : ""));
        obj.textView_materie.setText(item.usage + " - " + item.maxStudents + " posti");
        AbstractOrarioListAdapter.coloraViewAula(obj.textView_aula, item.location, a);
        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        //ok
        final EOra ora = getGroup(groupPosition);
        return getAuleVuote(ora, giorno.getGiorno()).size();
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
        EGiorno giorno = this.giorno.getGiorno();
        final TreeSet<RoomData> auleVuote = getAuleVuote(ora, giorno);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(a);
            convertView = layoutInflater.inflate(R.layout.listview_classivuote_header, parent, false);
        }

        LayoutObjs_listview_classivuote_header_xml obj = new LayoutObjs_listview_classivuote_header_xml(convertView);
        obj.textView_ora.setText(ora.getProgressivOra() + "°ora");
        obj.textView_fascia.setText(ora.fasciaPresenza());
        obj.textView_materie.setText(auleVuote.size() + " aule");
        return convertView;
    }

    public TreeSet<RoomData> getAuleVuote(EOra ora, EGiorno giorno) {
        if (includeDDI)
            return orario.getAuleVuoteIncludeDDI(ora, giorno);
        else
            return orario.getAuleVuote(ora, giorno);
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

