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
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloTagDetails;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_articoli_circolari_xml;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdo;
import it.gov.scuolesuperioridizagarolo.model.articolo.ArticoloSdoContainer;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetailsCircolare;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;

/**
 * Created by stefano on 04/03/15.
 */
public class ArticoliCircolariListAdapter extends BaseAdapter {

    private ArticoloSdoContainer<ArticoloDetailsCircolare> articoliCircolari;
    private Activity activity;
    private LayoutInflater layoutInflater;


    public ArticoliCircolariListAdapter(Activity f) {
        this(f, new ArticoloSdoContainer<ArticoloDetailsCircolare>());

    }

    public ArticoliCircolariListAdapter(Activity f, ArticoloSdoContainer<ArticoloDetailsCircolare> circolari) {
        this.articoliCircolari = circolari;
        activity = f;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArticoloSdoContainer<ArticoloDetailsCircolare> getArticoliCircolari() {
        return articoliCircolari;
    }

    public void update(ArticoloSdoContainer<ArticoloDetailsCircolare> nuovaLista) {
        this.articoliCircolari = nuovaLista;
        super.notifyDataSetChanged();
    }

    public void close() {
    }

    @Override
    public int getCount() {

        // Set the total list item count
        return articoliCircolari.articoli.size();
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
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.listview_articoli_circolari, null);
        }

        LayoutObjs_listview_articoli_circolari_xml LAYOUT_OBJs;
        LAYOUT_OBJs = new LayoutObjs_listview_articoli_circolari_xml(listItem);
        // Initialize the views in the layout
        ImageView iv = LAYOUT_OBJs.image;
        TextView textView_info_circolare = LAYOUT_OBJs.textView_info_circolare;
        TextView textView_oggetto = LAYOUT_OBJs.textView_oggetto;
        TextView textView_tag = LAYOUT_OBJs.textView_tag;

        final ArticoloSdo<ArticoloDetailsCircolare> c = this.articoliCircolari.articoli.get(position);
        final ArticoloDetailsCircolare circolare = c.getDetails();

        textView_info_circolare.setText("Circolare n." + circolare.numeroCircolare + " del " + C_DateUtil.toDDMMYYY(circolare.dataCircolare));
        textView_oggetto.setText(circolare.oggetto);

        StringBuilder sb = new StringBuilder();
        for (ArticoloTagDetails tag : c.getDetails().getTags()) {
            sb.append(" ").append(tag.getTag());
        }
        textView_tag.setText(sb.toString().trim());

        return listItem;
    }


}
