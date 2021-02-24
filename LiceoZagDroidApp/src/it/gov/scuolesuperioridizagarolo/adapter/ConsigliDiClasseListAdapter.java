package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetailsCircolare;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloTagDetails;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_articoli_circolari_xml;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_consigli_classe_docente_xml;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by stefano on 04/03/15.
 */
public class ConsigliDiClasseListAdapter extends BaseAdapter {

    private ArrayList<BitOrarioGrigliaOrario.InsegnamentiDocente> insegnanti;

    private Activity activity;
    private LayoutInflater layoutInflater;


    public ConsigliDiClasseListAdapter(Activity f, TreeMap<String, BitOrarioGrigliaOrario.InsegnamentiDocente> insegnanti) {
        this.insegnanti = new ArrayList<>(insegnanti.values());
        activity = f;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<BitOrarioGrigliaOrario.InsegnamentiDocente> getInsegnanti() {
        return insegnanti;
    }

    public void update(TreeMap<String, BitOrarioGrigliaOrario.InsegnamentiDocente> insegnanti) {
        this.insegnanti = new ArrayList<>(insegnanti.values());
        super.notifyDataSetChanged();
    }

    public void close() {
    }

    @Override
    public int getCount() {

        // Set the total list item count
        return insegnanti.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate the item layout and set the views
        View listItem = convertView;
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.listview_consigli_classe_docente, null);
        }

        LayoutObjs_listview_consigli_classe_docente_xml LAYOUT_OBJs;
        LAYOUT_OBJs = new LayoutObjs_listview_consigli_classe_docente_xml(listItem);
        // Initialize the views in the layout
        TextView materie = LAYOUT_OBJs.textView_materia;
        TextView docente = LAYOUT_OBJs.textView_docente;

        final BitOrarioGrigliaOrario.InsegnamentiDocente c = this.insegnanti.get(position);
        materie.setText(c.materie.toString());
        docente.setText(c.docente);
        return listItem;
    }


}
