package it.gov.scuolesuperioridizagarolo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.cache.UrlImageLoader;
import it.gov.scuolesuperioridizagarolo.dao.NewsDB;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_listview_circolari_and_notizie_xml;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;
import it.gov.scuolesuperioridizagarolo.util.C_TextUtil;
import it.gov.scuolesuperioridizagarolo.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 04/03/15.
 */
public class NewsListAdapter extends BaseAdapter {

    public UrlImageLoader imageLoader;
    private List<NewsDB> feed;

    private Activity activity;
    private LayoutInflater layoutInflater;

    public NewsListAdapter(AbstractFragment e, List<NewsDB> feed) {
        this.feed = feed;
        activity = e.getActivity();
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Point size = ScreenUtil.getSize(activity);
        imageLoader = new UrlImageLoader(e, size.x / 3, size.y / 3, R.drawable.clessidra_30x30);
    }

    public NewsListAdapter(AbstractFragment e) {
        this(e, new ArrayList<NewsDB>());
    }

    public List<NewsDB> getRssItems() {
        return feed;
    }

    public void update(List<NewsDB> feed) {
        this.feed = feed;
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        // Set the total list item count
        return feed.size();
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
        int pos = position;
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.listview_circolari_and_notizie, null);
        }

        LayoutObjs_listview_circolari_and_notizie_xml LAYOUT_OBJs = new LayoutObjs_listview_circolari_and_notizie_xml(listItem);
        // Initialize the views in the layout
        final NewsDB c = feed.get(pos);


        String text = C_TextUtil.extractContentFromHTML(c.getTesto());
        if (text.length() > 500) {
            text = text.substring(500) + " [...]";
        }


        LAYOUT_OBJs.date.setText(C_DateUtil.toDDMMYYY(c.getPubDate()));

        if (c.getFlagContenutoLetto()) {
            LAYOUT_OBJs.title.setTypeface(Typeface.DEFAULT);
            LAYOUT_OBJs.title.setText(c.getTitolo() + "");
            LAYOUT_OBJs.descrizione.setText(text);
            LAYOUT_OBJs.image.setImageResource(R.drawable.arrow_next);
        } else {
            LAYOUT_OBJs.title.setTypeface(Typeface.DEFAULT_BOLD);
            LAYOUT_OBJs.title.setText(c.getTitolo());
            LAYOUT_OBJs.descrizione.setText(text);
            LAYOUT_OBJs.image.setImageResource(R.drawable.new_icon_50x50);

        }


        return listItem;
    }


}
