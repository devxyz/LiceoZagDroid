package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_listview_news_rssitem_xml {
    public final ImageView arrow;
    public final TextView title;
    public final TextView date;

    public LayoutObjs_listview_news_rssitem_xml(Fragment f) {
        View view = f.getView();
        arrow = (ImageView) view.findViewById(R.id.arrow);
        title = (TextView) view.findViewById(R.id.title);
        date = (TextView) view.findViewById(R.id.date);
    }

    public LayoutObjs_listview_news_rssitem_xml(Activity view) {
        arrow = (ImageView) view.findViewById(R.id.arrow);
        title = (TextView) view.findViewById(R.id.title);
        date = (TextView) view.findViewById(R.id.date);

    }

    public LayoutObjs_listview_news_rssitem_xml(View view) {
        arrow = (ImageView) view.findViewById(R.id.arrow);
        title = (TextView) view.findViewById(R.id.title);
        date = (TextView) view.findViewById(R.id.date);

    }

    public LayoutObjs_listview_news_rssitem_xml(Dialog view) {
        arrow = (ImageView) view.findViewById(R.id.arrow);
        title = (TextView) view.findViewById(R.id.title);
        date = (TextView) view.findViewById(R.id.date);

    }
}
   
