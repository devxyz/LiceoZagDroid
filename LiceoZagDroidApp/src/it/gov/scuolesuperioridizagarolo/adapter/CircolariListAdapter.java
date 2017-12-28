package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.dao.CircolareDB;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_circolari_and_notizie_xml;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by stefano on 04/03/15.
 */
public class CircolariListAdapter extends BaseAdapter {

    private final List<CircolareDB> circolari;
    private final boolean showTesto;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public CircolariListAdapter(Activity f) {
        this(f, true);
    }

    public CircolariListAdapter(Activity f, boolean showTesto) {
        this(f, new ArrayList<CircolareDB>(), showTesto);

    }

    public CircolariListAdapter(Activity f, List<CircolareDB> circolari) {
        this(f, circolari, true);
    }

    public CircolariListAdapter(Activity f, List<CircolareDB> circolari, boolean showTesto) {
        this.showTesto = showTesto;
        this.circolari = new ArrayList<CircolareDB>(circolari == null ? Collections.EMPTY_LIST : circolari);
        activity = f;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<CircolareDB> getCircolari() {
        return circolari;
    }

    public void update(List<CircolareDB> nuovaLista) {
        this.circolari.clear();
        this.circolari.addAll(circolari == null ? Collections.EMPTY_LIST : nuovaLista);
        super.notifyDataSetChanged();
    }

    public void close() {
    }

    @Override
    public int getCount() {

        // Set the total list item count
        return circolari.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private String extractText(String testo) {

        if (testo == null)
            return null;
        testo = C_TextUtil.normalizeTextAndLineFeed_forTextCircolari(testo, true);

        final StringBuilder sb = new StringBuilder();
        for (int j = 0; j < testo.length(); j++) {
            char c = testo.charAt(j);
            if (c == '\n' && sb.length() > 200) {
                return sb.substring(0, 200) + "...";
            }
            sb.append(c);
        }

        return (sb.toString().replaceAll("[\n \\s]+", " ")).trim();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate the item layout and set the views
        View listItem = convertView;
        int pos = position;
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.listview_circolari_and_notizie, null);
        }

        LayoutObjs_listview_circolari_and_notizie_xml LAYOUT_OBJs;
        LAYOUT_OBJs = new LayoutObjs_listview_circolari_and_notizie_xml(listItem);
        // Initialize the views in the layout
        ImageView iv = LAYOUT_OBJs.image;
        TextView tvTitle = LAYOUT_OBJs.title;
        TextView tvDate = LAYOUT_OBJs.date;


        final TextView desc = LAYOUT_OBJs.descrizione;


        CircolareDB c = this.circolari.get(pos);
        if (showTesto) {
            desc.setVisibility(View.VISIBLE);
            if (c.getTesto() != null)
                desc.setText(extractText(C_TextUtil.normalizeTextAndLineFeed_forTextCircolari(c.getTesto(), true)));

            else
                desc.setText("(Testo non disponibile)");
        } else {
            desc.setVisibility(View.GONE);
        }

        tvDate.setText(C_DateUtil.toDDMMYYY(c.getData()));



        /*final Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, -1);
        final Date ieri = instance.getTime();*/


        if (c.getFlagContenutoLetto()) {
            tvTitle.setTypeface(Typeface.DEFAULT);
            tvTitle.setText(c.getNumero() + " - " + c.getTitolo());
            iv.setImageResource(R.drawable.arrow_next);
        } else {
            tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
            tvTitle.setText(c.getNumero() + " - " + c.getTitolo() + "");
            iv.setImageResource(R.drawable.new_icon_50x50);
        }

        return listItem;
    }


}
